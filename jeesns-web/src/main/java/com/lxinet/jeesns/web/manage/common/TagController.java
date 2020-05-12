package com.lxinet.jeesns.web.manage.common;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.utils.ValidUtill;
import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.system.Tag;
import com.lxinet.jeesns.service.system.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/10/13.
 */
@Controller
@RequestMapping("/${jeesns.managePath}/tag")
@Before(AdminLoginInterceptor.class)
public class TagController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/tag/";
    @Resource
    private TagService tagService;

    @RequestMapping("/list/{funcType}")
    public String list(Model model,@PathVariable("funcType") Integer funcType){
        Page page = new Page(request);
        Result result = tagService.listByPage(page,funcType);
        model.addAttribute("funcType",funcType);
        model.addAttribute("model", result);
        return MANAGE_FTL_PATH + "list";
    }

    @RequestMapping("/add/{funcType}")
    public String add(Model model,@PathVariable("funcType") Integer funcType){
        model.addAttribute("funcType",funcType);
        return MANAGE_FTL_PATH + "add";
    }

    @RequestMapping("/save")
    @ResponseBody
    public Result save(Tag tag){
        return new Result(tagService.save(tag));
    }


    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id){
        Tag tag = tagService.findById(id);
        model.addAttribute("tag",tag);
        return MANAGE_FTL_PATH + "edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(Tag tag){
        ValidUtill.checkIsNull(tag);
        return new Result(tagService.update(tag));
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable("id") Integer id){
        return new Result(tagService.delete(id));
    }

}
