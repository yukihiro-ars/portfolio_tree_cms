package ars.yukihiro.service;

import ars.yukihiro.constants.NodeType;
import ars.yukihiro.entity.Node;
import ars.yukihiro.form.NodeForm;
import ars.yukihiro.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Node用サービスクラス
 * @atuher yukihiro adachi
 */
@Service
public class NodeService {

    @Autowired
    private NodeRepository nodeRepository;
    /**
     * @return
     */
    public NodeForm getNodeForm(String nodeId) {
        try {
            Node entity = nodeRepository.findNode(nodeId);
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
        } catch(Exception e) {
            throw e;
        }
    }

    /**
     * @param form
     */
    public void updateNodeByForm(NodeForm form) {
        try {
            Node entity = new Node();
            entity.setNodeId(form.getNodeId());
            entity.setNodeType(
                    String.valueOf(
                            form.getNodeType().getValue()));
            entity.setHierarchy(form.getHierarchy());
            entity.setNodeNmLgc(form.getNodeNmLgc());
            entity.setNodeNmPsc(form.getNodeNmPsc());
            entity.setContentsId(form.getContentsId());
            nodeRepository.updateNode(entity);
        } catch(Exception e) {
            throw e;
        }
    }
}
