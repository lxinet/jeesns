package cn.jeesns.web.manage.cms;

import cn.jeesns.interceptor.AdminLoginInterceptor;
import cn.jeesns.model.cms.Article;
import cn.jeesns.model.cms.ArticleCate;
import cn.jeesns.model.member.Member;
import cn.jeesns.utils.MemberUtil;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.dto.Result;
import cn.jeesns.service.cms.ArticleCateService;
import cn.jeesns.service.cms.ArticleService;
import cn.jeesns.core.annotation.Before;
import cn.jeesns.core.model.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by zchuanzhao on 16/9/29.
 */
@Controller("manageArticleController")
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class ArticleController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/cms/article/";
    @Resource
    private ArticleCateService articleCateService;
    @Resource
    private ArticleService articleService;

    @RequestMapping("${jeesns.managePath}/cms/index")
    @Before(AdminLoginInterceptor.class)
    public String index(String key, @RequestParam(value = "cateid",defaultValue = "0",required = false) Integer cateid,
    @RequestParam(value = "status",defaultValue = "2",required = false) Integer status,
    @RequestParam(value = "memberId",defaultValue = "0",required = false) Integer memberId, Model model) {
        List<ArticleCate> cateList = articleCateService.list();
        Page page = new Page(request);
        Result resultModel = articleService.listByPage(page,key,cateid,status,memberId);
        model.addAttribute("model", resultModel);
        model.addAttribute("cateList",cateList);
        model.addAttribute("key",key);
        model.addAttribute("cateid",cateid);
        return MANAGE_FTL_PATH + "index";
    }

    @RequestMapping(value="${jeesns.managePath}/cms/article/add",method = RequestMethod.GET)
    public String add(Model model) {
        List<ArticleCate> cateList = articleCateService.list();
        model.addAttribute("cateList",cateList);
        return MANAGE_FTL_PATH + "add";
    }

    @RequestMapping(value="${jeesns.managePath}/cms/article/save",method = RequestMethod.POST)
    @ResponseBody
    public Result save(@Valid Article article, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new Result(-1,getErrorMessages(bindingResult));
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        return new Result(articleService.save(loginMember,article));
    }

    @RequestMapping(value="${jeesns.managePath}/cms/article/list",method = RequestMethod.GET)
    public String list(String key, @RequestParam(value = "cateid",defaultValue = "0",required = false) Integer cateid,
                       @RequestParam(value = "status",defaultValue = "2",required = false) Integer status,
                       @RequestParam(value = "memberId",defaultValue = "0",required = false) Integer memberId, Model model) {
        Page page = new Page(request);
        Result resultModel = articleService.listByPage(page,key,cateid,status,memberId);
        model.addAttribute("model", resultModel);
        return MANAGE_FTL_PATH + "list";
    }

    @RequestMapping(value="${jeesns.managePath}/cms/article/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        List<ArticleCate> cateList = articleCateService.list();
        model.addAttribute("cateList",cateList);
        Article article = articleService.findById(id,loginMember);
        model.addAttribute("article",article);
        return MANAGE_FTL_PATH + "/edit";
    }

    @RequestMapping(value="${jeesns.managePath}/cms/article/update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@Valid Article article,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new Result(-1,getErrorMessages(bindingResult));
        }
        if(article.getId() == null){
            return new Result(-2);
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        return new Result(articleService.update(loginMember,article));
    }


    @RequestMapping(value = "${jeesns.managePath}/cms/article/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@PathVariable("id") Integer id){
        Member loginMember = MemberUtil.getLoginMember(request);
        return new Result(articleService.delete(loginMember,id));
    }

    @RequestMapping(value = "${jeesns.managePath}/cms/article/audit/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Result audit(@PathVariable("id") Integer id){
        return new Result(articleService.audit(id));
    }

}
