package com.lxinet.jeesns.web.manage.member;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.invoke.JeesnsInvoke;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.member.MemberLevel;
import com.lxinet.jeesns.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zchuanzhao on 2018/11/19.
 */
@Controller("manageMemberLevelController")
@RequestMapping("${managePath}/member/level/")
@Before(AdminLoginInterceptor.class)
public class MemberLevelController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/member/level/";
    private static final String EXT_MEMBER_LEVEL_CLASS = "extMemberLevelService";

    @GetMapping("list")
    public String list(Model model) {
        List<MemberLevel> list = (List<MemberLevel>) JeesnsInvoke.invoke(EXT_MEMBER_LEVEL_CLASS, "listAll");
        model.addAttribute("list", list);
        return MANAGE_FTL_PATH + "list";
    }

    @GetMapping("add")
    public String add() {
        return MANAGE_FTL_PATH + "add";
    }

    @PostMapping("save")
    @ResponseBody
    public ResultModel save(MemberLevel memberLevel) {
        boolean boo = (boolean) JeesnsInvoke.invoke(EXT_MEMBER_LEVEL_CLASS, "save",memberLevel);
        return new ResultModel(boo);
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        MemberLevel memberLevel = (MemberLevel) JeesnsInvoke.invoke(EXT_MEMBER_LEVEL_CLASS, "findById",id);
        model.addAttribute("memberLevel", memberLevel);
        return MANAGE_FTL_PATH + "edit";
    }

    @PostMapping("update")
    @ResponseBody
    public ResultModel update(MemberLevel memberLevel) {
        boolean boo = (boolean) JeesnsInvoke.invoke(EXT_MEMBER_LEVEL_CLASS, "update",memberLevel);
        return new ResultModel(boo);
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    public ResultModel delete(@PathVariable("id") Integer id) {
        boolean boo = (boolean) JeesnsInvoke.invoke(EXT_MEMBER_LEVEL_CLASS, "deleteById",id);
        return new ResultModel(boo);
    }


}
