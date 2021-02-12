package ars.yukihiro.service;

import ars.yukihiro.entity.Contents;
import ars.yukihiro.repository.ContentsRepository;
import ars.yukihiro.response.form.AbstractNodeForm;
import ars.yukihiro.response.form.LeafContentsForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * Contents用サービスクラス.
 * @atuher yukihiro adachi
 */
@Service
public class LeafContentsService extends AbstractNodeService<LeafContentsForm> {
    private static final Logger logger =
            LoggerFactory.getLogger(LeafContentsService.class);

    @Autowired
    private ContentsRepository contentsRepository;

    /**
     * @return ContentsForm
     */
    @Override
    public LeafContentsForm getNodeForm(Integer nodeId) {
        try {
            Optional<AbstractNodeForm> optForm = findNodeForm(nodeId, LeafContentsForm::new);
            if (optForm.isEmpty()) {
                return null;
            }
            LeafContentsForm form = (LeafContentsForm) optForm.get();
            Optional<Contents> optContents = contentsRepository.findById(form.getContentsId());
            if (optContents.isEmpty()) {
                return null;
            }
            Contents contents = optContents.get();
            form.setContentsVal(contents.getContentsVal());
            form.setContentsVersion(contents.getVersion());
            return form;
        } catch(Exception e) {
            throw e;
        }
    }

    /**
     * @param form
     */
    @Override
    @Transactional
    public void upsertNodeByForm(LeafContentsForm form) {
        try {
            // TODO 処理内容精査
            Contents entity = findOrCreateEntity(form.getContentsId(), contentsRepository, Contents::new);
            entity.setContentsVal(form.getContentsVal());

            // 管理プロパティの設定
            boolean isNew = Objects.isNull(form.getContentsId());
            setAdminProperties(entity, isNew);
            if (!isNew && form.getContentsVersion() != entity.getVersion()) {
                // 楽観排他チェック
                throw new ObjectOptimisticLockingFailureException(Contents.class, form.getContentsId());
            }
            // contentsの更新
            entity = contentsRepository.save(entity);
            // contentsIDを設定
            if (Objects.isNull(form.getContentsId())) {
                form.setContentsId(entity.getContentsId());
            }
            // nodeの更新
            saveNode(form);
        } catch(Exception e) {
            throw e;
        } finally {
            contentsRepository.flush();
        }
    }
}
