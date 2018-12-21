package com.lxinet.jeesns.web.manage.question;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.annotation.UsePage;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.enums.Messages;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.question.QuestionType;
import com.lxinet.jeesns.service.question.IQuestionService;
import com.lxinet.jeesns.service.question.IQuestionTypeService;
import com.lxinet.jeesns.web.common.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2018/12/7.
 */
@Controller
@RequestMapping("${managePath}/question/")
@Before(AdminLoginInterceptor.class)
public class QuestionController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/question/";
    @Resource
    private IQuestionService questionService;

    @GetMapping("list")
    @UsePage
    public String list(Model model){
        ResultModel<QuestionType> resultModel = questionService.list();
        model.addAttribute("resultModel",resultModel);
        return MANAGE_FTL_PATH + "list";
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    public ResultModel delete(@PathVariable("id") Integer id){
        return new ResultModel(questionService.deleteById(id));
    }
}
