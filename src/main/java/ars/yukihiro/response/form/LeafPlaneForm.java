package ars.yukihiro.response.form;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class LeafPlaneForm extends AbstractLeafNodeForm implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull
    private String contentsVal;

    private Long contentsPlaneVersion;

    public String getContentsVal() { return contentsVal; }

    public void setContentsVal(String contentsVal) {
        this.contentsVal = contentsVal;
    }

    public Long getContentsPlaneVersion() {
        return contentsPlaneVersion;
    }

    public void setContentsPlaneVersion(Long contentsPlaneVersion) {
        this.contentsPlaneVersion = contentsPlaneVersion;
    }
}
