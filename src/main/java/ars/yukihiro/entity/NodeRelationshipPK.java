package ars.yukihiro.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class NodeRelationshipPK implements Serializable {
    private String parentNodeId;
    private String childNodeId;

    @Column(name = "parent_node_id", nullable = false, length = 10)
    @Id
    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    @Column(name = "child_node_id", nullable = false, length = 10)
    @Id
    public String getChildNodeId() {
        return childNodeId;
    }

    public void setChildNodeId(String childNodeId) {
        this.childNodeId = childNodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeRelationshipPK that = (NodeRelationshipPK) o;

        if (parentNodeId != null ? !parentNodeId.equals(that.parentNodeId) : that.parentNodeId != null) return false;
        if (childNodeId != null ? !childNodeId.equals(that.childNodeId) : that.childNodeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parentNodeId != null ? parentNodeId.hashCode() : 0;
        result = 31 * result + (childNodeId != null ? childNodeId.hashCode() : 0);
        return result;
    }
}
