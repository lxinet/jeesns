package com.lxinet.jeesns.web.manage.cms;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.service.cms.ArticleCommentService;
import com.lxinet.jeesns.utils.MemberUtil;
import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.cms.ArticleComment;
import com.lxinet.jeesns.model.member.Member;
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
