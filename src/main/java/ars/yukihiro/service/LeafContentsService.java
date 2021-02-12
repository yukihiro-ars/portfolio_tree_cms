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
import java.util.function.Supplier;

/**
 * Contents用サービスクラス.
 * @atuher yukihiro adachi
 */
@Service
public class LeafContentsService extends AbstractNodeService<LeafContentsForm> {
    private static final Logger logger =
            LoggerFactory.getLogger(LeafContentsService.class);

    private Supplier<Contents> newContents = () -> new Contents();

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
            // TODO 楽観排他メソッドの共通化、及びentityの取得方法の改善を検討
            // TODO 処理内容精査
            Optional<Integer> optContentsId =
                    Optional.ofNullable(form.getContentsId());
            Contents contents = optContentsId
                    .map(id -> contentsRepository
                            .findById(id)
                            .orElseGet(newContents)) // findById null
                    .orElseGet(newContents); // nodeId null
            contents.setContentsVal(form.getContentsVal());
            contents.setUpDt(getCurrentTimeStamp());
            contents.setUpNm("ADMIN"); // TODO UpNm オブジェクトより取得
            if (optContentsId.isEmpty()) {
                contents.setRgDt(getCurrentTimeStamp());
                contents.setRgNm("ADMIN"); // TODO RgNm オブジェクトより取得
            } else if (form.getContentsVersion() != contents.getVersion()) {
                // 楽観排他チェック
                throw new ObjectOptimisticLockingFailureException(Contents.class, optContentsId.get());
            }
            Contents c = contentsRepository.save(contents);
            // contentsIDを設定
            if (optContentsId.isEmpty()) {
                form.setContentsId(c.getContentsId());
            }
            saveAndFlushNode(form);
        } catch(Exception e) {
            throw e;
        } finally {
            contentsRepository.flush();
        }
    }
}
