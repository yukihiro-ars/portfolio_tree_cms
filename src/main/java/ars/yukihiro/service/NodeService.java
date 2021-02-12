package ars.yukihiro.service;

import ars.yukihiro.response.form.NodeForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Node用サービスクラス.
 * @atuher yukihiro adachi
 */
@Service
public class NodeService  extends AbstractNodeService<NodeForm> {

    private static final Logger logger =
            LoggerFactory.getLogger(NodeService.class);

    /**
     * @return NodeForm
     */
    @Override
    public NodeForm getNodeForm(Integer nodeId) {
        try {
            return (NodeForm) findNodeForm(nodeId, NodeForm::new);
        } catch(Exception e) {
            throw e;
        }
    }

    /**
     * @param form
     */
    @Override
    @Transactional
    public void upsertNodeByForm(NodeForm form) {
        saveAndFlushNode(form);
    }
}
