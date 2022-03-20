package ars.yukihiro.portfolio.tree.cms.service;

import ars.yukihiro.portfolio.tree.cms.entity.Contents;
import ars.yukihiro.portfolio.tree.cms.entity.ContentsPlane;
import ars.yukihiro.portfolio.tree.cms.enums.ApplicationMessageId;
import ars.yukihiro.portfolio.tree.cms.enums.ContentsType;
import ars.yukihiro.portfolio.tree.cms.message.ApplicationMessageBundle;
import ars.yukihiro.portfolio.tree.cms.repository.ContentsPlaneRepository;
import ars.yukihiro.portfolio.tree.cms.repository.ContentsRepository;
import ars.yukihiro.portfolio.tree.cms.response.form.AbstractNodeForm;
import ars.yukihiro.portfolio.tree.cms.response.form.LeafPlaneForm;
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
public class LeafPlaneService extends AbstractNodeService<LeafPlaneForm> {
    private static final Logger logger =
            LoggerFactory.getLogger(LeafPlaneService.class);

    @Autowired
    private ContentsRepository contentsRepository;
    @Autowired
    private ContentsPlaneRepository contentsPlaneRepository;

    /**
     * @return ContentsForm
     */
    @Override
    public LeafPlaneForm getNodeForm(Integer nodeId) {
        try {
            // NODE
            Optional<AbstractNodeForm> optForm = findNodeForm(nodeId, LeafPlaneForm::new);
            if (optForm.isEmpty()) {
                return null;
            }
            LeafPlaneForm form = (LeafPlaneForm) optForm.get();
            // コンテンツIDのないノードリクエストの場合
            if (Objects.isNull(form.getContentsId())) {
                throw new IllegalArgumentException(
                        ApplicationMessageBundle.getMessage(
                                ApplicationMessageId.M10_E_01));
            }
            // CONTENTS
            Optional<Contents> optContents = contentsRepository.findById(form.getContentsId());
            if (optContents.isEmpty()) {
                return null;
            }
            Contents contents = optContents.get();
            form.setContentsType(ContentsType.convertByValue(contents.getContentsType()));
            form.setContentsVersion(contents.getVersion());
            // CONTENTS_PLANE
            Optional<ContentsPlane> optPlane = contentsPlaneRepository.findById(form.getContentsId());
            if (optPlane.isEmpty()) {
                return null;
            }
            ContentsPlane plane = optPlane.get();
            form.setContentsVal(plane.getContentsVal());
            form.setContentsPlaneVersion(plane.getVersion());

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
    public void upsertNodeByForm(LeafPlaneForm form) {
        try {
            // TODO 処理内容精査
            Contents contents = findOrCreateEntity(form.getContentsId(), contentsRepository, Contents::new);
            // 監査プロパティの設定
            boolean isNew = Objects.isNull(form.getContentsId());
            setAuditProperties(contents , isNew);
            if (!isNew && form.getContentsVersion() != contents .getVersion()) {
                // 楽観排他チェック
                throw new ObjectOptimisticLockingFailureException(Contents.class, form.getContentsId());
            }
            // contentsの更新
            contents  = contentsRepository.save(contents );
            // contentsIDを設定
            if (Objects.isNull(form.getContentsId())) {
                form.setContentsId(contents .getContentsId());
            }
            // contentsPlaneの更新
            ContentsPlane plane = findOrCreateEntity(form.getContentsId(), contentsPlaneRepository, ContentsPlane::new);
            plane.setContentsId(form.getContentsId());
            plane.setContentsVal(form.getContentsVal());
            setAuditProperties(plane, isNew);
            contentsPlaneRepository.save(plane);

            // nodeの更新
            saveNode(form);
        } catch(Exception e) {
            throw e;
        } finally {
            contentsRepository.flush();
        }
    }
}
