package com.lxinet.jeesns.web.api;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.model.member.Checkin;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.member.CheckinService;
import com.lxinet.jeesns.utils.MemberUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangchuanzhao
 * @date 2020/5/8 12:46
 */
@RestController("apiCheckinController")
@RequestMapping("/api/checkin/")
public class CheckinController extends BaseController {
    @Resource
    private CheckinService checkinService;
    @Resource
    private JeesnsConfig jeesnsConfig;

    @GetMapping({"","index"})
    public String index(Model model){
        Page page = new Page<Checkin>(request);
        List<Checkin> list = checkinService.todayList(page);
        Result result = new Result(0, page);
        result.setData(list);
        model.addAttribute("model",result);
        model.addAttribute("todayContinueList",checkinService.todayContinueList());
        return jeesnsConfig.getFrontTemplate() + "/checkin/index";
    }

    @PostMapping("save")
    public Result save(){
        Member member = MemberUtil.getLoginMember(request);
        return new Result<>(checkinService.save(member.getId()));
    }
}
