package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.service.cms.ArticleCateService;
import com.lxinet.jeesns.service.cms.ArticleCommentService;
import com.lxinet.jeesns.service.cms.ArticleService;
import com.lxinet.jeesns.service.common.ArchiveService;
import com.lxinet.jeesns.utils.MemberUtil;
import com.lxinet.jeesns.core.enums.Messages;
import com.lxinet.jeesns.core.exception.NotFountException;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.model.cms.ArticleComment;
import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.*;
import com.lxinet.jeesns.model.cms.ArticleCate;
import com.lxinet.jeesns.model.cms.Article;
import com.lxinet.jeesns.model.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 前台文章Controller
 * Created by zchuanzhao on 16/9/29.
 */
@Controller("frontArticleController")
@RequestMapping("/article")
public class ArticleController extends BaseController {
    @Resource
    private JeesnsConfig jeesnsConfig;
    @Resource
    private ArticleCateService articleCateService;
    @Resource
    private ArticleService articleService;
    @Resource
    private ArchiveService archiveService;
    @Resource
    private ArticleCommentService articleCommentService;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(String key, @RequestParam(value = "cid",defaultValue = "0",required = false) Integer cid,
                       @RequestParam(value = "memberId",defaultValue = "0",required = false) Integer memberId, Model model) {
        if (StringUtils.isNotEmpty(key)){
            try {
                key = new String(key.getBytes("iso-8859-1"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        Page page = new Page(request);
        Result result = articleService.listByPage(page,key,cid,1,memberId);
        model.addAttribute("model", result);
        List<ArticleCate> articleCateList = articleCateService.list();
        model.addAttribute("articleCateList",articleCateList);
        ArticleCate articleCate = articleCateService.findById(cid);
        model.addAttribute("articleCate",articleCate);
        model.addAttribute("key",key);
        return jeesnsConfig.getFrontTemplate() + "/cms/list";
    }

    @RequestMapping(value="/detail/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        Article article = articleService.findById(id,loginMember);
        //文章不存在或者访问未审核的文章，跳到错误页面，提示文章不存在
        if(article == null || article.getStatus() == 0){
            throw new NotFountException(Messages.ARTICLE_NOT_EXISTS);
        }
        //更新文章访问次数
        articleService.updateViewCount(article.getId());
        model.addAttribute("article",article);
        List<ArticleCate> articleCateList = articleCateService.list();
        model.addAttribute("articleCateList",articleCateList);
        model.addAttribute("loginUser",loginMember);
        return jeesnsConfig.getFrontTemplate() + "/cms/detail";
    }

    @RequestMapping(value="/add",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String add(Model model) {
        List<ArticleCate> cateList = articleCateService.list();
        model.addAttribute("cateList",cateList);
        return jeesnsConfig.getFrontTemplate() + "/cms/add";
    }

    @RequestMapping(value="/save",method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result save(@Valid Article article, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new Result(-1,getErrorMessages(bindingResult));
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        Result result = new Result(articleService.save(loginMember,article));
        if(result.getCode() == 0){
            result.setCode(2);
            //文章需要审核就跳转到列表页面
            if(article.getStatus() == 0){
                result.setMessage("文章发布成功，请等待审核");
                result.setUrl(request.getContextPath()+"/article/list");
            }else {
                result.setUrl(request.getContextPath()+"/article/detail/"+article.getId());
            }
        }
        return result;
    }

    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String edit(@PathVariable("id") int id, Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        Article article = articleService.findById(id,loginMember);
        if(article.getMemberId().intValue() != loginMember.getId().intValue()){
            throw new NotFountException(Messages.ARTICLE_NOT_EXISTS);
        }
        model.addAttribute("article",article);
        List<ArticleCate> cateList = articleCateService.list();
        model.addAttribute("cateList",cateList);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/cms/edit";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@Valid Article article,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            new Result(-1,getErrorMessages(bindingResult));
        }
        if(article.getId() == null){
            return new Result(-2);
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        Result result = new Result(articleService.update(loginMember,article));
        if(result.getCode() == 0){
            result.setCode(2);
            result.setUrl(request.getContextPath() + "/article/detail/"+article.getId());
        }
        return result;
    }

    /**
     * 评论文章
     * @param articleId
     * @param content
     * @return
     */
    @RequestMapping(value="/comment/{articleId}",method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result comment(@PathVariable("articleId") Integer articleId, String content){
        Member loginMember = MemberUtil.getLoginMember(request);
        return new Result(articleCommentService.save(loginMember,content,articleId));
    }


    @RequestMapping(value="/commentList/{articleId}.json",method = RequestMethod.GET)
    @ResponseBody
    public Result commentList(@PathVariable("articleId") Integer articleId){
        Page page = new Page(request);
        if(articleId == null){
            articleId = 0;
        }
        List<ArticleComment> list = articleCommentService.listByPage(page,articleId, null);
        Result result = new Result(0,page);
        result.setData(list);
        return result;
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result delete(@PathVariable("id") int id){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember.getIsAdmin() == 0){
            return new Result(-1,"权限不足");
        }
        Result result = new Result(articleService.delete(loginMember,id));
        if(result.getCode() > 0){
            result.setCode(2);
            result.setUrl(request.getContextPath() + "/article/list");
        }
        return result;
    }


    /**
     * 文章、喜欢
     * @param id
     * @return
     */
    @RequestMapping(value="/favor/{id}",method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result favor(@PathVariable("id") Integer id){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(id == null) {
            throw new ParamException();
        }
        Result result = articleService.favor(loginMember,id);
        return result;
    }
}
