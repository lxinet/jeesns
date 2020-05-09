package com.lxinet.jeesns.web.api;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.member.ScoreDetail;
import com.lxinet.jeesns.service.member.ScoreDetailService;
import com.lxinet.jeesns.utils.JwtUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangchuanzhao
 * @date 2020/5/8 12:46
 */
@RestController("apiDetailFrontController")
@RequestMapping("/api/member/scoreDetail")
public class ScoreDetailController extends BaseController {
    private static final String INDEX_FTL_PATH = "/member/scoreDetail/";
    @Resource
    private ScoreDetailService scoreDetailService;
    @Resource
    private JwtUtil jwtUtil;

    @GetMapping(value = "/list")
    public String list(Model model){
        Member loginMember = jwtUtil.getMember(request);
        Page page = new Page(request);
        Result<ScoreDetail> result = scoreDetailService.list(page,loginMember.getId());
        model.addAttribute("model", result);
        return INDEX_FTL_PATH + "list";
    }
}
