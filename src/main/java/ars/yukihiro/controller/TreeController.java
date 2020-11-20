package ars.yukihiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * tree管理用のコントローラ
 * @atuher yukihiro adachi
 */
@Controller
@RequestMapping("tree")
public class TreeController {

    @RequestMapping(method = RequestMethod.GET)
    public String view(){
        return "tree";
    }
}
