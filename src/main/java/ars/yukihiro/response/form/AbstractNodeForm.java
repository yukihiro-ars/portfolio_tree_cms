package ars.yukihiro.response.form;

import ars.yukihiro.enums.NodeType;

import javax.validation.constraints.*;

public class AbstractNodeForm {

    private Integer nodeId;

    @NotNull
    private NodeType nodeType;

    @NotNull
    @Max(value = 5)
    @Min(value = 1)
    private Short hierarchy;

    @NotBlank
    private String nodeNmLgc;

    @NotBlank
    @Pattern(regexp = "^$|^[a-zA-Z0-9]+$", message = "{javax.validation.constraints.Pattern.azAZ09.message}")
    private String nodeNmPsc;

    private Integer contentsId;

    private Long nodeVersion;

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

    public Long getNodeVersion() { return nodeVersion; }

    public void setNodeVersion(Long nodeVersion) { this.nodeVersion = nodeVersion; }

}
