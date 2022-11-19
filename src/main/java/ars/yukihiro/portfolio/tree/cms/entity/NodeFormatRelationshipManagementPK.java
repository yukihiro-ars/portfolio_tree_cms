package ars.yukihiro.portfolio.tree.cms.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class NodeFormatRelationshipManagementPK implements Serializable {
    private String formatType;
    private String nodeId;

    @Column(name = "format_type", nullable = false, length = -1)
    @Id
    public String getFormatType() {
        return formatType;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    @Column(name = "node_id", nullable = false, length = 32)
    @Id
    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeFormatRelationshipManagementPK that = (NodeFormatRelationshipManagementPK) o;
        return Objects.equals(formatType, that.formatType) &&
                Objects.equals(nodeId, that.nodeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formatType, nodeId);
    }
}
