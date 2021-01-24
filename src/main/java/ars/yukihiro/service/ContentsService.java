package ars.yukihiro.service;

import ars.yukihiro.entity.Contents;
import ars.yukihiro.response.form.ContentsForm;
import ars.yukihiro.repository.ContentsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Contents用サービスクラス.
 * @atuher yukihiro adachi
 */
@Service
public class ContentsService extends AbstractService {
    private static final Logger logger =
            LoggerFactory.getLogger(ContentsService.class);

    private Supplier<Contents> newContents = () -> new Contents();

    @Autowired
    private ContentsRepository contentsRepository;

    /**
     * @return ContentsForm
     */
    public ContentsForm getContentsForm(Integer contentsId) {
        try {
            return contentsRepository.findById(contentsId).map(entity -> {
                ContentsForm form = new ContentsForm();
                form.setContentsId(entity.getContentsId());
                form.setContentsVal(entity.getContentsVal());
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
    public void upsertContentsByForm(ContentsForm form) {
        try {

            Optional<Integer> optContentsId =
                    Optional.ofNullable(form.getContentsId());
            // TODO 楽観排他メソッドの共通化、及びentityの取得方法の改善を検討
            Contents entity = optContentsId
                    .map(nodeId ->
                            contentsRepository
                                    .findById(nodeId)
                                    .orElseGet(newContents)) // findById null
                    .orElseGet(newContents); // nodeId null

            entity.setContentsVal(form.getContentsVal());
            entity.setUpDt(getCurrentTimeStamp());
            entity.setUpNm("ADMIN"); // TODO UpNm オブジェクトより取得

            if (optContentsId.isEmpty()) {
                entity.setRgDt(getCurrentTimeStamp());
                entity.setRgNm("ADMIN"); // TODO RgNm オブジェクトより取得
            } else if (form.getVersion() != entity.getVersion()) {
                // 楽観排他チェック
                throw new ObjectOptimisticLockingFailureException(Contents.class, optContentsId.get());
            }
            contentsRepository.save(entity);
        } catch(Exception e) {
            throw e;
        } finally {
            contentsRepository.flush();
        }
    }
}
