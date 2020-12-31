package ars.yukihiro.controller;

import ars.yukihiro.constants.NodeType;
import ars.yukihiro.form.NodeForm;
import ars.yukihiro.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * node管理用のコントローラ.
 * @auther yukihiro adachi
 */
@Controller
@RequestMapping("node")
@SessionAttributes(types = NodeForm.class)
public class NodeController {
    @Autowired
    private NodeService nodeService;

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(Model model){
        // TODO @RequestParam属性から取得するか？
        // TODO NodeIdの指定がある場合、DBよりデータを取得する
        // TODO NodeIdが指定済み、かつ取得結果0件の場合は、不正なNodeId
        try {
            model.addAttribute(
                    "nodeForm",
                    nodeService.getNodeForm("1234567890"));
        } catch (Exception e) {
            // TODO エラー時の処理 400 BadRequest
        }

        return "node";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> doPost(@Validated NodeForm form,
                                                            BindingResult result,
                                                            Model model) {

        Map<String, Object> responseBody = new HashMap<String, Object>();

        if (result.hasErrors()) {
            for(FieldError error : result.getFieldErrors()) {
                responseBody.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(responseBody);
        } else {
            try {
                nodeService.updateNodeByForm(form);
                responseBody.put("message", "正常に更新処理が完了しました。");
                return ResponseEntity.ok(responseBody);
            } catch (Exception e) {
                e.printStackTrace();
                responseBody.put("message", "想定外の例外が発生しました。");
                return ResponseEntity.badRequest().body(responseBody);
            }

        }
    }
}
