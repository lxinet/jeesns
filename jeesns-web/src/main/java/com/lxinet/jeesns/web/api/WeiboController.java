package com.lxinet.jeesns.web.api;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.exception.NotFountException;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.Const;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.weibo.Weibo;
import com.lxinet.jeesns.service.weibo.WeiboCommentService;
import com.lxinet.jeesns.service.weibo.WeiboService;
import com.lxinet.jeesns.utils.JwtUtil;
import com.lxinet.jeesns.utils.ValidLoginUtill;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author zhangchuanzhao
 * @date 2020/5/8 12:45
 */
@RestController("apiWeiboController")
@RequestMapping("/api/${jeesns.weiboPath}")
public class WeiboController extends BaseController {
    @Resource
    private WeiboService weiboService;
    @Resource
    private WeiboCommentService weiboCommentService;
    @Resource
    private JeesnsConfig jeesnsConfig;
    @Resource
    private JwtUtil jwtUtil;

    @PostMapping("/publish")
    public Result publish(String content, String pictures){
        Member loginMember = jwtUtil.getMember(request);
        return new Result(weiboService.save(request, loginMember,content, pictures));
    }

    @GetMapping("/list")
    public String list(@RequestParam(value = "key",required = false,defaultValue = "") String key, Model model){
        Page page = new Page(request);
        Member loginMember = jwtUtil.getMember(request);
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        Result result = weiboService.listByPage(page,0,loginMemberId,key);
        model.addAttribute("model", result);
        List<Weibo> hotList = weiboService.hotList(loginMemberId);
        model.addAttribute("hotList",hotList);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/weibo/list";
    }

    @GetMapping(value = "/detail/{weiboId}")
    public String detail(@PathVariable("weiboId") Integer weiboId, Model model){
        Member loginMember = jwtUtil.getMember(request);
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        Weibo weibo = weiboService.findById(weiboId,loginMemberId);
        if (weibo == null){
            throw new NotFountException("微博不存在");
        }
        model.addAttribute("weibo",weibo);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/weibo/detail";
    }

    @GetMapping(value="/delete/{weiboId}")
    public Result delete(@PathVariable("weiboId") Integer weiboId){
        Member loginMember = jwtUtil.getMember(request);
        boolean flag = weiboService.userDelete(request, loginMember,weiboId);
        Result result = new Result(flag);
        if(result.getCode() >= 0){
            result.setCode(2);
            result.setUrl(Const.WEIBO_PATH + "/list");
        }
        return result;
    }


    @PostMapping(value="/comment/{weiboId}")
    public Result comment(@PathVariable("weiboId") Integer weiboId, String content, Integer weiboCommentId){
        Member loginMember = jwtUtil.getMember(request);
        ValidLoginUtill.checkLogin(loginMember);
        return new Result(weiboCommentService.save(loginMember,content,weiboId,weiboCommentId));
    }

    @GetMapping(value="/commentList/{weiboId}")
    public Result commentList(@PathVariable("weiboId") Integer weiboId){
        Page page = new Page(request);
        return weiboCommentService.listByWeibo(page,weiboId);
    }

    @GetMapping(value="/favor/{weiboId}")
    public Result favor(@PathVariable("weiboId") Integer weiboId){
        Member loginMember = jwtUtil.getMember(request);
        Result result = weiboService.favor(loginMember,weiboId);
        return result;
    }


    @GetMapping(value = "/topic/{topicName}")
    public String listByTopic(@PathVariable(value = "topicName") String topicName, Model model){
        Page page = new Page(request);
        try {
            topicName = URLDecoder.decode(topicName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Member loginMember = jwtUtil.getMember(request);
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
