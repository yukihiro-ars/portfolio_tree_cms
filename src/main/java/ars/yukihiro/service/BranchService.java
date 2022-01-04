package ars.yukihiro.service;

import ars.yukihiro.response.form.BranchForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Node用サービスクラス.
 * @atuher yukihiro adachi
 */
@Service
public class BranchService extends AbstractNodeService<BranchForm> {

    private static final Logger logger =
            LoggerFactory.getLogger(BranchService.class);

    /**
     * @return NodeForm
     */
    @Override
    public BranchForm getNodeForm(Integer nodeId) {
        try {
            return (BranchForm) findNodeForm(nodeId, BranchForm::new)
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
    public void upsertNodeByForm(BranchForm form) {
        try {
            saveNode(form);
        } catch(Exception e) {
            throw e;
        } finally {
            nodeRepository.flush();
        }
    }
}
