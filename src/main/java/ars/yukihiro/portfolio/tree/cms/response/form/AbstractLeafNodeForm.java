package ars.yukihiro.portfolio.tree.cms.response.form;

import ars.yukihiro.portfolio.tree.cms.enums.ContentsType;

import javax.validation.constraints.NotNull;

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
