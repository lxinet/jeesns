package com.lxinet.jeesns.web.manage.shop;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.enums.GoodsStatue;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.cms.Article;
import com.lxinet.jeesns.model.cms.ArticleCate;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.shop.Goods;
import com.lxinet.jeesns.model.shop.GoodsCate;
import com.lxinet.jeesns.service.cms.IArticleCateService;
import com.lxinet.jeesns.service.cms.IArticleService;
import com.lxinet.jeesns.service.shop.IGoodsCateService;
import com.lxinet.jeesns.service.shop.IGoodsService;
import com.lxinet.jeesns.utils.MemberUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 商品
 * Created by zchuanzhao on 2019/5/15.
 */
@Controller("manageGoodsController")
@RequestMapping("${managePath}/shop/goods/")
@Before(AdminLoginInterceptor.class)
public class GoodsController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/shop/goods/";
    @Resource
    private IGoodsCateService goodsCateService;
    @Resource
    private IGoodsService goodsService;

    @GetMapping("list")
    @Before(AdminLoginInterceptor.class)
    public String index(String key, @RequestParam(value = "cateid",defaultValue = "0",required = false) Integer cateid,
    @RequestParam(value = "status",defaultValue = "-1",required = false) Integer status, Model model) {
        List<GoodsCate> topList = goodsCateService.topList();
        List<GoodsCate> sonList = goodsCateService.sonList();
        Page page = new Page(request);
        ResultModel resultModel = goodsService.listByPage(page,key,cateid,status);
        model.addAttribute("model", resultModel);
        model.addAttribute("topList",topList);
        model.addAttribute("sonList",sonList);
        model.addAttribute("key",key);
        model.addAttribute("cateid",cateid);
        return MANAGE_FTL_PATH + "list";
    }

    @GetMapping("add")
    public String add(Model model) {
        List<GoodsCate> topList = goodsCateService.topList();
        List<GoodsCate> sonList = goodsCateService.sonList();
        model.addAttribute("topList",topList);
        model.addAttribute("sonList",sonList);
        return MANAGE_FTL_PATH + "add";
    }

    @PostMapping("save")
    @ResponseBody
    public ResultModel save(Goods goods) {
        return new ResultModel(goodsService.save(goods));
    }


    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        List<GoodsCate> topList = goodsCateService.topList();
        List<GoodsCate> sonList = goodsCateService.sonList();
        model.addAttribute("topList",topList);
        model.addAttribute("sonList",sonList);
        Goods goods = goodsService.findById(id);
        model.addAttribute("goods",goods);
        return MANAGE_FTL_PATH + "/edit";
    }

    @PostMapping("update")
    @ResponseBody
    public ResultModel update(Goods goods) {
        return new ResultModel(goodsService.update(goods));
    }


    @GetMapping("delete/{id}")
    @ResponseBody
    public ResultModel delete(@PathVariable("id") Integer id){
        return new ResultModel(goodsService.delete(id));
    }

    @GetMapping("changeStatus/{id}")
    @ResponseBody
    public ResultModel changeStatus(@PathVariable("id") Integer id){
        return new ResultModel(goodsService.changeStatus(id));
    }

}
