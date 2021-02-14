package ars.yukihiro.response.form;

import ars.yukihiro.enums.ContentsType;
import ars.yukihiro.enums.NodeType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public abstract class AbstractLeafNodeForm extends AbstractNodeForm {

    @NotNull
    private ContentsType contentsType;
    private Long contentsVersion;

    public ContentsType getContentsType() {
        return contentsType;
    }

    public void setContentsType(ContentsType contentsType) {
        this.contentsType = contentsType;
    }

    public Long getContentsVersion() { return contentsVersion; }

    public void setContentsVersion(Long contentsVersion) { this.contentsVersion = contentsVersion; }
}
