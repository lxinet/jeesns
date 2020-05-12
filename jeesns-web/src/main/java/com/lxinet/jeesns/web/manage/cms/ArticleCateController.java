package com.lxinet.jeesns.web.manage.cms;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.enums.Messages;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.cms.ArticleCate;
import com.lxinet.jeesns.service.cms.ArticleCateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 16/9/29.
 */
@Controller
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class ArticleCateController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/cms/articleCate";
    @Resource
    private ArticleCateService articleCateService;

    @RequestMapping("${jeesns.managePath}/cms/articleCate/list")
    public String list(Model model){
        List<ArticleCate> list = articleCateService.list();
        model.addAttribute("list",list);
        return MANAGE_FTL_PATH + "/list";
    }

    @RequestMapping("${jeesns.managePath}/cms/articleCate/add")
    public String add(Model model){
        return MANAGE_FTL_PATH + "/add";
    }

    @RequestMapping("${jeesns.managePath}/cms/articleCate/save")
    @ResponseBody
    public Result save(ArticleCate articleCate){
        if(articleCate == null){
            throw new ParamException();
        }
        if(StringUtils.isEmpty(articleCate.getName())){
            throw new ParamException(Messages.NAME_NOT_EMPTY);
        }
        return new Result(articleCateService.save(articleCate));
    }

    @RequestMapping("${jeesns.managePath}/cms/articleCate/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        ArticleCate articleCate = articleCateService.findById(id);
        model.addAttribute("articleCate",articleCate);
        return MANAGE_FTL_PATH + "/edit";
    }

    @RequestMapping("${jeesns.managePath}/cms/articleCate/update")
    @ResponseBody
    public Result update(ArticleCate articleCate){
        if(articleCate == null){
            throw new ParamException();
        }
        if(StringUtils.isEmpty(articleCate.getName())){
            throw new ParamException(Messages.NAME_NOT_EMPTY);
        }
        return new Result(articleCateService.update(articleCate));
    }


    @RequestMapping("${jeesns.managePath}/cms/articleCate/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable("id") int id){
        return new Result(articleCateService.delete(id));
    }
}
