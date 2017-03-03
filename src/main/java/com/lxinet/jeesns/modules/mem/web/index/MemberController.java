package com.lxinet.jeesns.modules.mem.web.index;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.annotation.Clear;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.core.utils.*;
import com.lxinet.jeesns.core.web.BaseController;
import com.lxinet.jeesns.modules.mem.entity.Member;
import com.lxinet.jeesns.modules.mem.service.IMemberService;
import com.lxinet.jeesns.modules.sys.entity.ActionLog;
import com.lxinet.jeesns.modules.sys.service.IActionLogService;
import com.lxinet.jeesns.modules.sys.service.IConfigService;
import com.lxinet.jeesns.modules.weibo.service.IWeiboService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * Created by zchuanzhao on 2016/11/22.
 */
@Controller("memberIndexController")
@RequestMapping("/member")
@Before(UserLoginInterceptor.class)
public class MemberController extends BaseController {
    private static final String MEMBER_FTL_PATH = "/member/";
    @Resource
    private IMemberService memberService;
    @Resource
    private IWeiboService weiboService;
    @Resource
    private IConfigService configService;
    @Resource
    private IActionLogService actionLogService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @Clear
    public String login(Model model,@RequestParam(value = "redirectUrl",required = false,defaultValue = "") String redirectUrl){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember != null){
            return "redirect:/member/";
        }
        model.addAttribute("redirectUrl",redirectUrl);
        return MEMBER_FTL_PATH + "/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    @Clear
    public ResponseModel<Member> login(Member member,@RequestParam(value = "redirectUrl",required = false,defaultValue = "") String redirectUrl){
        ResponseModel responseModel = memberService.login(member,request);
        if (StringUtils.isNotEmpty(redirectUrl) && responseModel.getCode() >= 0){
            responseModel.setCode(3);
            responseModel.setUrl(redirectUrl);
        }
        return responseModel;
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    @Clear
    public String register(){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember != null){
            return "redirect:/member/";
        }
        return MEMBER_FTL_PATH + "/register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    @Clear
    public ResponseModel register(Member member,String repassword){
        Map<String,String> config = configService.getConfigToMap();
        if("0".equals(config.get(ConfigUtil.MEMBER_REGISTER_OPEN))){
            return new ResponseModel(-1,"注册功能已关闭");
        }
        if(member == null){
            return new ResponseModel(-1,"参数错误");
        }
        if(member.getName().length() < 6){
            return new ResponseModel(-1,"用户名长度最少6位");
        }
        if(!StringUtils.isEmail(member.getEmail())){
            return new ResponseModel(-1,"邮箱格式错误");
        }
        if(member.getPassword().length() < 6){
            return new ResponseModel(-1,"密码长度最少6位");
        }
        if(!member.getPassword().equals(repassword)){
            return new ResponseModel(-1,"两次密码输入不一致");
        }
        return memberService.register(member,request);
    }

    @RequestMapping(value = "/active",method = RequestMethod.GET)
    @Clear
    public String active(){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return "redirect:/member/login";
        }
        return MEMBER_FTL_PATH + "/active";
    }

    @RequestMapping(value = "/active",method = RequestMethod.POST)
    @Clear
    @ResponseBody
    public ResponseModel active(String randomCode){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        return memberService.active(loginMember,randomCode,request);
    }

