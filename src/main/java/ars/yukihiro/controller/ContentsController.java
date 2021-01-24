package ars.yukihiro.controller;

import ars.yukihiro.exception.ResourceNotFoundException;
import ars.yukihiro.response.form.ContentsForm;
import ars.yukihiro.message.ApplicationMessageBundle;
import ars.yukihiro.enums.ApplicationMessageId;
import ars.yukihiro.service.ContentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * contents管理コントローラ.
 * @auther yukihiro adachi
 */
@Controller
@RequestMapping("contents")
public class ContentsController {

    private static final Logger logger =
            LoggerFactory.getLogger(ContentsController.class);

    @Autowired
    private ContentsService contentsService;

    @RequestMapping(path = {"/", "/{contentsId}"}, method = RequestMethod.GET)
    public ModelAndView doGet(
            @PathVariable Optional<Integer> contentsId,
            ModelAndView mv) {
        try {
            ContentsForm form;
            if (contentsId.isEmpty()) {
                // 新規
                form = new ContentsForm();
            } else {
                // 編集
                form = contentsService.getContentsForm(contentsId.get());
                if (form == null) {
                    throw new ResourceNotFoundException(
                            String.format("contentsId:%s",  contentsId.get()));
                }
            }
            mv.addObject("contentsForm", form);
            mv.setViewName("contents");
            return mv;
        } catch (Exception e) {
            logger.error(
                    ApplicationMessageBundle.getMessage(
                            ApplicationMessageId.SYS_E_01), e);
            throw e;
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> doPost(@Validated ContentsForm form,
                                                      BindingResult result) {

        Map<String, Object> responseBody = new HashMap<>();

        if (result.hasErrors()) {
            for(FieldError error : result.getFieldErrors()) {
                responseBody.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(responseBody);
        } else {
            try {
                contentsService.upsertContentsByForm(form);
                responseBody.put("message",
                        ApplicationMessageBundle.getMessage(
                                ApplicationMessageId.SYS_I_01, "登録／更新"));
                return ResponseEntity.ok(responseBody);
            } catch (Exception e) {
                responseBody.put("message",
                        ApplicationMessageBundle.getMessage(ApplicationMessageId.SYS_E_01));
                logger.error(
                        ApplicationMessageBundle.getMessage(
                                ApplicationMessageId.SYS_E_01), e);
                return ResponseEntity.badRequest().body(responseBody);
            }
        }
    }
}
