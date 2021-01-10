package ars.yukihiro.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "node_relationship", schema = "business", catalog = "ars")
@IdClass(NodeRelationshipPK.class)
public class NodeRelationship implements Serializable {
    private String parentNodeId;
    private String childNodeId;
    private short sort;
    private Timestamp upDt;
    private String upNm;
    private Timestamp rgDt;
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
    @Column(name = "sort", nullable = false)
    public short getSort() {
        return sort;
    }

    public void setSort(short sort) {
        this.sort = sort;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeRelationship that = (NodeRelationship) o;
        return sort == that.sort &&
                Objects.equals(parentNodeId, that.parentNodeId) &&
                Objects.equals(childNodeId, that.childNodeId) &&
                Objects.equals(upDt, that.upDt) &&
                Objects.equals(upNm, that.upNm) &&
                Objects.equals(rgDt, that.rgDt) &&
                Objects.equals(rgNm, that.rgNm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentNodeId, childNodeId, sort, upDt, upNm, rgDt, rgNm);
    }
}
