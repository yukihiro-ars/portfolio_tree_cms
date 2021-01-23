package ars.yukihiro.form;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Contents用のフォーム
 * @atuher yukihiro adachi
 */
public class ContentsForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer contentsId;
    @NotNull
    private String contentsVal;
    private Long version;

    public Integer getContentsId() {
        return contentsId;
    }

    public void setContentsId(Integer contentsId) {
        this.contentsId = contentsId;
    }

    public String getContentsVal() {
        return contentsVal;
    }

    public void setContentsVal(String contentsVal) {
        this.contentsVal = contentsVal;
    }

    public Long getVersion() { return version; }

    public void setVersion(Long version) { this.version = version; }

}
