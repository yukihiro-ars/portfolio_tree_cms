package ars.yukihiro.portfolio.tree.cms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(schema = "business")
public class Contents implements Serializable, IAuditEntity {
    private int contentsId;
    private String contentsType;
    private Timestamp upDt;
    private String upNm;
    private Timestamp rgDt;
    private String rgNm;
    private long version;

    @Id
    @Column(name = "contents_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getContentsId() {
        return contentsId;
    }

    public void setContentsId(int contentsId) {
        this.contentsId = contentsId;
    }

    @Basic
    @Column(name = "contents_type", nullable = false, length = -1)
    public String getContentsType() {
        return contentsType;
    }

    public void setContentsType(String contentsType) {
        this.contentsType = contentsType;
    }

    @Basic
    @Column(name = "up_dt", nullable = true)
    public Timestamp getUpDt() {
        return upDt;
    }

    public void setUpDt(Timestamp upDt) {
        this.upDt = upDt;
    }

    @Basic
    @Column(name = "up_nm", nullable = true, length = 20)
    public String getUpNm() {
        return upNm;
    }

    public void setUpNm(String upNm) {
        this.upNm = upNm;
    }

    @Basic
    @Column(name = "rg_dt", nullable = true)
    public Timestamp getRgDt() {
        return rgDt;
    }

    public void setRgDt(Timestamp rgDt) {
        this.rgDt = rgDt;
    }

    @Basic
    @Column(name = "rg_nm", nullable = true, length = 20)
    public String getRgNm() {
        return rgNm;
    }

    public void setRgNm(String rgNm) {
        this.rgNm = rgNm;
    }

    @Basic
    @Version
    @Column(name = "version", nullable = false)
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contents contents = (Contents) o;
        return contentsId == contents.contentsId &&
                version == contents.version &&
                Objects.equals(contentsType, contents.contentsType) &&
                Objects.equals(upDt, contents.upDt) &&
                Objects.equals(upNm, contents.upNm) &&
                Objects.equals(rgDt, contents.rgDt) &&
                Objects.equals(rgNm, contents.rgNm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentsId, contentsType, upDt, upNm, rgDt, rgNm, version);
    }
}
