package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.enums.GoodsStatue;
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

    @GetMapping(value = {"/list", "/list-{cateId}"})
    public String list(@PathVariable(value = "cateId", required = false) Integer cateId, Model model){
        cateId = cateId == null ? -1 : cateId;
        Page page = new Page(request);
        List<GoodsCate> topCateList = goodsCateService.topList();
        List<GoodsCate> sonCateList = goodsCateService.sonList();
        ResultModel goodsResultModel = goodsService.listByPage(page, "", cateId, GoodsStatue.ENABLED.value());
        GoodsCate goodsCate = null;
        if (cateId > 0){
            goodsCate = goodsCateService.findById(cateId);
        }
        model.addAttribute("topCateList", topCateList);
        model.addAttribute("sonCateList", sonCateList);
        model.addAttribute("goodsCate", goodsCate);
        model.addAttribute("goodsResultModel", goodsResultModel);
        return jeesnsConfig.getFrontTemplate() + "/shop/list";
    }




}
