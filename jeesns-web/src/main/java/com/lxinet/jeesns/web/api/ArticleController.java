package com.lxinet.jeesns.web.api;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.enums.Messages;
import com.lxinet.jeesns.core.exception.NotFountException;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.core.utils.StringUtils;
import com.lxinet.jeesns.core.utils.ValidUtill;
import com.lxinet.jeesns.model.cms.Article;
import com.lxinet.jeesns.model.cms.ArticleComment;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.cms.ArticleCateService;
import com.lxinet.jeesns.service.cms.ArticleCommentService;
import com.lxinet.jeesns.service.cms.ArticleService;
import com.lxinet.jeesns.service.common.ArchiveService;
import com.lxinet.jeesns.utils.JwtUtil;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author zhangchuanzhao
 * @date 2020/5/8 12:46
 */
@RestController("apiArticleController")
@RequestMapping("/api/article")
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
    @Resource
    private JwtUtil jwtUtil;

    @GetMapping(value="/list")
    public Result list(String key, @RequestParam(value = "cid",defaultValue = "0",required = false) Integer cid,
                       @RequestParam(value = "memberId",defaultValue = "0",required = false) Integer memberId) {
        try {
            key = StringUtils.isNotEmpty(key) ? new String(key.getBytes("iso-8859-1"),"utf-8") : "";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Page page = new Page(request);
        Result result = articleService.listByPage(page,key,cid,1,memberId);
        return result;
    }

    @GetMapping(value="/info/{id}")
    public Result info(@PathVariable("id") Integer id){
        Member loginMember = jwtUtil.getMember(request);
        Article article = articleService.findById(id,loginMember);
        //文章不存在或者访问未审核的文章，跳到错误页面，提示文章不存在
        if(article == null || article.getStatus() == 0){
            throw new NotFountException(Messages.ARTICLE_NOT_EXISTS);
        }
        //更新文章访问次数
        articleService.updateViewCount(article.getId());
        return new Result(article);
    }

    @PostMapping(value="/save")
    public Result save(@Valid Article article, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new Result(-1,getErrorMessages(bindingResult));
        }
        Member loginMember = jwtUtil.getMember(request);
        Result result = new Result(articleService.save(loginMember,article));
        return result;
    }


    @PostMapping(value="/update")
    public Result update(@Valid Article article,BindingResult bindingResult) {
        ValidUtill.checkParam(bindingResult.hasErrors(), getErrorMessages(bindingResult));
        ValidUtill.checkParam(article.getId() == null);
        Member loginMember = jwtUtil.getMember(request);
        Result result = new Result(articleService.update(loginMember,article));
        return result;
    }

    /**
     * 评论文章
     * @param articleId
     * @param content
     * @return
     */
    @PostMapping(value="/comment/{articleId}")
    public Result comment(@PathVariable("articleId") Integer articleId, String content){
        Member loginMember = jwtUtil.getMember(request);
        return new Result(articleCommentService.save(loginMember,content,articleId));
    }


    @GetMapping(value="/commentList/{articleId}")
    public Result commentList(@PathVariable("articleId") Integer articleId){
        Page page = new Page(request);
        articleId = articleId == null ? 0 : articleId;
        List<ArticleComment> list = articleCommentService.listByPage(page,articleId, null);
        Result result = new Result(0,page);
        result.setData(list);
        return result;
    }


    @GetMapping(value="/delete/{id}")
    public Result delete(@PathVariable("id") int id){
        Member loginMember = jwtUtil.getMember(request);
        ValidUtill.checkParam(loginMember.getIsAdmin() == 0, "权限不足");
        Result result = new Result(articleService.delete(loginMember,id));
        return result;
    }


    /**
     * 文章、喜欢
     * @param id
     * @return
     */
    @GetMapping(value="/favor/{id}")
    public Result favor(@PathVariable("id") Integer id){
        Member loginMember = jwtUtil.getMember(request);
        ValidUtill.checkParam(id == null);
        Result result = articleService.favor(loginMember,id);
        return result;
    }
}
