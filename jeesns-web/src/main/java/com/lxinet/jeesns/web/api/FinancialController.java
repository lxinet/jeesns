package com.lxinet.jeesns.web.api;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.annotation.UsePage;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.utils.PageUtil;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.model.member.Financial;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.member.FinancialService;
import com.lxinet.jeesns.utils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangchuanzhao
 * @date 2020/5/8 12:46
 */
@RestController("apiFinancialController")
@RequestMapping("/api/member/financial/")
@Before(UserLoginInterceptor.class)
public class FinancialController extends BaseController {
    @Resource
    private FinancialService financialService;
    @Resource
    private JwtUtil jwtUtil;

    @UsePage
    @GetMapping("list")
    public Result list(){
        Member loginMember = jwtUtil.getMember(request);
        List<Financial> list = financialService.list(loginMember.getId());
        Result result = new Result(0, PageUtil.getPage());
        result.setData(list);
        return result;
    }
}
