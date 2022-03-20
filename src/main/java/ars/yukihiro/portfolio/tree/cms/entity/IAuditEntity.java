package ars.yukihiro.portfolio.tree.cms.entity;

import java.sql.Timestamp;

public interface IAuditEntity {

    Timestamp getUpDt();
    void setUpDt(Timestamp upDt);

    String getUpNm();
    void setUpNm(String upNm);

    Timestamp getRgDt();
    void setRgDt(Timestamp rgDt);

    String getRgNm();
    void setRgNm(String rgNm);

    long getVersion();
    void setVersion(long version);
}