    @RequestMapping(value = "/sendEmailActiveValidCode",method = RequestMethod.GET)
    @Clear
    @ResponseBody
    public ResponseModel sendEmailActiveValidCode(){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResponseModel(-1,"请先登录");
        }
        return memberService.sendEmailActiveValidCode(loginMember);
    }

    @RequestMapping(value = "/forgetpwd",method = RequestMethod.GET)
    @Clear
    public String forgetpwd(){
        return MEMBER_FTL_PATH + "/forgetpwd";
    }

    @RequestMapping(value = "/forgetpwd",method = RequestMethod.POST)
    @Clear
    @ResponseBody
    public ResponseModel forgetpwd(String name,String email){
        return memberService.forgetpwd(name,email);
    }

    @RequestMapping(value = "/resetpwd",method = RequestMethod.GET)
    @Clear
    public String resetpwd(String email,String token,Model model){
        model.addAttribute("email",email);
        model.addAttribute("token",token);
        return MEMBER_FTL_PATH + "/resetpwd";
    }

    @RequestMapping(value = "/resetpwd",method = RequestMethod.POST)
    @Clear
    @ResponseBody
    public ResponseModel resetpwd(String email,String token,String password,String repassword){
        if(StringUtils.isEmpty(password)){
            return new ResponseModel(-1,"新密码不能为空");
        }
        if(password.length() < 6){
            return new ResponseModel(-1,"密码不能少于6个字符");
        }
        if(!password.equals(repassword)){
            return new ResponseModel(-1,"两次密码输入不一致");
        }
        return memberService.resetpwd(email,token,password,request);
    }


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        Page page = new Page(request);
        ResponseModel<ActionLog> list = actionLogService.memberActionLog(page,loginMemberId);
        model.addAttribute("actionLogModel",list);
        return MEMBER_FTL_PATH + "index";
    }


    @RequestMapping(value = "/editInfo",method = RequestMethod.GET)
    public String editInfo(){

        return MEMBER_FTL_PATH + "editInfo";
    }

    @RequestMapping(value = "/editBaseInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel editBaseInfo(String name,String sex,String introduce){
        Member loginMember = MemberUtil.getLoginMember(request);
        return memberService.editBaseInfo(loginMember,name,sex,introduce);
    }

    @RequestMapping(value = "/editOtherInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel editOtherInfo(String birthday,String qq,String wechat,String contactPhone,
                                       String contactEmail,String website){
        Member loginMember = MemberUtil.getLoginMember(request);
        return memberService.editOtherInfo(loginMember,birthday,qq,wechat,contactPhone,contactEmail,website);
    }

    @RequestMapping(value = "/avatar",method = RequestMethod.GET)
    public String avatar(){
        return MEMBER_FTL_PATH + "avatar";
    }

    @RequestMapping(value = "/password",method = RequestMethod.GET)
    public String password(){
        return MEMBER_FTL_PATH + "password";
    }

    @RequestMapping(value = "/password",method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel password(String oldPassword, String newPassword, String renewPassword){
        if(StringUtils.isEmpty(oldPassword)){
            return new ResponseModel(-1,"旧密码不能为空");
        }
        if(StringUtils.isEmpty(newPassword)){
            return new ResponseModel(-1,"新密码不能为空");
        }
        if(!newPassword.equals(renewPassword)){
            return new ResponseModel(-1,"两次密码输入不一致");
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        return memberService.changepwd(loginMember,oldPassword,newPassword);
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @Clear
    public String logout(){
        MemberUtil.setLoginMember(request,null);
        return "redirect:/member/login";
    }

    /**
     * 关注、取消关注
     * @param followWhoId
     * @return
     */
    @RequestMapping(value = "/follows/{followWhoId}",method = RequestMethod.GET)
    @Clear
    @ResponseBody
    public Object follows(@PathVariable(value = "followWhoId") Integer followWhoId){
        Member loginMember = MemberUtil.getLoginMember(request);
        return memberService.follows(loginMember,followWhoId);
    }

    /**
     * 查询是否已关注该用户
     * @param followWhoId
     * @return
     */
    @RequestMapping(value = "/isFollowed/{followWhoId}",method = RequestMethod.GET)
    @Clear
    @ResponseBody
    public Object isFollowed(@PathVariable(value = "followWhoId") Integer followWhoId){
        Member loginMember = MemberUtil.getLoginMember(request);
        return memberService.isFollowed(loginMember,followWhoId);
    }
}
