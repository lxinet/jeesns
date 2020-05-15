package cn.jeesns.web.manage.cms;

import cn.jeesns.interceptor.AdminLoginInterceptor;
import cn.jeesns.model.cms.ArticleComment;
import cn.jeesns.model.member.Member;
import cn.jeesns.utils.MemberUtil;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.service.cms.ArticleCommentService;
import cn.jeesns.core.annotation.Before;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.model.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 18/8/15.
 */
@Controller("manageArticleCommentController")
@RequestMapping("${jeesns.managePath}/cms/comment")
@Before(AdminLoginInterceptor.class)
public class ArticleCommentController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/cms/comment/";
    @Resource
    private ArticleCommentService articleCommentService;

    @RequestMapping("/list")
    public String list(String key, @RequestParam(value = "articleId",defaultValue = "0",required = false) Integer articleId, Model model) {
        Page page = new Page(request);
        List<ArticleComment> list = articleCommentService.listByPage(page, articleId, key);
        Result result = new Result(0, page);
        result.setData(list);
        model.addAttribute("model", result);
        model.addAttribute("key",key);
        return MANAGE_FTL_PATH + "list";
    }


    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@PathVariable("id") Integer id){
        Member loginMember = MemberUtil.getLoginMember(request);
        return new Result(articleCommentService.delete(loginMember,id));
    }

}
