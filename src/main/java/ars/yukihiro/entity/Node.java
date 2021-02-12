package ars.yukihiro.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(schema = "business")
public class Node implements Serializable, IAdminEntity {
    private int nodeId;
    private String nodeType;
    private short hierarchy;
    private String nodeNmLgc;
    private String nodeNmPsc;
    private Integer contentsId;
    private Timestamp upDt;
    private String upNm;
    private Timestamp rgDt;
    private String rgNm;
    private long version;

    @Id
    @Column(name = "node_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
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
    @Column(name = "hierarchy", nullable = false)
    public short getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(short hierarchy) {
        this.hierarchy = hierarchy;
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
    @Column(name = "contents_id", nullable = true)
    public Integer getContentsId() {
        return contentsId;
    }

    public void setContentsId(Integer contentsId) {
        this.contentsId = contentsId;
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
        Node node = (Node) o;
        return nodeId == node.nodeId &&
                hierarchy == node.hierarchy &&
                version == node.version &&
                Objects.equals(nodeType, node.nodeType) &&
                Objects.equals(nodeNmLgc, node.nodeNmLgc) &&
                Objects.equals(nodeNmPsc, node.nodeNmPsc) &&
                Objects.equals(contentsId, node.contentsId) &&
                Objects.equals(upDt, node.upDt) &&
                Objects.equals(upNm, node.upNm) &&
                Objects.equals(rgDt, node.rgDt) &&
                Objects.equals(rgNm, node.rgNm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId, nodeType, hierarchy, nodeNmLgc, nodeNmPsc, contentsId, upDt, upNm, rgDt, rgNm, version);
    }
}
