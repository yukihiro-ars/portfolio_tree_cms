package ars.yukihiro.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Contents {
    private String contentsId;
    private String contentsVal;
    private String upDt;
    private String upNm;
    private String rgDt;
    private String rgNm;

    @Id
    @Column(name = "contents_id", nullable = false, length = 10)
    public String getContentsId() {
        return contentsId;
    }

    public void setContentsId(String contentsId) {
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
    @Column(name = "up_dt", nullable = true, length = 17)
    public String getUpDt() {
        return upDt;
    }

    public void setUpDt(String upDt) {
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
    @Column(name = "rg_dt", nullable = true, length = 17)
    public String getRgDt() {
        return rgDt;
    }

    public void setRgDt(String rgDt) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contents contents = (Contents) o;

        if (contentsId != null ? !contentsId.equals(contents.contentsId) : contents.contentsId != null) return false;
        if (contentsVal != null ? !contentsVal.equals(contents.contentsVal) : contents.contentsVal != null)
            return false;
        if (upDt != null ? !upDt.equals(contents.upDt) : contents.upDt != null) return false;
        if (upNm != null ? !upNm.equals(contents.upNm) : contents.upNm != null) return false;
        if (rgDt != null ? !rgDt.equals(contents.rgDt) : contents.rgDt != null) return false;
        if (rgNm != null ? !rgNm.equals(contents.rgNm) : contents.rgNm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contentsId != null ? contentsId.hashCode() : 0;
        result = 31 * result + (contentsVal != null ? contentsVal.hashCode() : 0);
        result = 31 * result + (upDt != null ? upDt.hashCode() : 0);
        result = 31 * result + (upNm != null ? upNm.hashCode() : 0);
        result = 31 * result + (rgDt != null ? rgDt.hashCode() : 0);
        result = 31 * result + (rgNm != null ? rgNm.hashCode() : 0);
        return result;
    }
}
