package com.lxinet.jeesns.web.manage.shop;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.enums.Messages;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.shop.GoodsCate;
import com.lxinet.jeesns.service.shop.GoodsCateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品分类
 * Created by zchuanzhao on 2019/5/15.
 */
@Controller
@RequestMapping("${managePath}/shop/goodsCate/")
@Before(AdminLoginInterceptor.class)
public class GoodsCateController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/shop/goodsCate";
    @Resource
    private GoodsCateService goodsCateService;

    @RequestMapping("list")
    public String list(Model model){
        List<GoodsCate> topList = goodsCateService.topList();
        List<GoodsCate> sonList = goodsCateService.sonList();
        model.addAttribute("topList",topList);
        model.addAttribute("sonList",sonList);
        return MANAGE_FTL_PATH + "/list";
    }

    @RequestMapping("add")
    public String add(Model model){
        List<GoodsCate> topList = goodsCateService.topList();
        model.addAttribute("topList",topList);
        return MANAGE_FTL_PATH + "/add";
    }

    @RequestMapping("save")
    @ResponseBody
    public Result save(GoodsCate goodsCate){
        if(goodsCate == null){
            throw new ParamException();
        }
        if(StringUtils.isEmpty(goodsCate.getName())){
            throw new ParamException(Messages.NAME_NOT_EMPTY);
        }
        return new Result(goodsCateService.save(goodsCate));
    }

    @RequestMapping("edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        GoodsCate goodsCate = goodsCateService.findById(id);
        model.addAttribute("goodsCate",goodsCate);
        List<GoodsCate> topList = goodsCateService.topList();
        model.addAttribute("topList",topList);
        return MANAGE_FTL_PATH + "/edit";
    }

    @RequestMapping("update")
    @ResponseBody
    public Result update(GoodsCate goodsCate){
        if(goodsCate == null){
            throw new ParamException();
        }
        if(StringUtils.isEmpty(goodsCate.getName())){
            throw new ParamException(Messages.NAME_NOT_EMPTY);
        }
        return new Result(goodsCateService.update(goodsCate));
    }


    @RequestMapping("delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable("id") int id){
        return new Result(goodsCateService.delete(id));
    }
}
