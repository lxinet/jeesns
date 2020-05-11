package com.lxinet.jeesns.web.api;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.exception.ParamException;
import com.lxinet.jeesns.core.invoke.JeesnsInvoke;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.*;
import com.lxinet.jeesns.interceptor.UserLoginInterceptor;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.member.MemberService;
import com.lxinet.jeesns.service.member.MessageService;
import com.lxinet.jeesns.service.system.ActionLogService;
import com.lxinet.jeesns.service.system.ConfigService;
import com.lxinet.jeesns.utils.ConfigUtil;
import com.lxinet.jeesns.utils.JwtUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author zhangchuanzhao
 * @date 2020/5/8 12:46
 */
@RestController("apiMemberController")
@RequestMapping("/api/member")
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
    @Resource
    private JwtUtil jwtUtil;
    private static final String EXT_CARDKEY_SERVICE = "extCardkeyService";


    @PostMapping(value = "/login")
    public Result<Member> login(Member member){
        Result result = new Result(memberService.login(member,request));
        result.setCode(0);
        return result;
    }


    @PostMapping(value = "/register")
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

    @PostMapping(value = "/active")
    public Result active(String randomCode){
        Member loginMember = jwtUtil.getMember(request);
        return memberService.active(loginMember,randomCode,request);
    }

    @PostMapping(value = "/forgetpwd")
    public Result forgetpwd(String name, String email){
        return memberService.forgetpwd(name, email, request);
    }

    @GetMapping(value = "/resetpwd")
    public String resetpwd(String email,String token,Model model){
        model.addAttribute("email",email);
        model.addAttribute("token",token);
        return MEMBER_FTL_PATH + "/resetpwd";
    }

    @PostMapping(value = "/resetpwd")
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


    @PostMapping(value = "/editBaseInfo")
    public Result editBaseInfo(String name, String sex, String introduce){
        Member loginMember = jwtUtil.getMember(request);
        return memberService.editBaseInfo(loginMember,name,sex,introduce);
    }

    @PostMapping(value = "/editOtherInfo")
    public Result editOtherInfo(String birthday, String qq, String wechat, String contactPhone,
                                     String contactEmail, String website){
        Member loginMember = jwtUtil.getMember(request);
        return memberService.editOtherInfo(loginMember,birthday,qq,wechat,contactPhone,contactEmail,website);
    }

    @PostMapping(value = "/password")
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
        Member loginMember = jwtUtil.getMember(request);
        return memberService.changepwd(loginMember,oldPassword,newPassword);
    }

    /**
     * 关注、取消关注
     * @param followWhoId
     * @return
     */
    @PostMapping(value = "/follows/{followWhoId}")
    public Result follows(@PathVariable(value = "followWhoId") Integer followWhoId){
        Member loginMember = jwtUtil.getMember(request);
        return memberService.follows(loginMember,followWhoId);
    }

    /**
     * 查询是否已关注该用户
     * @param followWhoId
     * @return
     */
    @GetMapping(value = "/isFollowed/{followWhoId}")
    public Result isFollowed(@PathVariable(value = "followWhoId") Integer followWhoId){
        Member loginMember = jwtUtil.getMember(request);
        return memberService.isFollowed(loginMember,followWhoId);
    }



    /**
     * 获取联系人
     * @return
     */
    @GetMapping(value = "/listContactMembers")
    public Result listContactMembers(){
        Page page = new Page(request);
        Member loginMember = jwtUtil.getMember(request);
        //获取联系人
        Result contactMembers = memberService.listContactMembers(page, loginMember.getId());
        return contactMembers;
    }

    /**
     * 获取聊天记录
     * @param memberId
     * @return
     */
    @GetMapping(value = "/messageRecords/{memberId}")
    public Result messageRecords(@PathVariable("memberId") Integer memberId){
        Page page = new Page(request);
        Member loginMember = jwtUtil.getMember(request);
        Result messageRecords = messageService.messageRecords(page, memberId, loginMember.getId());
        return messageRecords;
    }

    /**
     * 发送信息窗口
     * @param memberId
     * @param model
     * @return
     */
    @GetMapping(value = "/sendMessageBox")
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
    @PostMapping(value = "/sendMessage")
    public Result sendMessage(String content,Integer memberId){
        Member loginMember = jwtUtil.getMember(request);
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
    @GetMapping(value = "/systemMessage")
    public String systemMessage(Model model){
        Page page = new Page(request);
        Member loginMember = jwtUtil.getMember(request);
        loginMember = memberService.findById(loginMember.getId());
        Result messageModel = messageService.systemMessage(page, loginMember.getId(),request.getContextPath());
        model.addAttribute("member",loginMember);
        model.addAttribute("messageModel",messageModel);
        return MEMBER_FTL_PATH + "systemMessage";
    }

    @GetMapping("/cdkRecharge")
    @Before(UserLoginInterceptor.class)
    public String recharge(Model model){
        Member loginMember = jwtUtil.getMember(request);
        Member findMember = memberService.findById(loginMember.getId());
        model.addAttribute("member", findMember);
        return MEMBER_FTL_PATH + "cdkRecharge";
    }

    @PostMapping("/cdkRecharge")
    public Result recharge(@RequestParam("cardkeyNo") String cardkeyNo){
        ValidUtill.checkIsBlank(cardkeyNo, "卡号不能为空");
        Member loginMember = jwtUtil.getMember(request);
        boolean result = (boolean) JeesnsInvoke.invoke(EXT_CARDKEY_SERVICE, "recharge", cardkeyNo, loginMember.getId());
        return new Result(result);
    }


    @PostMapping("/validSuperMember/{name}")
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
