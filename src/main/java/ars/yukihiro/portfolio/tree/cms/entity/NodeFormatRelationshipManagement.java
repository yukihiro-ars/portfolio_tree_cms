package ars.yukihiro.portfolio.tree.cms.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "node_format_relationship_management", schema = "business", catalog = "ars")
@IdClass(NodeFormatRelationshipManagementPK.class)
public class NodeFormatRelationshipManagement {
    private String formatType;
    private String nodeId;
    private String viewableFlg;
    private Short sortOrder;
    private Timestamp upDt;
    private String upNm;
    private Timestamp rgDt;
    private String rgNm;
    private long version;

    @Id
    @Column(name = "format_type", nullable = false, length = -1)
    public String getFormatType() {
        return formatType;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    @Id
    @Column(name = "node_id", nullable = false, length = 32)
    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @Basic
    @Column(name = "viewable_flg", nullable = false, length = -1)
    public String getViewableFlg() {
        return viewableFlg;
    }

    public void setViewableFlg(String viewableFlg) {
        this.viewableFlg = viewableFlg;
    }

    @Basic
    @Column(name = "sort_order", nullable = true)
    public Short getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Short sortOrder) {
        this.sortOrder = sortOrder;
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
        NodeFormatRelationshipManagement that = (NodeFormatRelationshipManagement) o;
        return version == that.version &&
                Objects.equals(formatType, that.formatType) &&
                Objects.equals(nodeId, that.nodeId) &&
                Objects.equals(viewableFlg, that.viewableFlg) &&
                Objects.equals(sortOrder, that.sortOrder) &&
                Objects.equals(upDt, that.upDt) &&
                Objects.equals(upNm, that.upNm) &&
                Objects.equals(rgDt, that.rgDt) &&
                Objects.equals(rgNm, that.rgNm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formatType, nodeId, viewableFlg, sortOrder, upDt, upNm, rgDt, rgNm, version);
    }
}
