package com.lxinet.jeesns.web.manage.question;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.annotation.UsePage;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.question.Answer;
import com.lxinet.jeesns.model.question.Question;
import com.lxinet.jeesns.service.question.IAnswerService;
import com.lxinet.jeesns.service.question.IQuestionService;
import com.lxinet.jeesns.utils.MemberUtil;
import com.lxinet.jeesns.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 后台问题回答Controller
 * Created by zchuanzhao on 2018/12/24.
 */
@Controller("answerController")
@RequestMapping("${managePath}/question/{questionId}/answer/")
public class AnswerController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/question/answer/";
    @Resource
    private JeesnsConfig jeesnsConfig;
    @Resource
    private IAnswerService answerService;
    @Resource
    private IQuestionService questionService;


    @UsePage
    @GetMapping(value="list")
    public String list(@PathVariable("questionId") Integer questionId, Model model) {
        Question question = questionService.findById(questionId);
        Answer bestAnswer = answerService.findById(question.getAnswerId());
        ResultModel answerModel = answerService.listByQuestion(questionId);
        model.addAttribute("question",question);
        model.addAttribute("bestAnswer",bestAnswer);
        model.addAttribute("model",answerModel);
        return MANAGE_FTL_PATH + "list";
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    public ResultModel delete(@PathVariable("id") Integer id){
        return new ResultModel(answerService.deleteById(id));
    }
}
