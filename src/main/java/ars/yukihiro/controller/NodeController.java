package ars.yukihiro.controller;

import ars.yukihiro.constants.NodeType;
import ars.yukihiro.form.NodeForm;
import ars.yukihiro.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

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
        System.out.println(nodeService.findNode("1234567890").getNodeNmLgc());
        NodeForm form = new NodeForm();
        form.setNodeType(NodeType.LEAF);
        form.setNodeNmPsc("NODENAMELGC");
        model.addAttribute("nodeForm", form);
        System.out.println("doGet");
        return "node";
    }

    @RequestMapping(method = RequestMethod.POST)
    public void doPost(@Validated NodeForm form,
                       BindingResult result,
                       Model model) {

        if (result.hasErrors()) {
            System.out.println("doPost hasError");
            List<FieldError> errors = result.getFieldErrors();
            for(FieldError error : errors) {
                System.out.println(error.getDefaultMessage());
            }
        } else {
            // do some process
            System.out.println("doPost corrected");
        }

    }
}
