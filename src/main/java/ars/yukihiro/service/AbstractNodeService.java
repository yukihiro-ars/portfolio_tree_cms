package ars.yukihiro.service;

import ars.yukihiro.entity.Node;
import ars.yukihiro.entity.NodeRelationship;
import ars.yukihiro.entity.NodeRelationshipPK;
import ars.yukihiro.enums.NodeType;
import ars.yukihiro.repository.NodeRelationshipRepository;
import ars.yukihiro.repository.NodeRepository;
import ars.yukihiro.repository.helper.NodeRelationshipRepositoryConditioningHelper;
import ars.yukihiro.response.form.AbstractNodeForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author yukihiro adachi
 */
public abstract class AbstractNodeService<T extends AbstractNodeForm> extends AbstractService implements INodeService<T> {

    private static final Logger logger =
            LoggerFactory.getLogger(AbstractNodeService.class);

    @Autowired
    protected NodeRepository nodeRepository;

    @Autowired
    protected NodeRelationshipRepository nodeRelationshipRepository;
    /**
     * @param nodeId
     * @return
     */
    protected Optional<AbstractNodeForm> findNodeForm(Integer nodeId, Supplier<T> supplier) {
        return nodeRepository.findById(nodeId).map(entity -> {
            AbstractNodeForm form = supplier.get();
            form.setNodeId(entity.getNodeId());
            form.setNodeType(NodeType.convertByValue(entity.getNodeType()));
            form.setHierarchy(entity.getHierarchy());
            form.setNodeNmLgc(entity.getNodeNmLgc());
            form.setNodeNmPsc(entity.getNodeNmPsc());
            form.setContentsId(entity.getContentsId());
            form.setNodeVersion(entity.getVersion());
            return form;
        });
    }

    /**
     * @param id
     * @param repository
     * @param supplier
     * @param <E>
     * @param <ID>
     * @return
     */
    protected <E,ID> E findOrCreateEntity(ID id, JpaRepository<E, ID> repository, Supplier<E> supplier) {
        Optional<ID> optId =
                Optional.ofNullable(id);
        return optId.map(id2 -> repository
                .findById(id2)
                .orElseGet(supplier)) // findById null
        .orElseGet(supplier); // nodeId null
    }

    /**
     * ノード生成処理.
     * @param form
     * @return
     */
    protected Node saveNode(AbstractNodeForm form) {
        Node entity = findOrCreateEntity(form.getNodeId(), nodeRepository, Node::new);
        entity.setNodeType(String.valueOf(form.getNodeType().getValue()));
        entity.setHierarchy(form.getHierarchy());
        entity.setNodeNmLgc(form.getNodeNmLgc());
        entity.setNodeNmPsc(form.getNodeNmPsc());
        entity.setContentsId(form.getContentsId());

        boolean isNew = Objects.isNull(form.getNodeId());
        setAuditProperties(entity, isNew);
        if (!isNew && form.getNodeVersion() != entity.getVersion()) {
            // 楽観排他チェック
            throw new ObjectOptimisticLockingFailureException(Node.class, form.getNodeId());
        }
        Node node = nodeRepository.save(entity);

        // ノード関連付けテーブルの登録(Node生成処理内に隠ぺい)
        if (enableFormWithCreateNodeRelationPK(form)) {
            NodeRelationshipPK pk = new NodeRelationshipPK();
            pk.setParentNodeId(form.getParentNodeId());
            pk.setChildNodeId(node.getNodeId());
            createNodeRelationship(pk);
        }
        return node;
    }

    /**
     * 当該フォームがノード関連付けの新規作成可能なフォームかを検証.
     * @param form
     * @return ノード関連付けの新規作成可能 : true
     */
    private boolean enableFormWithCreateNodeRelationPK(AbstractNodeForm form) {
        return  Objects.isNull(form.getParentNodeId()) == false;
    }

    /**
     * ノード関連付け情報作成.
     * ノード生成処理(saveNode)からのみ呼び出し可.
     * @param key
     * @return NodeRelationship
     */
    private NodeRelationship createNodeRelationship(NodeRelationshipPK key) {
        // parentNodeIdに紐づく関連付け情報を取得
        List<NodeRelationship> children =
                nodeRelationshipRepository.findAll(
                        NodeRelationshipRepositoryConditioningHelper.parentNodeIdIs(
                                key.getParentNodeId()));
        // 登録済み判定
        boolean hasAlready = children
                .stream()
                .anyMatch(e -> e.getChildNodeId() == key.getChildNodeId());

        if (hasAlready) {
            logger.warn(
                    "既に登録済みのノード関連付け情報です。parent:{0}, child:{1}",
                    key.getParentNodeId(),
                    key.getChildNodeId());
            return null;
        }

        // 最大ソート番号の取得
        Optional<Short> maxSort = children.stream()
                .map(e -> e.getSort())
                .max(Comparator.naturalOrder());
        Short max = (short) 1;
        if (maxSort.isPresent()) {
            max = (short) (maxSort.get() +  1);
        }

        // entityの登録
        NodeRelationship entity = new NodeRelationship();
        entity.setParentNodeId(key.getParentNodeId());
        entity.setChildNodeId(key.getChildNodeId());
        entity.setSort(max);
        setAuditProperties(entity, true);

        return nodeRelationshipRepository.save(entity);
    }
}
