package cn.jeesns.web.api;

import cn.jeesns.model.member.Checkin;
import cn.jeesns.model.member.Member;
import cn.jeesns.service.member.CheckinService;
import cn.jeesns.utils.JwtUtil;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.model.Page;
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
