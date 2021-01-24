package ars.yukihiro.response.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class TreeJson {
    @JsonIgnore
    private Integer nodeId;

    @JsonProperty("text")
    private String nodeName;

    @JsonProperty("nodes")
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
        return nodeId  + ":" + "dummy"; // nodeName
    }

    public List<TreeJson> getChildren() {
        return children;
    }

    public void setChildren(List<TreeJson> children) {
        this.children = children;
    }
}
