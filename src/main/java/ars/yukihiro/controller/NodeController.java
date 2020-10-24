package ars.yukihiro.controller;

import ars.yukihiro.form.NodeForm;
import ars.yukihiro.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("node")
@SessionAttributes(types = NodeForm.class)
public class NodeController {
    @Autowired
    private NodeService nodeService;

    @RequestMapping(method = RequestMethod.GET)
    public String view(){
        System.out.println(nodeService.findNode("1234567890").getNodeNmLgc());
        return "node";
    }

    @RequestMapping(path = "confirm", method = RequestMethod.GET)
    public void confirm() {

    }
    @RequestMapping(path = "complete", method = RequestMethod.GET)
    public void complete() {

    }
}
