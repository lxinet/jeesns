package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.shop.ShopCart;
import com.lxinet.jeesns.service.shop.GoodsService;
import com.lxinet.jeesns.service.shop.ShopCartService;
import com.lxinet.jeesns.utils.MemberUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 购物车
 * Created by zchuanzhao on 2019/5/27.
 */
@Controller("frontShopCartController")
@RequestMapping("/member/shopCart")
@Before(UserLoginInterceptor.class)
public class ShopCartController extends BaseController {
    private static final String SHOP_CART_FTL_PATH = "/member/shopCart";
    @Resource
    private GoodsService goodsService;
    @Resource
    private ShopCartService shopCartService;

    @PostMapping("/save")
    @ResponseBody
    public Result save(ShopCart shopCart){
        Member loginMember = MemberUtil.getLoginMember(request);
        shopCart.setMemberId(loginMember.getId());
        shopCartService.save(shopCart);
        return new Result(0, "加入购物车成功");
    }

    @PostMapping("/updateNum")
    @ResponseBody
    public Result updateNum(ShopCart shopCart){
        Member loginMember = MemberUtil.getLoginMember(request);
        boolean result = shopCartService.updateNum(shopCart.getId(), shopCart.getNum(), loginMember.getId());
        return new Result(result);
    }

    @GetMapping("/list")
    public String cart(Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        Page page = new Page(request);
        model.addAttribute("member",loginMember);
        Result resultModel = shopCartService.listByMemberId(page, loginMember.getId());
        model.addAttribute("model", resultModel);
        return SHOP_CART_FTL_PATH + "/list";
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable("id") Integer id){
        Member loginMember = MemberUtil.getLoginMember(request);
        boolean result = shopCartService.delete(id, loginMember.getId());
        return new Result(result);
    }

    @PostMapping("/deleteAll")
    @ResponseBody
    public Result deleteAll(){
        Member loginMember = MemberUtil.getLoginMember(request);
        boolean result = shopCartService.deleteByMemberId(loginMember.getId());
        return new Result(result);
    }


}
