package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.exception.OpeErrorException;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.model.member.DeliveryAddress;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.member.IDeliveryAddressService;
import com.lxinet.jeesns.utils.MemberUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * 收货地址
 * Created by zchuanzhao on 2019/5/16.
 */
@Controller("frontDeliveryAddressController")
@RequestMapping("/member/deliveryAddress/")
@Before(UserLoginInterceptor.class)
public class DeliveryAddressController extends BaseController {
    private static final String MEMBER_FTL_PATH = "/member/deliveryAddress/";
    @Resource
    private IDeliveryAddressService deliveryAddressService;

    @GetMapping("list")
    public String list(Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        List<DeliveryAddress> deliveryAddressList = deliveryAddressService.listByMemberId(loginMember.getId());
        model.addAttribute("deliveryAddressList", deliveryAddressList);
        return MEMBER_FTL_PATH + "list";
    }


    @GetMapping("add")
    public String add(Model model){
        return MEMBER_FTL_PATH + "add";
    }

    @PostMapping("save")
    @ResponseBody
    public ResultModel save(@Validated DeliveryAddress deliveryAddress){
        if (deliveryAddress.getIsDefault() == null){
            deliveryAddress.setIsDefault(0);
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        deliveryAddress.setMemberId(loginMember.getId());
        boolean result = deliveryAddressService.save(deliveryAddress);
        return new ResultModel(result);
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        DeliveryAddress deliveryAddress = deliveryAddressService.findById(id);
        if (deliveryAddress != null && deliveryAddress.getMemberId().intValue() == loginMember.getId().intValue()){
            model.addAttribute("deliveryAddress", deliveryAddress);
        }
        return MEMBER_FTL_PATH + "edit";
    }

    @PostMapping("update")
    @ResponseBody
    public ResultModel update(@Valid DeliveryAddress deliveryAddress){
        Member loginMember = MemberUtil.getLoginMember(request);
        DeliveryAddress findDeliveryAddress = deliveryAddressService.findById(deliveryAddress.getId());
        if (findDeliveryAddress.getMemberId().intValue() != loginMember.getId().intValue()){
            throw new OpeErrorException("error");
        }
        if (deliveryAddress.getIsDefault() == null){
            deliveryAddress.setIsDefault(0);
        }
        boolean result = deliveryAddressService.update(deliveryAddress);
        return new ResultModel(result);
    }


    @GetMapping("delete/{id}")
    @ResponseBody
    public ResultModel delete(@PathVariable("id") Integer id){
        Member loginMember = MemberUtil.getLoginMember(request);
        DeliveryAddress findDeliveryAddress = deliveryAddressService.findById(id);
        if (findDeliveryAddress.getMemberId().intValue() != loginMember.getId().intValue()){
            throw new OpeErrorException("error");
        }
        deliveryAddressService.delete(findDeliveryAddress);
        return new ResultModel(0);
    }
}
