package com.lxinet.jeesns.web.api;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.member.Checkin;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.member.CheckinService;
import com.lxinet.jeesns.utils.JwtUtil;
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
    private JwtUtil jwtUtil;

    @GetMapping({"list"})
    public Result list(){
        Page page = new Page<Checkin>(request);
        List<Checkin> list = checkinService.todayList(page);
        Result result = new Result(0, page);
        result.setData(list);
        return result;
    }

    @PostMapping("save")
    public Result save(){
        Member member = jwtUtil.getMember(request);
        return new Result<>(checkinService.save(member.getId()));
    }
}
