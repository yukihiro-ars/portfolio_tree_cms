package ars.yukihiro.controller;

import ars.yukihiro.form.NodeForm;
import ars.yukihiro.message.SystemMessageBundle;
import ars.yukihiro.message.SystemMessageConstants;
import ars.yukihiro.service.NodeService;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Map;

/**
 * node管理用のコントローラ.
 * @auther yukihiro adachi
 */
@Controller
@RequestMapping("node")
@SessionAttributes(types = NodeForm.class)
public class NodeController {

    private static final Logger logger =
            LoggerFactory.getLogger(NodeController.class);

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
            logger.error(
                    SystemMessageBundle.getMessage(
                            SystemMessageConstants.SYS_E_01), e);
            // TODO エラー時の処理 400 BadRequest
        }
        return "node";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> doPost(@Validated NodeForm form,
                                                            BindingResult result,
                                                            Model model) {

        Map<String, Object> responseBody = new HashMap<>();

        if (result.hasErrors()) {
            for(FieldError error : result.getFieldErrors()) {
                responseBody.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(responseBody);
        } else {
            try {
                nodeService.updateNodeByForm(form);
                responseBody.put("message",
                        SystemMessageBundle.getMessage(
                                SystemMessageConstants.SYS_I_01, "登録／更新"));
                return ResponseEntity.ok(responseBody);
            } catch (Exception e) {
                responseBody.put("message",
                        SystemMessageBundle.getMessage(SystemMessageConstants.SYS_E_01));
                logger.error(
                        SystemMessageBundle.getMessage(
                                SystemMessageConstants.SYS_E_01), e);
                return ResponseEntity.badRequest().body(responseBody);
            }
        }
    }
}
