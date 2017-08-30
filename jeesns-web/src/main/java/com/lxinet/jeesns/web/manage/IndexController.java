package com.lxinet.jeesns.web.manage;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.model.member.MemberToken;
import com.lxinet.jeesns.core.utils.TokenUtil;
import com.lxinet.jeesns.service.common.ICommonService;
import com.lxinet.jeesns.core.utils.Const;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.web.base.BaseController;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.member.IMemberService;
import com.lxinet.jeesns.service.member.IMemberTokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zchuanzhao on 16/9/26.
 */
@Controller("manageIndexController")
@RequestMapping("/${managePath}")
public class IndexController extends BaseController {
    private static final String FTL_PATH = "/manage";
    @Resource
    private IMemberService memberService;
    @Resource
    private JeesnsConfig jeesnsConfig;
    @Resource
    private ICommonService commonService;
    @Resource
    private IMemberTokenService memberTokenService;

    @RequestMapping("/version")
    @ResponseBody
    public Object version(){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        Map map = new HashMap();
        map.put("systemVersion", Const.SYSTEM_VERSION);
        map.put("systemName", Const.SYSTEM_NAME);
        map.put("systemUpdateTime", Const.SYSTEM_UPDATE_TIME);
        return map;
    }

    /**
     * 提交登录信息
     * @param member
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Object login(Member member){
        if(member == null){
            return new ResponseModel(-1,"参数错误");
        }
        if(StringUtils.isEmpty(member.getName())){
            return new ResponseModel(-1,"用户名不能为空");
        }
        if(StringUtils.isEmpty(member.getPassword())){
            return new ResponseModel(-1,"密码不能为空");
        }
        Member loginMember = memberService.manageLogin(member,request);
        if(loginMember != null){
            //生成token
            String token = TokenUtil.getToken();
            memberTokenService.save(loginMember.getId(),token);
            Map<String,Object> returnMap = new HashMap<>();
            returnMap.put("user",loginMember);
            returnMap.put("token",token);
            return new ResponseModel(2,"登录成功",returnMap);
        }else {
            return new ResponseModel(-1,"用户名或密码错误");
        }
    }
}
