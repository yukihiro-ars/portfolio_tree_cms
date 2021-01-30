package ars.yukihiro.controller;

import ars.yukihiro.enums.ApplicationMessageId;
import ars.yukihiro.message.ApplicationMessageBundle;
import ars.yukihiro.response.form.ContentsForm;
import ars.yukihiro.response.form.LeafContentsForm;
import ars.yukihiro.response.form.NodeForm;
import ars.yukihiro.service.TreeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

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
    public ModelAndView view(ModelAndView mv) {
        mv.addObject("nodeForm", new NodeForm());
        mv.addObject("contentsForm", new LeafContentsForm());
        // TODO append LeafXXFrom
        mv.setViewName("tree");
        return mv;
    }

    @RequestMapping(path = "/{filter}",  method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> doGet(@PathVariable String filter) {

        Map<String, Object> responseBody = new HashMap<>();

        try {
            responseBody.put("data", treeService.getTree());
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            logger.error(
                    ApplicationMessageBundle.getMessage(
                            ApplicationMessageId.SYS_E_01), e);
            throw e;
        }
    }
}
