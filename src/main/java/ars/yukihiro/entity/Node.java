package ars.yukihiro.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Node {
    private String nodeId;
    private String nodeType;
    private String nodeNmLgc;
    private String nodeNmPsc;
    private String contentsId;
    private String upDt;
    private String upNm;
    private String rgDt;
    private String rgNm;

    @Id
    @Column(name = "node_id", nullable = false, length = 10)
    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @Basic
    @Column(name = "node_type", nullable = false, length = -1)
    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Basic
    @Column(name = "node_nm_lgc", nullable = false, length = 20)
    public String getNodeNmLgc() {
        return nodeNmLgc;
    }

    public void setNodeNmLgc(String nodeNmLgc) {
        this.nodeNmLgc = nodeNmLgc;
    }

    @Basic
    @Column(name = "node_nm_psc", nullable = false, length = 20)
    public String getNodeNmPsc() {
        return nodeNmPsc;
    }

    public void setNodeNmPsc(String nodeNmPsc) {
        this.nodeNmPsc = nodeNmPsc;
    }

    @Basic
    @Column(name = "contents_id", nullable = true, length = 10)
    public String getContentsId() {
        return contentsId;
    }

    public void setContentsId(String contentsId) {
        this.contentsId = contentsId;
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

        Node node = (Node) o;

        if (nodeId != null ? !nodeId.equals(node.nodeId) : node.nodeId != null) return false;
        if (nodeType != null ? !nodeType.equals(node.nodeType) : node.nodeType != null) return false;
        if (nodeNmLgc != null ? !nodeNmLgc.equals(node.nodeNmLgc) : node.nodeNmLgc != null) return false;
        if (nodeNmPsc != null ? !nodeNmPsc.equals(node.nodeNmPsc) : node.nodeNmPsc != null) return false;
        if (contentsId != null ? !contentsId.equals(node.contentsId) : node.contentsId != null) return false;
        if (upDt != null ? !upDt.equals(node.upDt) : node.upDt != null) return false;
        if (upNm != null ? !upNm.equals(node.upNm) : node.upNm != null) return false;
        if (rgDt != null ? !rgDt.equals(node.rgDt) : node.rgDt != null) return false;
        if (rgNm != null ? !rgNm.equals(node.rgNm) : node.rgNm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nodeId != null ? nodeId.hashCode() : 0;
        result = 31 * result + (nodeType != null ? nodeType.hashCode() : 0);
        result = 31 * result + (nodeNmLgc != null ? nodeNmLgc.hashCode() : 0);
        result = 31 * result + (nodeNmPsc != null ? nodeNmPsc.hashCode() : 0);
        result = 31 * result + (contentsId != null ? contentsId.hashCode() : 0);
        result = 31 * result + (upDt != null ? upDt.hashCode() : 0);
        result = 31 * result + (upNm != null ? upNm.hashCode() : 0);
        result = 31 * result + (rgDt != null ? rgDt.hashCode() : 0);
        result = 31 * result + (rgNm != null ? rgNm.hashCode() : 0);
        return result;
    }
}
