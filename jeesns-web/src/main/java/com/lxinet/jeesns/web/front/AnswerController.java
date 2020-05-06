package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.question.Answer;
import com.lxinet.jeesns.service.question.AnswerService;
import com.lxinet.jeesns.utils.MemberUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 前台问题回答Controller
 * Created by zchuanzhao on 2018/12/21.
 */
@Controller("frontAnswerController")
@RequestMapping("/question/{questionId}/answer/")
public class AnswerController extends BaseController {
    @Resource
    private JeesnsConfig jeesnsConfig;
    @Resource
    private AnswerService answerService;


    @RequestMapping(value="commit",method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public ResultModel commit(@PathVariable("questionId") Integer questionId, Answer answer) {
        Member loginMember = MemberUtil.getLoginMember(request);
        answer.setMemberId(loginMember.getId());
        answer.setQuestionId(questionId);
        answerService.save(answer);
        ResultModel resultModel = new ResultModel(0);
        resultModel.setData(answer.getId());
        return resultModel;
    }

}
