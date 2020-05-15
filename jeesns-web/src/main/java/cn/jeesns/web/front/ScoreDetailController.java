package cn.jeesns.web.front;

import cn.jeesns.interceptor.UserLoginInterceptor;
import cn.jeesns.model.member.Member;
import cn.jeesns.model.member.ScoreDetail;
import cn.jeesns.service.member.ScoreDetailService;
import cn.jeesns.utils.MemberUtil;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.annotation.Before;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.model.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/4/7.
 */
@Controller("scoreDetailFrontController")
@RequestMapping("/member/scoreDetail")
@Before(UserLoginInterceptor.class)
public class ScoreDetailController extends BaseController {
    private static final String INDEX_FTL_PATH = "/member/scoreDetail/";
    @Resource
    private ScoreDetailService scoreDetailService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        Page page = new Page(request);
        Result<ScoreDetail> result = scoreDetailService.list(page,loginMember.getId());
        model.addAttribute("model", result);
        return INDEX_FTL_PATH + "list";
    }
}
