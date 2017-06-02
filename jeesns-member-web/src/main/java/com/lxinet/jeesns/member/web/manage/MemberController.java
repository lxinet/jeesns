package com.lxinet.jeesns.member.web.manage;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.web.BaseController;
import com.lxinet.jeesns.member.entity.Member;
import com.lxinet.jeesns.member.service.IMemberService;
import com.lxinet.jeesns.member.utils.MemberUtil;
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
public class MemberController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/mem/";
    @Resource
    private IMemberService memberService;

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
