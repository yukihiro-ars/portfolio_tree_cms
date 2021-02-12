package ars.yukihiro.service;

import ars.yukihiro.entity.IAdminEntity;
import ars.yukihiro.response.form.AbstractNodeForm;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author yukihiro adachi
 */
public abstract class AbstractService {
    protected static Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    protected <E extends IAdminEntity> void setAdminProperties(E entity, boolean isNew) {
        entity.setUpDt(getCurrentTimeStamp());
        entity.setUpNm("Admin");
        if (isNew) {
            entity.setRgDt(getCurrentTimeStamp());
            entity.setRgNm("Admin");
        }
    }
}
