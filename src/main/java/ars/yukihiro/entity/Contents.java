package ars.yukihiro.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "business")
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
        return Objects.equals(contentsId, contents.contentsId) &&
                Objects.equals(contentsVal, contents.contentsVal) &&
                Objects.equals(upDt, contents.upDt) &&
                Objects.equals(upNm, contents.upNm) &&
                Objects.equals(rgDt, contents.rgDt) &&
                Objects.equals(rgNm, contents.rgNm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentsId, contentsVal, upDt, upNm, rgDt, rgNm);
    }
}
