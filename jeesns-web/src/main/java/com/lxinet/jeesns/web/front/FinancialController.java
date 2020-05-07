package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.annotation.UsePage;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.utils.PageUtil;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.model.member.Financial;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.member.FinancialService;
import com.lxinet.jeesns.utils.MemberUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2018/11/28.
 */
@Controller("frontFinancialController")
@RequestMapping("/member/financial/")
@Before(UserLoginInterceptor.class)
public class FinancialController extends BaseController {
    private static final String INDEX_FTL_PATH = "/member/financial/";
    @Resource
    private FinancialService financialService;

    @UsePage
    @GetMapping("list")
    public String list(Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        List<Financial> list = financialService.list(loginMember.getId());
        Result result = new Result(0, PageUtil.getPage());
        result.setData(list);
        model.addAttribute("model", result);
        return INDEX_FTL_PATH + "list";
    }
}
