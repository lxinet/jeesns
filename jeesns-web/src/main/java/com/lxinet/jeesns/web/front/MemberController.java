package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.invoke.JeesnsInvoke;
import com.lxinet.jeesns.service.member.MemberService;
import com.lxinet.jeesns.service.member.MessageService;
import com.lxinet.jeesns.service.system.ActionLogService;
import com.lxinet.jeesns.service.system.ConfigService;
import com.lxinet.jeesns.utils.MemberUtil;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.*;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.model.system.ActionLog;
import com.lxinet.jeesns.utils.ConfigUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by zchuanzhao on 2016/11/22.
 */
@Controller("memberIndexController")
@RequestMapping("/member")
public class MemberController extends BaseController {
    private static final String MEMBER_FTL_PATH = "/member/";
    @Resource
    private MemberService memberService;
    @Resource
    private ConfigService configService;
    @Resource
    private ActionLogService actionLogService;
    @Resource
    private MessageService messageService;
    @Resource
    private JeesnsConfig jeesnsConfig;
    private static final String EXT_CARDKEY_SERVICE = "extCardkeyService";

    @RequestMapping(value = "/login",method = RequestMethod.GET)
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
    public Result<Member> login(Member member, @RequestParam(value = "redirectUrl",required = false,defaultValue = "") String redirectUrl){
        Result result = new Result(memberService.login(member,request));
        result.setCode(3);
        if (StringUtils.isNotEmpty(redirectUrl) && result.getCode() >= 0){
            result.setUrl(redirectUrl);
        }else {
            result.setUrl(request.getContextPath() + "/member/");
        }
        return result;
    }

    @RequestMapping(value = {"/register","/reg/{superMemberId}"},method = RequestMethod.GET)
    public String register(@PathVariable(value = "superMemberId", required = false) Integer superMemberId,
                           Model model){
        if (superMemberId != null){
            Member superMember = memberService.findById(superMemberId);
            model.addAttribute("superMember", superMember);
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember != null){
            return "redirect:/member/";
        }
        return MEMBER_FTL_PATH + "/register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Result register(Member member, String repassword){
        Map<String,String> config = configService.getConfigToMap();
        if("0".equals(config.get(ConfigUtil.MEMBER_REGISTER_OPEN))){
            return new Result(-1,"注册功能已关闭");
        }
        if(member == null){
            throw new ParamException();
        }
        if(member.getName().length() < 6){
            return new Result(-1,"用户名长度最少6位");
        }
        if(!StringUtils.checkNickname(member.getName())){
            return new Result(-1,"用户名只能由中文、字母、数字、下划线(_)或者短横线(-)组成");
        }
        if(!StringUtils.isEmail(member.getEmail())){
            return new Result(-1,"邮箱格式错误");
        }
        if(member.getPassword().length() < 6){
            return new Result(-1,"密码长度最少6位");
        }
        if(!member.getPassword().equals(repassword)){
            return new Result(-1,"两次密码输入不一致");
        }
        return memberService.register(member,request);
    }

