package ars.yukihiro.portfolio.tree.cms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "contents_plane", schema = "business", catalog = "ars")
public class ContentsPlane implements Serializable, IAuditEntity {
    private int contentsId;
    private String contentsVal;
    private Timestamp upDt;
    private String upNm;
    private Timestamp rgDt;
    private String rgNm;
    private long version;

    @Id
    @Column(name = "contents_id", nullable = false)
    public int getContentsId() {
        return contentsId;
    }

    public void setContentsId(int contentsId) {
        this.contentsId = contentsId;
    }

    @Basic
    @Column(name = "contents_val", nullable = false, length = 140)
    public String getContentsVal() {
        return contentsVal;
    }

    public void setContentsVal(String contentsVal) {
        this.contentsVal = contentsVal;
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
        ContentsPlane that = (ContentsPlane) o;
        return contentsId == that.contentsId &&
                version == that.version &&
                Objects.equals(contentsVal, that.contentsVal) &&
                Objects.equals(upDt, that.upDt) &&
                Objects.equals(upNm, that.upNm) &&
                Objects.equals(rgDt, that.rgDt) &&
                Objects.equals(rgNm, that.rgNm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentsId, contentsVal, upDt, upNm, rgDt, rgNm, version);
    }
}
