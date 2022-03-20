package ars.yukihiro.portfolio.tree.cms.response.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class TreeJson {

    @JsonProperty("nodeId")
    private Integer nodeId;

    @JsonProperty("text")
    private String nodeName;

    /**
     * type nodeType {nodeType}
     * type contentsType {nodeType}_{contentsType}
     */
    @JsonProperty("type")
    private String type;

    @JsonProperty("children")
    private List<TreeJson> children = new ArrayList<>();

    public TreeJson(Integer n) {
        nodeId = n;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public List<TreeJson> getChildren() {
        return children;
    }

    public void setChildren(List<TreeJson> children) {
        this.children = children;
    }
}
