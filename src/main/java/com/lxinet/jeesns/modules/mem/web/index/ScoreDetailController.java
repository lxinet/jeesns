package com.lxinet.jeesns.modules.mem.web.index;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.core.utils.MemberUtil;
import com.lxinet.jeesns.core.web.BaseController;
import com.lxinet.jeesns.modules.mem.entity.Member;
import com.lxinet.jeesns.modules.mem.entity.ScoreDetail;
import com.lxinet.jeesns.modules.mem.service.IScoreDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/4/7.
 */
@Controller("scoreDetailIndexController")
@RequestMapping("/member/scoreDetail")
@Before(UserLoginInterceptor.class)
public class ScoreDetailController extends BaseController {
    private static final String INDEX_FTL_PATH = "/member/scoreDetail/";
    @Resource
    private IScoreDetailService scoreDetailService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        Page page = new Page(request);
        ResponseModel<ScoreDetail> responseModel = scoreDetailService.list(page,loginMember.getId());
        model.addAttribute("model",responseModel);
        return INDEX_FTL_PATH + "list";
    }
}
