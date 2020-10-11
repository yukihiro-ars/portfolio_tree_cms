package ars.yukihiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("node")
public class NodeController {

    @RequestMapping(method = RequestMethod.GET)
    public String view(){
        System.out.println("node");
        return "node";
    }
}
