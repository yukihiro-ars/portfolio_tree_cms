package ars.yukihiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("tree")
public class TreeController {

    @RequestMapping(method = RequestMethod.GET)
    public String view(){
        System.out.println("tree");
        return "tree";
    }
}
