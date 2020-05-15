package cn.jeesns.web.api;

import cn.jeesns.model.member.Member;
import cn.jeesns.model.member.ScoreDetail;
import cn.jeesns.service.member.ScoreDetailService;
import cn.jeesns.utils.JwtUtil;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.model.Page;
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
