package com.lxinet.jeesns.web.api;

import com.lxinet.jeesns.core.annotation.UsePage;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.question.Answer;
import com.lxinet.jeesns.model.question.Question;
import com.lxinet.jeesns.model.question.QuestionType;
import com.lxinet.jeesns.service.question.AnswerService;
import com.lxinet.jeesns.service.question.QuestionService;
import com.lxinet.jeesns.service.question.QuestionTypeService;
import com.lxinet.jeesns.utils.JwtUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangchuanzhao
 * @date 2020/5/8 12:46
 */
@RestController("apiQuestionController")
@RequestMapping("/api/question/")
public class QuestionController extends BaseController {
    @Resource
    private JeesnsConfig jeesnsConfig;
    @Resource
    private QuestionTypeService questionTypeService;
    @Resource
    private QuestionService questionService;
    @Resource
    private AnswerService answerService;
    @Resource
    private JwtUtil jwtUtil;

    @UsePage
    @GetMapping(value={"/","list","list-{statusName}"})
    public String list(String key, @RequestParam(value = "tid",defaultValue = "0",required = false) Integer typeId,
                       @RequestParam(value = "memberId",defaultValue = "0",required = false) Integer memberId,
                       @PathVariable(value = "statusName", required = false) String statusName,
                       Model model) {
        Result<Question> result = questionService.list(typeId, statusName);
        List<QuestionType> questionTypeList = questionTypeService.listAll();
        model.addAttribute("model", result);
        model.addAttribute("questionTypeList", questionTypeList);
        model.addAttribute("statusName", statusName);
        return jeesnsConfig.getFrontTemplate() + "/question/list";
    }

    @UsePage
    @GetMapping(value="detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model){
        Member loginMember = jwtUtil.getMember(request);
        Question question = questionService.findById(id);
        Answer bestAnswer = answerService.findById(question.getAnswerId());
        Result answerModel = answerService.listByQuestion(id);
        List<QuestionType> questionTypeList = questionTypeService.listAll();
        //更新访问次数
        questionService.updateViewCount(id);
        model.addAttribute("question",question);
        model.addAttribute("loginUser",loginMember);
        model.addAttribute("bestAnswer",bestAnswer);
        model.addAttribute("answerModel",answerModel);
        model.addAttribute("questionTypeList", questionTypeList);
        return jeesnsConfig.getFrontTemplate() + "/question/detail";
    }

    @GetMapping(value="ask")
    public String ask(Model model) {
        List<QuestionType> list = questionTypeService.listAll();
        model.addAttribute("questionTypeList",list);
        return jeesnsConfig.getFrontTemplate() + "/question/ask";
    }

    @PostMapping(value="ask")
    public Result ask(Question question) {
        Member loginMember = jwtUtil.getMember(request);
        question.setMemberId(loginMember.getId());
        questionService.save(question);
        Result result = new Result(0);
        result.setData(question.getId());
        return result;
    }

    @GetMapping(value="/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        Question question = questionService.findById(id);
        model.addAttribute("question",question);
        return jeesnsConfig.getFrontTemplate() + "/question/edit";
    }

    @PostMapping(value="/update")
    public Result update(Question question) {
        Member loginMember = jwtUtil.getMember(request);
        Result result = new Result(questionService.update(loginMember,question));
        result.setData(question.getId());
        return result;
    }


    @GetMapping(value="delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        Member loginMember = jwtUtil.getMember(request);
        Result result = new Result(questionService.delete(loginMember, id));
        return result;
    }

    @GetMapping(value="close/{id}")
    public Result close(@PathVariable("id") Integer id){
        Member loginMember = jwtUtil.getMember(request);
        questionService.close(loginMember, id);
        return new Result(0);
    }

    @GetMapping(value="bestAnswer/{id}/{answerId}")
    public Result bestAnswer(@PathVariable("id") Integer id, @PathVariable("answerId") Integer answerId){
        Member loginMember = jwtUtil.getMember(request);
        questionService.bestAnswer(loginMember, answerId, id);
        return new Result(0);
    }

}
