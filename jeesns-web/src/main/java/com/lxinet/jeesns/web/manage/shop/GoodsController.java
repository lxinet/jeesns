package com.lxinet.jeesns.web.manage.shop;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.shop.Goods;
import com.lxinet.jeesns.model.shop.GoodsCate;
import com.lxinet.jeesns.service.shop.GoodsCateService;
import com.lxinet.jeesns.service.shop.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    private GoodsCateService goodsCateService;
    @Resource
    private GoodsService goodsService;

    @GetMapping("list")
    @Before(AdminLoginInterceptor.class)
    public String index(String key, @RequestParam(value = "cateid",defaultValue = "0",required = false) Integer cateid,
    @RequestParam(value = "status",defaultValue = "-1",required = false) Integer status, Model model) {
        List<GoodsCate> topList = goodsCateService.topList();
        List<GoodsCate> sonList = goodsCateService.sonList();
        Page page = new Page(request);
        Result resultModel = goodsService.listByPage(page,key,cateid,status);
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
    public Result save(Goods goods) {
        return new Result(goodsService.save(goods));
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
    public Result update(Goods goods) {
        return new Result(goodsService.update(goods));
    }


    @GetMapping("delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable("id") Integer id){
        return new Result(goodsService.delete(id));
    }

    @GetMapping("changeStatus/{id}")
    @ResponseBody
    public Result changeStatus(@PathVariable("id") Integer id){
        return new Result(goodsService.changeStatus(id));
    }

}
