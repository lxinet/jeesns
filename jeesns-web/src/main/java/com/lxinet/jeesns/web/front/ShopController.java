package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.model.shop.GoodsCate;
import com.lxinet.jeesns.service.shop.IGoodsCateService;
import com.lxinet.jeesns.service.shop.IGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商城
 * Created by zchuanzhao on 2019/5/28.
 */
@Controller("frontShopController")
@RequestMapping("/shop")
public class ShopController extends BaseController {
    @Resource
    private IGoodsService goodsService;
    @Resource
    private IGoodsCateService goodsCateService;

    @GetMapping("/list")
    public String list(Model model){
        List<GoodsCate> topCateList = goodsCateService.topList();
        List<GoodsCate> sonCateList = goodsCateService.sonList();
        model.addAttribute("topCateList", topCateList);
        model.addAttribute("sonCateList", sonCateList);
        return jeesnsConfig.getFrontTemplate() + "/shop/list";
    }




}
