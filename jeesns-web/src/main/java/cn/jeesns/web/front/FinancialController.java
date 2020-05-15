package cn.jeesns.web.front;

import cn.jeesns.interceptor.UserLoginInterceptor;
import cn.jeesns.model.member.Financial;
import cn.jeesns.model.member.Member;
import cn.jeesns.service.member.FinancialService;
import cn.jeesns.utils.MemberUtil;
import cn.jeesns.core.annotation.Before;
import cn.jeesns.core.annotation.UsePage;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.utils.PageUtil;
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
