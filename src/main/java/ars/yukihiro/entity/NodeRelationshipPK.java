package ars.yukihiro.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class NodeRelationshipPK implements Serializable {
    private int parentNodeId;
    private int childNodeId;

    @Column(name = "parent_node_id", nullable = false)
    @Id
    public int getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(int parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    @Column(name = "child_node_id", nullable = false)
    @Id
    public int getChildNodeId() {
        return childNodeId;
    }

    public void setChildNodeId(int childNodeId) {
        this.childNodeId = childNodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeRelationshipPK that = (NodeRelationshipPK) o;
        return parentNodeId == that.parentNodeId &&
                childNodeId == that.childNodeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentNodeId, childNodeId);
    }
}
