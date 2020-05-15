package cn.jeesns.web.api;

import cn.jeesns.interceptor.UserLoginInterceptor;
import cn.jeesns.model.member.Financial;
import cn.jeesns.model.member.Member;
import cn.jeesns.service.member.FinancialService;
import cn.jeesns.utils.JwtUtil;
import cn.jeesns.core.annotation.Before;
import cn.jeesns.core.annotation.UsePage;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.utils.PageUtil;
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
