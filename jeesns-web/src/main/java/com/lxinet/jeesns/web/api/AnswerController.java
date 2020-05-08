package com.lxinet.jeesns.web.api;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
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
 * @author zhangchuanzhao
 * @date 2020/5/8 12:46
 */
@RestController("apiAnswerController")
@RequestMapping("/api/question/{questionId}/answer/")
public class AnswerController extends BaseController {
    @Resource
    private JeesnsConfig jeesnsConfig;
    @Resource
    private AnswerService answerService;


    @PostMapping(value="commit")
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result commit(@PathVariable("questionId") Integer questionId, Answer answer) {
        Member loginMember = MemberUtil.getLoginMember(request);
        answer.setMemberId(loginMember.getId());
        answer.setQuestionId(questionId);
        answerService.save(answer);
        Result result = new Result(0);
        result.setData(answer.getId());
        return result;
    }

}
