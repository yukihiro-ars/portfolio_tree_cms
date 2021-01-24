package ars.yukihiro.service;

import ars.yukihiro.enums.NodeType;
import ars.yukihiro.entity.Node;
import ars.yukihiro.response.form.NodeForm;
import ars.yukihiro.repository.NodeRepository;
import java.util.Optional;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Node用サービスクラス.
 * @atuher yukihiro adachi
 */
@Service
public class NodeService extends AbstractService {

    private static final Logger logger =
            LoggerFactory.getLogger(NodeService.class);

    private Supplier<Node> newNode = () -> new Node();

    @Autowired
    private NodeRepository nodeRepository;
    /**
     * @return NodeForm
     */
    public NodeForm getNodeForm(Integer nodeId) {
        try {
            return nodeRepository.findById(nodeId).map(entity -> {
                NodeForm form = new NodeForm();
                form.setNodeId(entity.getNodeId());
                form.setNodeType(
                        NodeType.convertByValue(
                                Integer.parseInt(
                                        entity.getNodeType())));
                form.setHierarchy(entity.getHierarchy());
                form.setNodeNmLgc(entity.getNodeNmLgc());
                form.setNodeNmPsc(entity.getNodeNmPsc());
                form.setContentsId(entity.getContentsId());
                form.setVersion(entity.getVersion());
                return form;
            }).orElse(null);
        } catch(Exception e) {
            throw e;
        }
    }

    /**
     * @param form
     */
    @Transactional
    public void upsertNodeByForm(NodeForm form) {
        try {
            Optional<Integer> optNodeId =
                    Optional.ofNullable(form.getNodeId());
            // TODO 楽観排他メソッドの共通化、及びentityの取得方法の改善を検討
            Node entity = optNodeId
                    .map(nodeId ->
                            nodeRepository
                                    .findById(nodeId)
                                    .orElseGet(newNode)) // findById null
                    .orElseGet(newNode); // nodeId null

            entity.setNodeType(
                    String.valueOf(
                            form.getNodeType().getValue()));
            entity.setHierarchy(form.getHierarchy());
            entity.setNodeNmLgc(form.getNodeNmLgc());
            entity.setNodeNmPsc(form.getNodeNmPsc());
            logger.info(form.getNodeNmLgc());
            entity.setContentsId(form.getContentsId());
            entity.setUpDt(getCurrentTimeStamp());
            entity.setUpNm("ADMIN"); // TODO UpNm オブジェクトより取得
            if (optNodeId.isEmpty()) {
                entity.setRgDt(getCurrentTimeStamp());
                entity.setRgNm("ADMIN"); // TODO RgNm オブジェクトより取得
            } else if (form.getVersion() != entity.getVersion()) {
                // 楽観排他チェック
                throw new ObjectOptimisticLockingFailureException(Node.class, optNodeId.get());
            }
            nodeRepository.save(entity);
        } catch(Exception e) {
            throw e;
        } finally {
            nodeRepository.flush();
        }
    }
}
