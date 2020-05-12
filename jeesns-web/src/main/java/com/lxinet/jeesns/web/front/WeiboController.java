package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.service.member.MemberService;
import com.lxinet.jeesns.service.weibo.WeiboCommentService;
import com.lxinet.jeesns.service.weibo.WeiboService;
import com.lxinet.jeesns.utils.MemberUtil;
import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.exception.NotFountException;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.Const;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.utils.ValidLoginUtill;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.weibo.Weibo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/12/20.
 */
@Controller("frontWeiboController")
@RequestMapping("/${jeesns.weiboPath}")
public class WeiboController extends BaseController {
    @Resource
    private WeiboService weiboService;
    @Resource
    private WeiboCommentService weiboCommentService;
    @Resource
    private MemberService memberService;
    @Resource
    private JeesnsConfig jeesnsConfig;

    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result publish(String content, String pictures){
        Member loginMember = MemberUtil.getLoginMember(request);
        return new Result(weiboService.save(request, loginMember,content, pictures));
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(@RequestParam(value = "key",required = false,defaultValue = "") String key, Model model){
        Page page = new Page(request);
        Member loginMember = MemberUtil.getLoginMember(request);
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        Result result = weiboService.listByPage(page,0,loginMemberId,key);
        model.addAttribute("model", result);
        List<Weibo> hotList = weiboService.hotList(loginMemberId);
        model.addAttribute("hotList",hotList);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/weibo/list";
    }

    @RequestMapping(value = "/detail/{weiboId}",method = RequestMethod.GET)
    public String detail(@PathVariable("weiboId") Integer weiboId, Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        Weibo weibo = weiboService.findById(weiboId,loginMemberId);
        if (weibo == null){
            throw new NotFountException("微博不存在");
        }
        weibo.setMember(memberService.findById(weibo.getMemberId()));
        model.addAttribute("weibo",weibo);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/weibo/detail";
    }

    @RequestMapping(value="/delete/{weiboId}",method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result delete(@PathVariable("weiboId") Integer weiboId){
        Member loginMember = MemberUtil.getLoginMember(request);
        boolean flag = weiboService.userDelete(request, loginMember,weiboId);
        Result result = new Result(flag);
        if(result.getCode() >= 0){
            result.setCode(2);
            result.setUrl(Const.WEIBO_PATH + "/list");
        }
        return result;
    }


    @RequestMapping(value="/comment/{weiboId}",method = RequestMethod.POST)
    @ResponseBody
    public Result comment(@PathVariable("weiboId") Integer weiboId, String content, Integer weiboCommentId){
        Member loginMember = MemberUtil.getLoginMember(request);
        ValidLoginUtill.checkLogin(loginMember);
        return new Result(weiboCommentService.save(loginMember,content,weiboId,weiboCommentId));
    }

    @RequestMapping(value="/commentList/{weiboId}.json",method = RequestMethod.GET)
    @ResponseBody
    public Result commentList(@PathVariable("weiboId") Integer weiboId){
        Page page = new Page(request);
        return weiboCommentService.listByWeibo(page,weiboId);
    }

    @RequestMapping(value="/favor/{weiboId}",method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result favor(@PathVariable("weiboId") Integer weiboId){
        Member loginMember = MemberUtil.getLoginMember(request);
        Result result = weiboService.favor(loginMember,weiboId);
        return result;
    }


    @RequestMapping(value = "/topic/{topicName}",method = RequestMethod.GET)
    public String listByTopic(@PathVariable(value = "topicName") String topicName, Model model){
        Page page = new Page(request);
        try {
            topicName = URLDecoder.decode(topicName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        Result result = null;
        result = weiboService.listByTopic(page,loginMemberId,topicName);
        model.addAttribute("model", result);
        List<Weibo> hotList = weiboService.hotList(loginMemberId);
        model.addAttribute("hotList",hotList);
        model.addAttribute("loginUser", loginMember);
        model.addAttribute("topicName", topicName);
        return jeesnsConfig.getFrontTemplate() + "/weibo/topic";
    }

}
