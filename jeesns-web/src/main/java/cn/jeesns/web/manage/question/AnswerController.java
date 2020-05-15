package cn.jeesns.web.manage.question;

import cn.jeesns.model.question.Answer;
import cn.jeesns.model.question.Question;
import cn.jeesns.core.annotation.UsePage;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.utils.JeesnsConfig;
import cn.jeesns.service.question.AnswerService;
import cn.jeesns.service.question.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 后台问题回答Controller
 * Created by zchuanzhao on 2018/12/24.
 */
@Controller("answerController")
@RequestMapping("${jeesns.managePath}/question/{questionId}/answer/")
public class AnswerController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/question/answer/";
    @Resource
    private JeesnsConfig jeesnsConfig;
    @Resource
    private AnswerService answerService;
    @Resource
    private QuestionService questionService;


    @UsePage
    @GetMapping(value="list")
    public String list(@PathVariable("questionId") Integer questionId, Model model) {
        Question question = questionService.findById(questionId);
        Answer bestAnswer = answerService.findById(question.getAnswerId());
        Result answerModel = answerService.listByQuestion(questionId);
        model.addAttribute("question",question);
        model.addAttribute("bestAnswer",bestAnswer);
        model.addAttribute("model",answerModel);
        return MANAGE_FTL_PATH + "list";
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable("id") Integer id){
        return new Result(answerService.deleteById(id));
    }
}
