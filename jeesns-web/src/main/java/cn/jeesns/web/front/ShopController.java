package cn.jeesns.web.front;

import cn.jeesns.enums.GoodsStatue;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.model.Page;
import cn.jeesns.model.shop.Goods;
import cn.jeesns.model.shop.GoodsCate;
import cn.jeesns.service.shop.GoodsCateService;
import cn.jeesns.service.shop.GoodsService;
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
    private GoodsService goodsService;
    @Resource
    private GoodsCateService goodsCateService;

    @GetMapping(value = {"/list", "/list-{cateId}"})
    public String list(@PathVariable(value = "cateId", required = false) Integer cateId, Model model){
        cateId = cateId == null ? -1 : cateId;
        Page page = new Page(request);
        List<GoodsCate> topCateList = goodsCateService.topList();
        List<GoodsCate> sonCateList = goodsCateService.sonList();
        Result goodsResultModel = goodsService.listByPage(page, "", cateId, GoodsStatue.ENABLED.value());
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


    @GetMapping("detail/{id}")
    public String detail(@PathVariable(value = "id") Integer id, Model model){
        Goods goods = goodsService.findById(id);
        model.addAttribute("goods", goods);
        return jeesnsConfig.getFrontTemplate() + "/shop/detail";
    }




}