    @RequestMapping(value = "/active",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String active(){
        return MEMBER_FTL_PATH + "/active";
    }

    @RequestMapping(value = "/active",method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result active(String randomCode){
        Member loginMember = MemberUtil.getLoginMember(request);
        return memberService.active(loginMember,randomCode,request);
    }

    @RequestMapping(value = "/sendEmailActiveValidCode",method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result sendEmailActiveValidCode(){
        Member loginMember = MemberUtil.getLoginMember(request);
        return memberService.sendEmailActiveValidCode(loginMember, request);
    }

    @RequestMapping(value = "/forgetpwd",method = RequestMethod.GET)
    public String forgetpwd(){
        return MEMBER_FTL_PATH + "/forgetpwd";
    }

    @RequestMapping(value = "/forgetpwd",method = RequestMethod.POST)
    @ResponseBody
    public Result forgetpwd(String name, String email){
        return memberService.forgetpwd(name, email, request);
    }

    @RequestMapping(value = "/resetpwd",method = RequestMethod.GET)
    public String resetpwd(String email,String token,Model model){
        model.addAttribute("email",email);
        model.addAttribute("token",token);
        return MEMBER_FTL_PATH + "/resetpwd";
    }

    @RequestMapping(value = "/resetpwd",method = RequestMethod.POST)
    @ResponseBody
    public Result resetpwd(String email, String token, String password, String repassword){
        if(StringUtils.isEmpty(password)){
            return new Result(-1,"新密码不能为空");
        }
        if(password.length() < 6){
            return new Result(-1,"密码不能少于6个字符");
        }
        if(!password.equals(repassword)){
            return new Result(-1,"两次密码输入不一致");
        }
        return memberService.resetpwd(email,token,password,request);
    }


    @RequestMapping(value = "/",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String index(Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        Member member = memberService.findById(loginMember.getId());
        Page page = new Page(request);
        Result<ActionLog> list = actionLogService.memberActionLog(page,loginMember.getId());
        model.addAttribute("member",member);
        model.addAttribute("actionLogModel",list);
        return MEMBER_FTL_PATH + "index";
    }


    @RequestMapping(value = "/editInfo",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String editInfo(){
        return MEMBER_FTL_PATH + "editInfo";
    }

    @RequestMapping(value = "/editBaseInfo",method = RequestMethod.POST)
    @ResponseBody
    public Result editBaseInfo(String name, String sex, String introduce){
        Member loginMember = MemberUtil.getLoginMember(request);
        return memberService.editBaseInfo(loginMember,name,sex,introduce);
    }

    @RequestMapping(value = "/editOtherInfo",method = RequestMethod.POST)
    @ResponseBody
    public Result editOtherInfo(String birthday, String qq, String wechat, String contactPhone,
                                     String contactEmail, String website){
        Member loginMember = MemberUtil.getLoginMember(request);
        return memberService.editOtherInfo(loginMember,birthday,qq,wechat,contactPhone,contactEmail,website);
    }

    @RequestMapping(value = "/avatar",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String avatar(){
        return MEMBER_FTL_PATH + "avatar";
    }

    @RequestMapping(value = "/password",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String password(){
        return MEMBER_FTL_PATH + "password";
    }

    @RequestMapping(value = "/password",method = RequestMethod.POST)
    @ResponseBody
    public Result password(String oldPassword, String newPassword, String renewPassword){
        if(StringUtils.isEmpty(oldPassword)){
            return new Result(-1,"旧密码不能为空");
        }
        if(StringUtils.isEmpty(newPassword)){
            return new Result(-1,"新密码不能为空");
        }
        if(!newPassword.equals(renewPassword)){
            return new Result(-1,"两次密码输入不一致");
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        return memberService.changepwd(loginMember,oldPassword,newPassword);
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
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
    @ResponseBody
    public Result follows(@PathVariable(value = "followWhoId") Integer followWhoId){
        Member loginMember = MemberUtil.getLoginMember(request);
        return memberService.follows(loginMember,followWhoId);
    }

    /**
     * 查询是否已关注该用户
     * @param followWhoId
     * @return
     */
    @RequestMapping(value = "/isFollowed/{followWhoId}",method = RequestMethod.GET)
    @ResponseBody
    public Result isFollowed(@PathVariable(value = "followWhoId") Integer followWhoId){
        Member loginMember = MemberUtil.getLoginMember(request);
        return memberService.isFollowed(loginMember,followWhoId);
    }


    /**
     * 获取该登录会员的收件信息
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/message",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String message(@RequestParam(value = "mid",required = false,defaultValue = "-1") Integer memberId,Model model){
        Page page = new Page(request);
        Member loginMember = MemberUtil.getLoginMember(request);
        Integer loginMemberId = -1;
        if(loginMember != null){
            loginMemberId = loginMember.getId();
        }
        //获取联系人
//        ResponseModel contactMembers = messageService.listContactMembers(page, loginMemberId);
//        获取联系人
//        ResponseModel contactMembers = messageService.listContactMembers(page, memberId, loginMemberId);
//        model.addAttribute("model", contactMembers);
        return MEMBER_FTL_PATH + "message";
    }

    /**
     * 获取联系人
     * @return
     */
    @RequestMapping(value = "/listContactMembers",method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result listContactMembers(){
        Page page = new Page(request);
        Member loginMember = MemberUtil.getLoginMember(request);
        //获取联系人
        Result contactMembers = memberService.listContactMembers(page, loginMember.getId());
        return contactMembers;
    }

    /**
     * 获取聊天记录
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/messageRecords/{memberId}",method = RequestMethod.GET)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result messageRecords(@PathVariable("memberId") Integer memberId){
        Page page = new Page(request);
        Member loginMember = MemberUtil.getLoginMember(request);
        Result messageRecords = messageService.messageRecords(page, memberId, loginMember.getId());
        return messageRecords;
    }

    /**
     * 发送信息窗口
     * @param memberId
     * @param model
     * @return
     */
    @RequestMapping(value = "/sendMessageBox",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String sendMessageBox(@RequestParam(value = "mid") Integer memberId,Model model){
        if(memberId == null){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1000, Const.INDEX_ERROR_FTL_PATH);
        }
        Member findMember= memberService.findById(memberId);
        if(findMember == null){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model, -1005, Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("member", findMember);
        return MEMBER_FTL_PATH + "sendMessageBox";
    }

    /**
     * 发送信息
     * @param content
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/sendMessage",method = RequestMethod.POST)
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public Result sendMessage(String content,Integer memberId){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(memberId == null){
            return new Result(-1,"请选择发送对象");
        }
        Member findMember= memberService.findById(memberId);
        if(findMember == null){
            return new Result(-1,"会员不存在");
        }
        return messageService.sentMsg(loginMember.getId(), memberId, content);
    }

    /**
     * 系统信息
     * @param model
     * @return
     */
    @RequestMapping(value = "/systemMessage",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String systemMessage(Model model){
        Page page = new Page(request);
        Member loginMember = MemberUtil.getLoginMember(request);
        loginMember = memberService.findById(loginMember.getId());
        Result messageModel = messageService.systemMessage(page, loginMember.getId(),request.getContextPath());
        model.addAttribute("member",loginMember);
        model.addAttribute("messageModel",messageModel);
        return MEMBER_FTL_PATH + "systemMessage";
    }

    @GetMapping("/cdkRecharge")
    @Before(UserLoginInterceptor.class)
    public String recharge(Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        Member findMember = memberService.findById(loginMember.getId());
        model.addAttribute("member", findMember);
        return MEMBER_FTL_PATH + "cdkRecharge";
    }

    @PostMapping("/cdkRecharge")
    @Before(UserLoginInterceptor.class)
    @ResponseBody
    public Result recharge(@RequestParam("cardkeyNo") String cardkeyNo){
        ValidUtill.checkIsBlank(cardkeyNo, "卡号不能为空");
        Member loginMember = MemberUtil.getLoginMember(request);
        boolean result = (boolean) JeesnsInvoke.invoke(EXT_CARDKEY_SERVICE, "recharge", cardkeyNo, loginMember.getId());
        return new Result(result);
    }


    @PostMapping("/validSuperMember/{name}")
    @ResponseBody
    public Result validSuperMember(@PathVariable("name") String name){
        Member findMember = null;
        if (StringUtils.isNotBlank(name)){
            findMember = memberService.findByName(name);
            if (findMember == null){
                findMember = memberService.findByEmail(name);
                if (findMember == null){
                    findMember = memberService.findByPhone(name);
                }
            }
        }
        if (findMember != null){
            Result result = new Result(0,"");
            result.setData(findMember.getId());
            return result;
        }else {
            return new Result(-1, "上级会员不存在");
        }

    }

}
