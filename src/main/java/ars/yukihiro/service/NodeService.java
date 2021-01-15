package ars.yukihiro.service;

import ars.yukihiro.constants.NodeType;
import ars.yukihiro.entity.Node;
import ars.yukihiro.form.NodeForm;
import ars.yukihiro.repository.NodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleUnresolved;
import java.util.Optional;

/**
 * Node用サービスクラス
 * @atuher yukihiro adachi
 */
@Service
public class NodeService extends AbstractService {

    private static final Logger logger =
            LoggerFactory.getLogger(NodeService.class);

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
            Node entity = optNodeId
                    .map(nodeId ->
                            nodeRepository
                                    .findById(nodeId)
                                    .orElseGet(() ->new Node())) // findById null
                    .orElseGet(() -> new Node()); // nodeId null
            entity.setNodeType(
                    String.valueOf(
                            form.getNodeType().getValue()));
            entity.setHierarchy(form.getHierarchy());
            entity.setNodeNmLgc(form.getNodeNmLgc());
            entity.setNodeNmPsc(form.getNodeNmPsc());
            logger.info(form.getNodeNmLgc());
            entity.setContentsId(form.getContentsId());
            entity.setUpDt(getCurrentTimeStamp());
            entity.setUpNm("ADMIN"); // TODO Userオブジェクトより取得
            // TODO newNodeIdの場合のみ、登録日、登録者を設定する
            nodeRepository.save(entity);
        } catch(Exception e) {
            throw e;
        } finally {
            nodeRepository.flush();
        }
    }
}
