package com.lxinet.jeesns.web.manage.weibo;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.service.weibo.WeiboService;
import com.lxinet.jeesns.utils.MemberUtil;
import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.model.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2016/11/22.
 */
@Controller("mamageWeiboController")
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class WeiboController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/weibo/";
    @Resource
    private WeiboService weiboService;

    @RequestMapping("${jeesns.managePath}/weibo/index")
    @Before(AdminLoginInterceptor.class)
    public String index(@RequestParam(value = "key",required = false,defaultValue = "") String key, Model model) {
        Page page = new Page(request);
        Result result = weiboService.listByPage(page,0,0,key);
        model.addAttribute("model", result);
        return MANAGE_FTL_PATH + "index";
    }

    @RequestMapping(value = "${jeesns.managePath}/weibo/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@PathVariable("id") int id) {
        Member loginMember = MemberUtil.getLoginMember(request);
        return new Result(weiboService.delete(request, loginMember,id));
    }
}
