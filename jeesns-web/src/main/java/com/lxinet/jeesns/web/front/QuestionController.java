package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.annotation.UsePage;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.enums.Messages;
import com.lxinet.jeesns.core.exception.NotFountException;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.model.cms.Article;
import com.lxinet.jeesns.model.cms.ArticleCate;
import com.lxinet.jeesns.model.cms.ArticleComment;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.question.Answer;
import com.lxinet.jeesns.model.question.Question;
import com.lxinet.jeesns.model.question.QuestionType;
import com.lxinet.jeesns.service.cms.IArticleCateService;
import com.lxinet.jeesns.service.cms.IArticleCommentService;
import com.lxinet.jeesns.service.cms.IArticleService;
import com.lxinet.jeesns.service.common.IArchiveService;
import com.lxinet.jeesns.service.question.IAnswerService;
import com.lxinet.jeesns.service.question.IQuestionService;
import com.lxinet.jeesns.service.question.IQuestionTypeService;
import com.lxinet.jeesns.utils.MemberUtil;
import com.lxinet.jeesns.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 前台问答Controller
 * Created by zchuanzhao on 2018/12/21.
 */
@Controller("frontQuestionController")
@RequestMapping("/question/")
public class QuestionController extends BaseController {
    @Resource
    private JeesnsConfig jeesnsConfig;
    @Resource
    private IQuestionTypeService questionTypeService;
    @Resource
    private IQuestionService questionService;
    @Resource
    private IAnswerService answerService;

    @UsePage
    @RequestMapping(value={"/","list","list-{statusName}"},method = RequestMethod.GET)
    public String list(String key, @RequestParam(value = "tid",defaultValue = "0",required = false) Integer typeId,
                       @RequestParam(value = "memberId",defaultValue = "0",required = false) Integer memberId,
                       @PathVariable(value = "statusName", required = false) String statusName,
                       Model model) {
        ResultModel<Question> resultModel = questionService.list(typeId, statusName);
        List<QuestionType> questionTypeList = questionTypeService.listAll();
        model.addAttribute("model", resultModel);
        model.addAttribute("questionTypeList", questionTypeList);
        model.addAttribute("statusName", statusName);
        return jeesnsConfig.getFrontTemplate() + "/question/list";
    }

    @UsePage
    @RequestMapping(value="detail/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        Question question = questionService.findById(id);
        Answer bestAnswer = answerService.findById(question.getAnswerId());
        ResultModel answerModel = answerService.listByQuestion(id);
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

    @RequestMapping(value="ask",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String ask(Model model) {
        List<QuestionType> list = questionTypeService.listAll();
        model.addAttribute("questionTypeList",list);
        return jeesnsConfig.getFrontTemplate() + "/question/ask";
    }

    @RequestMapping(value="ask",method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public ResultModel ask(Question question) {
        Member loginMember = MemberUtil.getLoginMember(request);
        question.setMemberId(loginMember.getId());
        questionService.save(question);
        ResultModel resultModel = new ResultModel(0);
        resultModel.setData(question.getId());
        return resultModel;
    }

    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String edit(@PathVariable("id") int id, Model model){
        Question question = questionService.findById(id);
        model.addAttribute("question",question);
        return jeesnsConfig.getFrontTemplate() + "/question/edit";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public ResultModel update(Question question) {
        Member loginMember = MemberUtil.getLoginMember(request);
        ResultModel resultModel = new ResultModel(questionService.update(loginMember,question));
        resultModel.setData(question.getId());
        return resultModel;
    }


    @RequestMapping(value="delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public ResultModel delete(@PathVariable("id") Integer id){
        Member loginMember = MemberUtil.getLoginMember(request);
        ResultModel resultModel = new ResultModel(questionService.delete(loginMember, id));
        return resultModel;
    }

    @RequestMapping(value="close/{id}",method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public ResultModel close(@PathVariable("id") Integer id){
        Member loginMember = MemberUtil.getLoginMember(request);
        questionService.close(loginMember, id);
        return new ResultModel(0);
    }

    @RequestMapping(value="bestAnswer/{id}/{answerId}",method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public ResultModel bestAnswer(@PathVariable("id") Integer id, @PathVariable("answerId") Integer answerId){
        Member loginMember = MemberUtil.getLoginMember(request);
        questionService.bestAnswer(loginMember, answerId, id);
        return new ResultModel(0);
    }

}
