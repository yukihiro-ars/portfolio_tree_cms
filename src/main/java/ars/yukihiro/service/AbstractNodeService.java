package ars.yukihiro.service;

import ars.yukihiro.entity.Node;
import ars.yukihiro.enums.NodeType;
import ars.yukihiro.repository.NodeRepository;
import ars.yukihiro.response.form.AbstractNodeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author yukihiro adachi
 */
public abstract class AbstractNodeService<T extends AbstractNodeForm> extends AbstractService implements INodeService<T> {

    protected Supplier<Node> newNode = () -> new Node();

    @Autowired
    protected NodeRepository nodeRepository;

    /**
     * @param nodeId
     * @return
     */
    // TODO 戻り値をオプショナルにしてもよい。検討　戻り値の粒度を揃えたい。
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

    // TODO メソッド内でflushまでするか?、あくまでflushは実行側にまかせるか？
    protected Node saveAndFlushNode(AbstractNodeForm form) {
        try {
            Optional<Integer> optNodeId =
                    Optional.ofNullable(form.getNodeId());
            Node entity = optNodeId.map(id -> nodeRepository
                    .findById(id)
                    .orElseGet(newNode)) // findById null
                .orElseGet(newNode); // nodeId null
            entity.setNodeType(String.valueOf(form.getNodeType().getValue()));
            entity.setHierarchy(form.getHierarchy());
            entity.setNodeNmLgc(form.getNodeNmLgc());
            entity.setNodeNmPsc(form.getNodeNmPsc());
            entity.setContentsId(form.getContentsId());

            // TODO 楽観排他メソッドの共通化、及びentityの取得方法の改善を検討
            entity.setUpDt(getCurrentTimeStamp());
            entity.setUpNm("ADMIN"); // TODO UpNm オブジェクトより取得
            if (optNodeId.isEmpty()) {
                entity.setRgDt(getCurrentTimeStamp());
                entity.setRgNm("ADMIN"); // TODO RgNm オブジェクトより取得
            } else if (form.getNodeVersion() != entity.getVersion()) {
                // 楽観排他チェック
                throw new ObjectOptimisticLockingFailureException(Node.class, optNodeId.get());
            }
            return nodeRepository.save(entity);
        } catch(Exception e) {
            throw e;
        } finally {
            nodeRepository.flush();
        }
    }
}
