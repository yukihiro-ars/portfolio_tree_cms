package ars.yukihiro.portfolio.tree.cms.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "blob_management", schema = "business", catalog = "ars")
public class BlobManagement {
    private String blobId;
    private byte[] blobData;
    private Timestamp upDt;
    private String upNm;
    private Timestamp rgDt;
    private String rgNm;
    private long version;

    @Id
    @Column(name = "blob_id", nullable = false, length = 32)
    public String getBlobId() {
        return blobId;
    }

    public void setBlobId(String blobId) {
        this.blobId = blobId;
    }

    @Basic
    @Column(name = "blob_data", nullable = false)
    public byte[] getBlobData() {
        return blobData;
    }

    public void setBlobData(byte[] blobData) {
        this.blobData = blobData;
    }

    @Basic
    @Column(name = "up_dt", nullable = false)
    public Timestamp getUpDt() {
        return upDt;
    }

    public void setUpDt(Timestamp upDt) {
        this.upDt = upDt;
    }

    @Basic
    @Column(name = "up_nm", nullable = false, length = 20)
    public String getUpNm() {
        return upNm;
    }

    public void setUpNm(String upNm) {
        this.upNm = upNm;
    }

    @Basic
    @Column(name = "rg_dt", nullable = false)
    public Timestamp getRgDt() {
        return rgDt;
    }

    public void setRgDt(Timestamp rgDt) {
        this.rgDt = rgDt;
    }

    @Basic
    @Column(name = "rg_nm", nullable = false, length = 20)
    public String getRgNm() {
        return rgNm;
    }

    public void setRgNm(String rgNm) {
        this.rgNm = rgNm;
    }

    @Basic
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
        BlobManagement that = (BlobManagement) o;
        return version == that.version &&
                Objects.equals(blobId, that.blobId) &&
                Arrays.equals(blobData, that.blobData) &&
                Objects.equals(upDt, that.upDt) &&
                Objects.equals(upNm, that.upNm) &&
                Objects.equals(rgDt, that.rgDt) &&
                Objects.equals(rgNm, that.rgNm);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(blobId, upDt, upNm, rgDt, rgNm, version);
        result = 31 * result + Arrays.hashCode(blobData);
        return result;
    }
}
