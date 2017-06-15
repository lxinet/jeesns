package com.lxinet.jeesns.member.web.manage;

import com.lxinet.jeesns.commons.utils.MemberUtil;
import com.lxinet.jeesns.member.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.web.BaseController;
import com.lxinet.jeesns.member.model.Member;
import com.lxinet.jeesns.member.service.IMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2016/11/22.
 */
@Controller("manageMemberController")
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class MemberController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/mem/";
    @Resource
    private IMemberService memberService;


    /**
     * 管理员列表
     * @param key
     * @param model
     * @return
     */
    @RequestMapping(value = "${managePath}/mem/manager/list",method = RequestMethod.GET)
    public String managerList(String key,Model model) {
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember.getIsAdmin() == 1){
            return errorModel(model, "没有权限");
        }
        Page page = new Page(request);
        ResponseModel responseModel = memberService.managerList(page,key);
        model.addAttribute("model",responseModel);
        model.addAttribute("key",key);
        return MANAGE_FTL_PATH + "manager/list";
    }

    /**
     * 授权管理员
     * @param model
     * @return
     */
    @RequestMapping(value = "${managePath}/mem/manager/add",method = RequestMethod.GET)
    public String managerAdd(Model model) {
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember.getIsAdmin() == 1){
            return errorModel(model, "没有权限");
        }
        return MANAGE_FTL_PATH + "manager/add";
    }

    /**
     * 授权管理员
     * @param name
     * @return
     */
    @RequestMapping(value = "${managePath}/mem/manager/add",method = RequestMethod.POST)
    @ResponseBody
    public Object managerAdd(String name) {
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember.getId() != 1 && loginMember.getIsAdmin() == 1){
            return new ResponseModel(-1,"没有权限");
        }
        //管理员授权，只能授权普通管理员
        return memberService.managerAdd(loginMember, name);
    }

    /**
     * 取消管理员
     * @param id
     * @return
     */
    @RequestMapping(value = "${managePath}/mem/manager/cancel/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object managerCancel(@PathVariable("id") Integer id) {
        if(id == null){
            return new ResponseModel(-1,"参数错误");
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember.getIsAdmin() == 1){
            return new ResponseModel(-1,"没有权限");
        }
        return memberService.managerCancel(loginMember, id);
    }


    @RequestMapping("${managePath}/mem/index")
    public String index(String key,Model model) {
        Page page = new Page(request);
        ResponseModel responseModel = memberService.listByPage(page,key);
        model.addAttribute("model",responseModel);
        model.addAttribute("key",key);
        return MANAGE_FTL_PATH + "index";
    }


    /**
     * 会员启用、禁用操作
     */
    @RequestMapping(value = "${managePath}/mem/member/isenable/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel isenable(@PathVariable("id") int id) {
        return memberService.isenable(id);
    }

    @RequestMapping(value = "${managePath}/mem/member/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("id") int id) {
        return memberService.delete(id);
    }

    @RequestMapping(value = "${managePath}/mem/member/changepwd/{id}", method = RequestMethod.GET)
    public String changepwd(@PathVariable("id") int id, Model model) {
        Member member = memberService.findById(id);
        if (member == null) {
            return errorModel(model, "会员不存在");
        }
        model.addAttribute("member", member);
        return MANAGE_FTL_PATH + "changepwd";
    }

    @RequestMapping(value = "${managePath}/mem/member/changepwd", method = RequestMethod.POST)
    @ResponseBody
    public Object changepwd(int id, String password) {
        Member loginMember = MemberUtil.getLoginMember(request);
        return memberService.changepwd(loginMember,id, password);
    }

}
