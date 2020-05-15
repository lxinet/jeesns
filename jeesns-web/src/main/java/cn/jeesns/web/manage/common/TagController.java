package cn.jeesns.web.manage.common;

import cn.jeesns.interceptor.AdminLoginInterceptor;
import cn.jeesns.model.system.Tag;
import cn.jeesns.service.system.TagService;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.utils.ValidUtill;
import cn.jeesns.core.annotation.Before;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.model.Page;
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
