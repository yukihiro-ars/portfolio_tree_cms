package ars.yukihiro.service;

import ars.yukihiro.entity.IAuditEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

/**
 * @author yukihiro adachi
 */
public abstract class AbstractService {

    private static final Logger logger =
            LoggerFactory.getLogger(AbstractService.class);

    protected static Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    // TODO AuditProviderを使う
    // @see https://terasolunaorg.github.io/guideline/public_review/ArchitectureInDetail/DataAccessJpa.html
    protected <E extends IAuditEntity> void setAuditProperties(E entity, boolean isNew) {
        entity.setUpDt(getCurrentTimeStamp());
        entity.setUpNm("Admin");
        if (isNew) {
            entity.setRgDt(getCurrentTimeStamp());
            entity.setRgNm("Admin");
        }
    }
}
