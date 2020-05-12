package com.lxinet.jeesns.web.manage.question;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.annotation.UsePage;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.question.Question;
import com.lxinet.jeesns.model.question.QuestionType;
import com.lxinet.jeesns.service.question.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2018/12/7.
 */
@Controller
@RequestMapping("${jeesns.managePath}/question/")
@Before(AdminLoginInterceptor.class)
public class QuestionController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/question/";
    @Resource
    private QuestionService questionService;

    @GetMapping({"list","list-{statusName}"})
    @UsePage
    public String list(Model model, @RequestParam(value = "tid",defaultValue = "0",required = false) Integer typeId,
                       @PathVariable(value = "statusName", required = false) String statusName){
        Result<Question> result = questionService.list(typeId, statusName);
        model.addAttribute("model",result);
        model.addAttribute("statusName",statusName);
        return MANAGE_FTL_PATH + "list";
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable("id") Integer id){
        return new Result(questionService.deleteById(id));
    }
}
