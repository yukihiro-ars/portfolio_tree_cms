package ars.yukihiro.form;

import ars.yukihiro.constants.NodeType;
import ars.yukihiro.entity.Node;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class NodeForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String MSG = "半角英数字で入力してください";

    private String nodeId;
    @NotNull
    private NodeType nodeType;
    @NotNull
    private short hierarchy;
    @NotNull
    private String nodeNmLgc;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = MSG)
    private String nodeNmPsc;
//    @NotNull
//    @Pattern(regexp = "^[0-9]+$")
    private String contentsId;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public NodeType getNodeType() {
        return nodeType;
    }
    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public short getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(short hierarchy) {
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

    public String getContentsId() {
        return contentsId;
    }

    public void setContentsId(String contentsId) {
        this.contentsId = contentsId;
    }
}
