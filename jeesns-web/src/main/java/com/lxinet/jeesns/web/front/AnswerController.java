package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.annotation.UsePage;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.question.Answer;
import com.lxinet.jeesns.model.question.Question;
import com.lxinet.jeesns.model.question.QuestionType;
import com.lxinet.jeesns.service.question.IAnswerService;
import com.lxinet.jeesns.service.question.IQuestionService;
import com.lxinet.jeesns.service.question.IQuestionTypeService;
import com.lxinet.jeesns.utils.MemberUtil;
import com.lxinet.jeesns.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    private IAnswerService answerService;
    @Resource
    private IQuestionService questionService;

    @UsePage
    @RequestMapping(value={"/","list"},method = RequestMethod.GET)
    public String list(String key, @RequestParam(value = "tid",defaultValue = "0",required = false) Integer tid,
                       @RequestParam(value = "memberId",defaultValue = "0",required = false) Integer memberId, Model model) {
        ResultModel<Question> resultModel = questionService.list(tid);
        model.addAttribute("model", resultModel);
        return jeesnsConfig.getFrontTemplate() + "/question/list";
    }

    @RequestMapping(value="detail/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        Question question = questionService.findById(id);
        model.addAttribute("question",question);
        model.addAttribute("loginUser",loginMember);
        return jeesnsConfig.getFrontTemplate() + "/question/detail";
    }

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

    /**
     * 评论文章
     * @return
     */
//    @RequestMapping(value="/comment/{articleId}",method = RequestMethod.POST)
//    @ResponseBody
//    @Before(UserLoginInterceptor.class)
//    public ResultModel comment(@PathVariable("articleId") Integer articleId, String content){
//        Member loginMember = MemberUtil.getLoginMember(request);
//        return new ResultModel(articleCommentService.save(loginMember,content,articleId));
//    }


//    @RequestMapping(value="/commentList/{articleId}.json",method = RequestMethod.GET)
//    @ResponseBody
//    public ResultModel commentList(@PathVariable("articleId") Integer articleId){
//        Page page = new Page(request);
//        if(articleId == null){
//            articleId = 0;
//        }
//        List<ArticleComment> list = articleCommentService.listByPage(page,articleId, null);
//        ResultModel resultModel = new ResultModel(0,page);
//        resultModel.setData(list);
//        return resultModel;
//    }


    @RequestMapping(value="delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public ResultModel delete(@PathVariable("id") Integer id){
        Member loginMember = MemberUtil.getLoginMember(request);
        ResultModel resultModel = new ResultModel(questionService.delete(loginMember, id));
        return resultModel;
    }

}
