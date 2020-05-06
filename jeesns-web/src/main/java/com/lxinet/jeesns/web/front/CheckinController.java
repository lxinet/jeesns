package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.service.member.CheckinService;
import com.lxinet.jeesns.utils.MemberUtil;
import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.model.member.Checkin;
import com.lxinet.jeesns.model.member.Member;
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
        ResultModel resultModel = new ResultModel(0, page);
        resultModel.setData(list);
        model.addAttribute("model",resultModel);
        model.addAttribute("todayContinueList",checkinService.todayContinueList());
        return jeesnsConfig.getFrontTemplate() + "/checkin/index";
    }

    @RequestMapping("save")
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public ResultModel save(){
        Member member = MemberUtil.getLoginMember(request);
        return new ResultModel<>(checkinService.save(member.getId()));
    }
}
