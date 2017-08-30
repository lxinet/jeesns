package com.lxinet.jeesns.web.manage;

import com.lxinet.jeesns.model.member.MemberToken;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.web.base.BaseController;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.member.IMemberService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2016/11/22.
 */
@Controller("manageMemberController")
@RequestMapping("/")
public class MemberController extends BaseController {
    @Resource
    private IMemberService memberService;


    /**
     * 管理员列表
     * @param key
     * @return
     */
    @RequestMapping(value = "${managePath}/member/manager/list",method = RequestMethod.GET)
    @ResponseBody
    public Object managerList(String key) {
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        Member loginMember = memberService.findById(validMemberTokenModel.getData().getMemberId());
        if(loginMember.getIsAdmin() == 1){
            return new ResponseModel(-1, "没有权限");
        }
        Page page = new Page(request);
        ResponseModel responseModel = memberService.managerList(page,key);
        return responseModel;
    }


    /**
     * 授权管理员
     * @param name
     * @return
     */
    @RequestMapping(value = "${managePath}/member/manager/save",method = RequestMethod.POST)
    @ResponseBody
    public Object managerSave(String name) {
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        Member loginMember = memberService.findById(validMemberTokenModel.getData().getMemberId());
        if(loginMember.getId() != 1 && loginMember.getIsAdmin() == 1){
            return new ResponseModel(-1,"没有权限");
        }
        //管理员授权，只能授权普通管理员
        return memberService.managerAdd(loginMember, name);
    }

    /**
     * 取消管理员
     * @param id
     * @return
     */
    @RequestMapping(value = "${managePath}/member/manager/cancel",method = RequestMethod.GET)
    @ResponseBody
    public Object managerCancel(@Param("id") Integer id) {
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        if(id == null){
            return new ResponseModel(-1,"参数错误");
        }
        Member loginMember = memberService.findById(validMemberTokenModel.getData().getMemberId());
        if(loginMember.getIsAdmin() == 1){
            return new ResponseModel(-1,"没有权限");
        }
        return memberService.managerCancel(loginMember, id);
    }


    @RequestMapping("${managePath}/member/list")
    @ResponseBody
    public Object list(String key) {
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        Page page = new Page(request);
        ResponseModel responseModel = memberService.listByPage(page,key);
        return responseModel;
    }


    /**
     * 会员启用、禁用操作
     */
    @RequestMapping(value = "${managePath}/member/isenable", method = RequestMethod.GET)
    @ResponseBody
    public ResponseModel isenable(@Param("id") int id) {
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        return memberService.isenable(id);
    }

    @RequestMapping(value = "${managePath}/member/delete", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@Param("id") int id) {
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        return memberService.delete(id);
    }


    @RequestMapping(value = "${managePath}/member/changepwd", method = RequestMethod.POST)
    @ResponseBody
    public Object changepwd(int id, String password) {
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        Member loginMember = memberService.findById(validMemberTokenModel.getData().getMemberId());
        return memberService.changepwd(loginMember,id, password);
    }

}
