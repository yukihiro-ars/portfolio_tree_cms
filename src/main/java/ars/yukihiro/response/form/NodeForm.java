package ars.yukihiro.response.form;

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
public class NodeForm extends AbstractNodeForm implements Serializable {
    private static final long serialVersionUID = 1L;
}
