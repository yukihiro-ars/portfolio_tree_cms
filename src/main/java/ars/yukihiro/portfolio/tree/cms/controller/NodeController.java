package ars.yukihiro.portfolio.tree.cms.controller;

import ars.yukihiro.portfolio.tree.cms.enums.ContentsType;
import ars.yukihiro.portfolio.tree.cms.enums.NodeType;
import ars.yukihiro.portfolio.tree.cms.response.form.BranchForm;
import ars.yukihiro.portfolio.tree.cms.response.form.LeafPlaneForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * node登録画面表示用コントローラ.
 * @auther yukihiro adachi
 */
@Controller
@RequestMapping("node")
public class NodeController {

    private static final Logger logger =
            LoggerFactory.getLogger(NodeController.class);

    // TODO URLでparentNodeIdの引継ぎが必要、各post処理内でrelationを紐づけるためにも必要。
    @RequestMapping(path = {"/{parentNodeId}"}, method = RequestMethod.GET)
    public ModelAndView doGet(
            @PathVariable Integer parentNodeId,
            ModelAndView mv) {

        BranchForm nodeForm = new BranchForm();
        nodeForm.setParentNodeId(parentNodeId);
        nodeForm.setNodeType(NodeType.BRANCH);
        mv.addObject("nodeForm", nodeForm);

        LeafPlaneForm planeForm = new LeafPlaneForm();
        planeForm.setContentsType(ContentsType.PLANE);
        mv.addObject("planeForm", planeForm);

        mv.setViewName("node");
        return mv;
    }
}
