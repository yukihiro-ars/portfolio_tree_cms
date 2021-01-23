package ars.yukihiro.controller;

import ars.yukihiro.enums.NodeType;
import ars.yukihiro.exception.ResourceNotFoundException;
import ars.yukihiro.form.NodeForm;
import ars.yukihiro.message.SystemMessageBundle;
import ars.yukihiro.enums.SystemMessageId;
import ars.yukihiro.service.NodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView doGet(
            @RequestParam(required = false, name = "nodeId") Integer nodeId,
            ModelAndView mv) {
        try {
            NodeForm form;
            if (nodeId == null) {
                // 新規
                form = new NodeForm();
                // 初期値
                form.setNodeType(NodeType.INNER);
            } else {
                // 編集
                form = nodeService.getNodeForm(nodeId);
                if (form == null) {
                    throw new ResourceNotFoundException(
                            String.format("nodeId:%s", nodeId));
                }
            }
            mv.addObject("nodeForm", form);
            mv.setViewName("node");
            return mv;
        } catch (Exception e) {
            logger.error(
                    SystemMessageBundle.getMessage(
                            SystemMessageId.SYS_E_01), e);
            throw e;
        }
    }

    @ResponseBody
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
                nodeService.upsertNodeByForm(form);
                responseBody.put("message",
                        SystemMessageBundle.getMessage(
                                SystemMessageId.SYS_I_01, "登録／更新"));
                return ResponseEntity.ok(responseBody);
            } catch (Exception e) {
                responseBody.put("message",
                        SystemMessageBundle.getMessage(SystemMessageId.SYS_E_01));
                logger.error(
                        SystemMessageBundle.getMessage(
                                SystemMessageId.SYS_E_01), e);
                return ResponseEntity.badRequest().body(responseBody);
            }
        }
    }
}
