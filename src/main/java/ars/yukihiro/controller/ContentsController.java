package ars.yukihiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("contents")
public class ContentsController {

    @RequestMapping(method = RequestMethod.GET)
    public String view(){
        System.out.println("contents");
        return "contents";
    }
}
