package ars.yukihiro.entity;

import javax.persistence.*;

@Entity
@Table(name = "node_relationship", schema = "business", catalog = "ars")
@IdClass(NodeRelationshipPK.class)
public class NodeRelationship {
    private String parentNodeId;
    private String childNodeId;
    private String upDt;
    private String upNm;
    private String rgDt;
    private String rgNm;

    @Id
    @Column(name = "parent_node_id", nullable = false, length = 10)
    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    @Id
    @Column(name = "child_node_id", nullable = false, length = 10)
    public String getChildNodeId() {
        return childNodeId;
    }

    public void setChildNodeId(String childNodeId) {
        this.childNodeId = childNodeId;
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

        NodeRelationship that = (NodeRelationship) o;

        if (parentNodeId != null ? !parentNodeId.equals(that.parentNodeId) : that.parentNodeId != null) return false;
        if (childNodeId != null ? !childNodeId.equals(that.childNodeId) : that.childNodeId != null) return false;
        if (upDt != null ? !upDt.equals(that.upDt) : that.upDt != null) return false;
        if (upNm != null ? !upNm.equals(that.upNm) : that.upNm != null) return false;
        if (rgDt != null ? !rgDt.equals(that.rgDt) : that.rgDt != null) return false;
        if (rgNm != null ? !rgNm.equals(that.rgNm) : that.rgNm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parentNodeId != null ? parentNodeId.hashCode() : 0;
        result = 31 * result + (childNodeId != null ? childNodeId.hashCode() : 0);
        result = 31 * result + (upDt != null ? upDt.hashCode() : 0);
        result = 31 * result + (upNm != null ? upNm.hashCode() : 0);
        result = 31 * result + (rgDt != null ? rgDt.hashCode() : 0);
        result = 31 * result + (rgNm != null ? rgNm.hashCode() : 0);
        return result;
    }
}
