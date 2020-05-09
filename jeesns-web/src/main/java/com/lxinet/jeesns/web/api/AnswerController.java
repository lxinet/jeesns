package com.lxinet.jeesns.web.api;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.question.Answer;
import com.lxinet.jeesns.service.question.AnswerService;
import com.lxinet.jeesns.utils.JwtUtil;
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
    private AnswerService answerService;
    @Resource
    private JwtUtil jwtUtil;


    @PostMapping(value="commit")
    public Result commit(@PathVariable("questionId") Integer questionId, Answer answer) {
        Member loginMember = jwtUtil.getMember(request);
        answer.setMemberId(loginMember.getId());
        answer.setQuestionId(questionId);
        answerService.save(answer);
        Result result = new Result(0);
        result.setData(answer.getId());
        return result;
    }

}
