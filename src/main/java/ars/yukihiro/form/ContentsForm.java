package ars.yukihiro.form;

import ars.yukihiro.constants.NodeType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

}
