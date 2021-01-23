package ars.yukihiro.form;

import ars.yukihiro.enums.NodeType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Node用のフォーム
 * @atuher yukihiro adachi
 */
public class NodeForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer nodeId;
    @NotNull
    private NodeType nodeType;
    @NotNull
    @Max(value = 5)
    @Min(value = 1)
    private Short hierarchy;
    @NotNull
    private String nodeNmLgc;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{javax.validation.constraints.Pattern.azAZ09.message}")
    private String nodeNmPsc;
    private Integer contentsId;
    private Long version;

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public NodeType getNodeType() {
        return nodeType;
    }
    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public Short getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Short hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getNodeNmLgc() {
        return nodeNmLgc;
    }

    public void setNodeNmLgc(String nodeNmLgc) {
        this.nodeNmLgc = nodeNmLgc;
    }

    public String getNodeNmPsc() {
        return nodeNmPsc;
    }

    public void setNodeNmPsc(String nodeNmPsc) {
        this.nodeNmPsc = nodeNmPsc;
    }

    public Integer getContentsId() {
        return contentsId;
    }

    public void setContentsId(Integer contentsId) {
        this.contentsId = contentsId;
    }

    public Long getVersion() { return version; }

    public void setVersion(Long version) { this.version = version; }

}
