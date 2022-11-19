package ars.yukihiro.portfolio.tree.cms.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "node_management", schema = "business", catalog = "ars")
public class NodeManagement {
    private String nodeId;
    private String parentNodeId;
    private String key;
    private String value;
    private String blobFlg;
    private Timestamp upDt;
    private String upNm;
    private Timestamp rgDt;
    private String rgNm;
    private long version;

    @Id
    @Column(name = "node_id", nullable = false, length = 32)
    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @Basic
    @Column(name = "parent_node_id", nullable = false, length = 32)
    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    @Basic
    @Column(name = "key", nullable = false, length = 128)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Basic
    @Column(name = "value", nullable = true, length = 256)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "blob_flg", nullable = false, length = -1)
    public String getBlobFlg() {
        return blobFlg;
    }

    public void setBlobFlg(String blobFlg) {
        this.blobFlg = blobFlg;
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
        NodeManagement that = (NodeManagement) o;
        return version == that.version &&
                Objects.equals(nodeId, that.nodeId) &&
                Objects.equals(parentNodeId, that.parentNodeId) &&
                Objects.equals(key, that.key) &&
                Objects.equals(value, that.value) &&
                Objects.equals(blobFlg, that.blobFlg) &&
                Objects.equals(upDt, that.upDt) &&
                Objects.equals(upNm, that.upNm) &&
                Objects.equals(rgDt, that.rgDt) &&
                Objects.equals(rgNm, that.rgNm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId, parentNodeId, key, value, blobFlg, upDt, upNm, rgDt, rgNm, version);
    }
}
