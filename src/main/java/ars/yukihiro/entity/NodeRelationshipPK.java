package ars.yukihiro.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

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
        return Objects.equals(parentNodeId, that.parentNodeId) &&
                Objects.equals(childNodeId, that.childNodeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentNodeId, childNodeId);
    }
}
