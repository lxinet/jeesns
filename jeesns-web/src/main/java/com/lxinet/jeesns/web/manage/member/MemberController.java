package com.lxinet.jeesns.web.manage.member;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.enums.Messages;
import com.lxinet.jeesns.core.invoke.JeesnsInvoke;
import com.lxinet.jeesns.core.utils.ValidUtill;
import com.lxinet.jeesns.model.member.MemberLevel;
import com.lxinet.jeesns.service.member.MemberService;
import com.lxinet.jeesns.utils.MemberUtil;
import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.member.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/11/22.
 */
@Controller("manageMemberController")
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class MemberController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/member/";
    private static final String EXT_MEMBER_CLASS = "extMemberService";
    private static final String EXT_MEMBER_LEVEL_CLASS = "extMemberLevelService";
    @Resource
    private MemberService memberService;

    @RequestMapping("${jeesns.managePath}/member/index")
    @Before(AdminLoginInterceptor.class)
    public String index(String key,Model model) {
        Page page = new Page(request);
        Result result = memberService.listByPage(page,key);
        model.addAttribute("model", result);
        model.addAttribute("key",key);
        return MANAGE_FTL_PATH + "index";
    }


    /**
     * 会员详情
     */
    @GetMapping("${jeesns.managePath}/member/info/{id}")
    public String info(@PathVariable("id") int id, Model model) {
        Member member = memberService.findById(id);
        model.addAttribute("member", member);
        return MANAGE_FTL_PATH + "info";
    }

    /**
     * 会员启用、禁用操作
     */
    @RequestMapping(value = "${jeesns.managePath}/member/isenable/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result isenable(@PathVariable("id") int id) {
        return memberService.isenable(id);
    }

    @RequestMapping(value = "${jeesns.managePath}/member/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@PathVariable("id") int id) {
        return memberService.delete(id);
    }

    @RequestMapping(value = "${jeesns.managePath}/member/changepwd/{id}", method = RequestMethod.GET)
    public String changepwd(@PathVariable("id") int id, Model model) {
        Member member = memberService.findById(id);
        if (member == null) {
            return errorModel(model, "会员不存在");
        }
        model.addAttribute("member", member);
        return MANAGE_FTL_PATH + "changepwd";
    }

    @RequestMapping(value = "${jeesns.managePath}/member/changepwd", method = RequestMethod.POST)
    @ResponseBody
    public Result changepwd(int id, String password) {
        Member loginMember = MemberUtil.getLoginMember(request);
        return memberService.changepwd(loginMember,id, password);
    }






    /**
     * 管理员列表
     * @param key
     * @param model
     * @return
     */
    @RequestMapping(value = "${jeesns.managePath}/member/managerList",method = RequestMethod.GET)
    public String managerList(String key,Model model) {
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember.getIsAdmin() == 1){
            return errorModel(model, "没有权限");
        }
        Page page = new Page(request);
        Result result = memberService.managerList(page,key);
        model.addAttribute("model", result);
        model.addAttribute("key",key);
        return MANAGE_FTL_PATH + "managerList";
    }

    /**
     * 授权管理员
     * @param model
     * @return
     */
    @RequestMapping(value = "${jeesns.managePath}/member/managerAdd",method = RequestMethod.GET)
    public String managerAdd(Model model) {
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember.getIsAdmin() == 1){
            return errorModel(model, "没有权限");
        }
        return MANAGE_FTL_PATH + "managerAdd";
    }

    /**
     * 授权管理员
     * @param name
     * @return
     */
    @RequestMapping(value = "${jeesns.managePath}/member/managerAdd",method = RequestMethod.POST)
    @ResponseBody
    public Result managerAdd(String name) {
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember.getId() != 1 && loginMember.getIsAdmin() == 1){
            return new Result(-1,"没有权限");
        }
        //管理员授权，只能授权普通管理员
        return memberService.managerAdd(loginMember, name);
    }

    /**
     * 取消管理员
     * @param id
     * @return
     */
    @RequestMapping(value = "${jeesns.managePath}/member/managerCancel/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Result managerCancel(@PathVariable("id") Integer id) {
        if(id == null){
            return new Result(-1,"参数错误");
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember.getIsAdmin() == 1){
            return new Result(-1,"没有权限");
        }
        return memberService.managerCancel(loginMember, id);
    }
    /**
     * 设置会员VIP
     * @param id
     * @return
     */
    @GetMapping("${jeesns.managePath}/member/level/{id}")
    public String level(@PathVariable("id") Integer id, Model model) {
        Member findMember = memberService.findById(id);
        ValidUtill.checkIsNull(findMember, Messages.USER_NOT_EXISTS);
        List<MemberLevel> memberLevelList = (List<MemberLevel>) JeesnsInvoke.invoke(EXT_MEMBER_LEVEL_CLASS, "listAll");
        model.addAttribute("member", findMember);
        model.addAttribute("memberLevelList", memberLevelList);
        return MANAGE_FTL_PATH + "level";
    }
    /**
     * 设置会员VIP
     * @param id
     * @return
     */
    @PostMapping("${jeesns.managePath}/member/setLevel")
    @ResponseBody
    public Result setLevel(@Param("id") Integer id,@Param("memberLevelId") Integer memberLevelId) {
        ValidUtill.checkIsNull(id, Messages.PARAM_ERROR);
        boolean boo = (boolean) JeesnsInvoke.invoke(EXT_MEMBER_CLASS, "setMemberLevel", id, memberLevelId);
        return new Result(boo);
    }

    /**
     * 加/扣款
     * @param id
     * @return
     */
    @GetMapping("${jeesns.managePath}/member/increaseMoney/{id}")
    public String increaseMoney(@PathVariable("id") Integer id, Model model) {
        Member findMember = memberService.findById(id);
        model.addAttribute("member", findMember);
        return MANAGE_FTL_PATH + "increaseMoney";
    }

    /**
     * 加/扣款
     * @param id
     * @return
     */
    @PostMapping("${jeesns.managePath}/member/increaseMoney/{id}")
    @ResponseBody
    public Result increaseMoney(@PathVariable("id") Integer id, @RequestParam(value = "money", defaultValue = "0") Double money) {
        memberService.increaseMoney(money, id);
        return new Result(0);
    }

    /**
     * 加/减积分
     * @param id
     * @return
     */
    @GetMapping("${jeesns.managePath}/member/increaseScore/{id}")
    public String increaseScore(@PathVariable("id") Integer id, Model model) {
        Member findMember = memberService.findById(id);
        model.addAttribute("member", findMember);
        return MANAGE_FTL_PATH + "increaseScore";
    }

    /**
     * 加/减积分
     * @param id
     * @return
     */
    @PostMapping("${jeesns.managePath}/member/increaseScore/{id}")
    @ResponseBody
    public Result increaseScore(@PathVariable("id") Integer id, @RequestParam(value = "score", defaultValue = "0") Integer score) {
        memberService.increaseScore(score, id);
        return new Result(0);
    }
}
