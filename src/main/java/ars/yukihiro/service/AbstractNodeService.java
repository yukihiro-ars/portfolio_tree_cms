package ars.yukihiro.service;

import ars.yukihiro.entity.Node;
import ars.yukihiro.enums.NodeType;
import ars.yukihiro.repository.NodeRepository;
import ars.yukihiro.response.form.AbstractNodeForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

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

    protected <E,ID> E findOrCreateEntity(ID id, JpaRepository<E, ID> repository, Supplier<E> supplier) {
        Optional<ID> optId =
                Optional.ofNullable(id);
        return optId.map(id2 -> repository
                .findById(id2)
                .orElseGet(supplier)) // findById null
        .orElseGet(supplier); // nodeId null
    }

    protected Node saveNode(AbstractNodeForm form) {
        try {
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
            return nodeRepository.save(entity);
        } catch(Exception e) {
            throw e;
        }
    }
}
