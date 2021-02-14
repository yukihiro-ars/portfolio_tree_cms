package ars.yukihiro.service;

import ars.yukihiro.response.form.InternalForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Node用サービスクラス.
 * @atuher yukihiro adachi
 */
// TODO InternalNodeServiceに名称変更する
@Service
public class InternalService extends AbstractNodeService<InternalForm> {

    private static final Logger logger =
            LoggerFactory.getLogger(InternalService.class);

    /**
     * @return NodeForm
     */
    @Override
    public InternalForm getNodeForm(Integer nodeId) {
        try {
            return (InternalForm) findNodeForm(nodeId, InternalForm::new)
                    .orElse(null);
        } catch(Exception e) {
            throw e;
        }
    }

    /**
     * @param form
     */
    @Override
    @Transactional
    public void upsertNodeByForm(InternalForm form) {
        try {
            saveNode(form);
        } catch(Exception e) {
            throw e;
        } finally {
            nodeRepository.flush();
        }
    }
}
