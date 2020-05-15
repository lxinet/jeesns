package cn.jeesns.web.front;

import cn.jeesns.interceptor.UserLoginInterceptor;
import cn.jeesns.model.member.Member;
import cn.jeesns.model.question.Answer;
import cn.jeesns.utils.MemberUtil;
import cn.jeesns.core.annotation.Before;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.utils.JeesnsConfig;
import cn.jeesns.service.question.AnswerService;
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
