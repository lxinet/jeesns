package com.lxinet.jeesns.modules.cms.web.index;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.service.IArchiveService;
import com.lxinet.jeesns.core.utils.Const;
import com.lxinet.jeesns.core.utils.ErrorUtil;
import com.lxinet.jeesns.core.utils.MemberUtil;
import com.lxinet.jeesns.core.web.BaseController;
import com.lxinet.jeesns.modules.cms.entity.ArticleCate;
import com.lxinet.jeesns.modules.cms.entity.Article;
import com.lxinet.jeesns.modules.cms.service.IArticleCateService;
import com.lxinet.jeesns.modules.cms.service.IArticleCommentService;
import com.lxinet.jeesns.modules.cms.service.IArticleService;
import com.lxinet.jeesns.modules.mem.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by zchuanzhao on 16/9/29.
 */
@Controller("indexArticleController")
@RequestMapping("/article")
public class ArticleController extends BaseController {
    private static final String INDEX_FTL_PATH = "/index/cms/";
    @Resource
    private IArticleCateService articleCateService;
    @Resource
    private IArticleService articleService;
    @Resource
    private IArchiveService archiveService;
    @Resource
    private IArticleCommentService articleCommentService;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(String key,Integer cateid,Model model) {
        Page page = new Page(request);
        if(cateid == null){
            cateid = 0;
        }
        ResponseModel responseModel = articleService.listByPage(page,key,cateid,1);
        model.addAttribute("model",responseModel);
        List<ArticleCate> cateList = articleCateService.list();
        model.addAttribute("cateList",cateList);
        ArticleCate articleCate = articleCateService.findById(cateid);
        model.addAttribute("articleCate",articleCate);
        return INDEX_FTL_PATH + "list";
    }

    @RequestMapping(value="/detail/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        Article article = articleService.findById(id,loginMember);
        if(article != null){
            archiveService.updateViewCount(article.getArchiveId());
        }else {
            return "/index/common/error";
        }
        model.addAttribute("article",article);
        return INDEX_FTL_PATH + "/detail";
    }

    @RequestMapping(value="/add",method = RequestMethod.GET)
    public String add(Model model) {
        List<ArticleCate> cateList = articleCateService.list();
        model.addAttribute("cateList",cateList);
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return "redirect:"+request.getContextPath()+"/member/login";
        }
        return INDEX_FTL_PATH + "add";
    }

    @RequestMapping(value="/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(@Valid Article article, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseModel(-1,getErrorMessages(bindingResult));
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        ResponseModel responseModel = articleService.save(loginMember,article);
        if(responseModel.getCode() == 0){
            responseModel.setCode(2);
            responseModel.setUrl(request.getContextPath()+"/article/detail/"+article.getId());
        }
        return responseModel;
    }

    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return "redirect:"+request.getContextPath()+"/member/login";
        }
        Article article = articleService.findById(id,loginMember);
        if(article.getMemberId() != loginMember.getId()){
            return ErrorUtil.error(model,-1001,Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("article",article);
        List<ArticleCate> cateList = articleCateService.list();
        model.addAttribute("cateList",cateList);
        return INDEX_FTL_PATH + "/edit";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@Valid Article article,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            new ResponseModel(-1,getErrorMessages(bindingResult));
        }
        if(article.getId() == null){
            return new ResponseModel(-2);
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        ResponseModel responseModel = articleService.update(loginMember,article);
        if(responseModel.getCode() == 0){
            responseModel.setCode(2);
            responseModel.setUrl(request.getContextPath() + "/article/detail/"+article.getId());
        }
        return responseModel;
    }

    /**
     * 评论文章
     * @param articleId
     * @param content
     * @return
     */
    @RequestMapping(value="/comment/{articleId}",method = RequestMethod.POST)
    @ResponseBody
    public Object comment(@PathVariable("articleId") Integer articleId, String content){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        return articleCommentService.save(loginMember,content,articleId);
    }


    @RequestMapping(value="/commentList/{articleId}.json",method = RequestMethod.GET)
    @ResponseBody
    public Object commentList(@PathVariable("articleId") Integer articleId){
        Page page = new Page(request);
        if(articleId == null){
            articleId = 0;
        }
        return articleCommentService.listByArticle(page,articleId);
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("id") int id){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        if(loginMember.getIsAdmin() == 0){
            return new ResponseModel(-1,"权限不足");
        }
        ResponseModel responseModel = articleService.delete(loginMember,id);
        if(responseModel.getCode() > 0){
            responseModel.setCode(2);
            responseModel.setUrl(request.getContextPath() + "/article/list");
        }
        return responseModel;
    }

}
