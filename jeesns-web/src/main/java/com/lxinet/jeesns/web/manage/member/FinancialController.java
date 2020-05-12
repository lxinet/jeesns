package com.lxinet.jeesns.web.manage.member;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.annotation.UsePage;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.utils.PageUtil;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.member.Financial;
import com.lxinet.jeesns.service.member.FinancialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2018/11/28.
 */
@Controller
@RequestMapping("/${jeesns.managePath}/member/financial/")
@Before(AdminLoginInterceptor.class)
public class FinancialController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/member/financial/";
    @Resource
    private FinancialService financialService;

    @UsePage
    @RequestMapping("list")
    public String list(Model model, @RequestParam(value = "memberId",required = false, defaultValue = "0") Integer memberId){
        List<Financial> list = financialService.list(memberId);
        Result result = new Result(0, PageUtil.getPage());
        result.setData(list);
        model.addAttribute("model", result);
        return MANAGE_FTL_PATH + "list";
    }

}
