package cn.jeesns.web.manage.member;

import cn.jeesns.interceptor.UserLoginInterceptor;
import cn.jeesns.model.member.ScoreDetail;
import cn.jeesns.service.member.ScoreDetailService;
import cn.jeesns.core.annotation.Before;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.model.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2019/1/5.
 */
@Controller("scoreDetailManageController")
@RequestMapping("${jeesns.managePath}/member/scoreDetail")
@Before(UserLoginInterceptor.class)
public class ScoreDetailController extends BaseController {
    private static final String INDEX_FTL_PATH = "/manage/member/scoreDetail/";
    @Resource
    private ScoreDetailService scoreDetailService;

    @GetMapping(value = {"/list"})
    public String list(@RequestParam(value = "memberId",required = false, defaultValue = "0")  Integer memberId, Model model){
        Page page = new Page(request);
        Result<ScoreDetail> result = scoreDetailService.list(page,memberId);
        model.addAttribute("model", result);
        return INDEX_FTL_PATH + "list";
    }
}
