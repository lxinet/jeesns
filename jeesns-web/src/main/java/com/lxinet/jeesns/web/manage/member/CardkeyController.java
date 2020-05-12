package com.lxinet.jeesns.web.manage.member;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.annotation.UsePage;
import com.lxinet.jeesns.core.conditions.SqlWrapper;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.invoke.JeesnsInvoke;
import com.lxinet.jeesns.core.utils.PageUtil;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.member.Cardkey;
import com.lxinet.jeesns.model.member.MemberLevel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zchuanzhao on 2018/11/27.
 */
@Controller("manageCardkeyController")
@RequestMapping("${jeesns.managePath}/member/cardkey/")
@Before(AdminLoginInterceptor.class)
public class CardkeyController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/member/cardkey/";
    private static final String EXT_CARDKEY_CLASS = "extCardkeyService";

    @GetMapping("list")
    @UsePage
    public String list(Model model) {
        SqlWrapper<Cardkey> sqlWrapper = new SqlWrapper<>(Cardkey.class);
        sqlWrapper.order("id", SqlWrapper.DESC);
        List<MemberLevel> list = (List<MemberLevel>) JeesnsInvoke.invoke(EXT_CARDKEY_CLASS, "listByPage", PageUtil.getPage(), sqlWrapper);
        Result result = new Result(0,PageUtil.getPage());
        result.setData(list);
        model.addAttribute("model", result);
        return MANAGE_FTL_PATH + "list";
    }

    @GetMapping("add")
    public String add() {
        return MANAGE_FTL_PATH + "add";
    }

    @PostMapping("save")
    @ResponseBody
    public Result save(@Param("num") Integer num, @Param("money") Double money, @Param("expireTime") String expireTime) {
        boolean boo = (boolean) JeesnsInvoke.invoke(EXT_CARDKEY_CLASS, "save",num, money, expireTime);
        return new Result(boo);
    }


    @GetMapping("delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable("id") Integer id) {
        boolean boo = (boolean) JeesnsInvoke.invoke(EXT_CARDKEY_CLASS, "deleteById",id);
        return new Result(boo);
    }


}
