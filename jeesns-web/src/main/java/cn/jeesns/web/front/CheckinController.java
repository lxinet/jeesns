package cn.jeesns.web.front;

import cn.jeesns.interceptor.UserLoginInterceptor;
import cn.jeesns.model.member.Checkin;
import cn.jeesns.model.member.Member;
import cn.jeesns.service.member.CheckinService;
import cn.jeesns.utils.MemberUtil;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.annotation.Before;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.model.Page;
import cn.jeesns.core.utils.JeesnsConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 签到
 * Created by zchuanzhao on 2018/8/20.
 */
@Controller
@RequestMapping("/checkin/")
public class CheckinController extends BaseController {
    @Resource
    private CheckinService checkinService;
    @Resource
    private JeesnsConfig jeesnsConfig;

    @RequestMapping({"","index"})
    public String index(Model model){
        Page page = new Page<Checkin>(request);
        List<Checkin> list = checkinService.todayList(page);
        Result result = new Result(0, page);
        result.setData(list);
        model.addAttribute("model",result);
        model.addAttribute("todayContinueList",checkinService.todayContinueList());
        return jeesnsConfig.getFrontTemplate() + "/checkin/index";
    }

    @RequestMapping("save")
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result save(){
        Member member = MemberUtil.getLoginMember(request);
        return new Result<>(checkinService.save(member.getId()));
    }
}
