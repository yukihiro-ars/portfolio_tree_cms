package ars.yukihiro.controller;

import ars.yukihiro.service.TreeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger logger =
            LoggerFactory.getLogger(TreeController.class);

    @Autowired
    private TreeService treeService;

    @RequestMapping(method = RequestMethod.GET)
    public String view(){
        // TODO dummy code
        treeService.getTree();
        return "tree";
    }
}
