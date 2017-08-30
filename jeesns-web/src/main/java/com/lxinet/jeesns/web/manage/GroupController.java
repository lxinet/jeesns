package com.lxinet.jeesns.web.manage;

import com.lxinet.jeesns.model.member.MemberToken;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.service.group.IGroupService;
import com.lxinet.jeesns.web.base.BaseController;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.member.IMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 16/12/23.
 */
@Controller("manageGroupController")
@RequestMapping("/")
public class GroupController extends BaseController{
    @Resource
    private IGroupService groupService;
    @Resource
    private IMemberService memberService;

    @RequestMapping(value = "${managePath}/group/list")
    @ResponseBody
    public Object list(@RequestParam(value = "status",required = false,defaultValue = "-1") Integer status, String key) {
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        Page page = new Page(request);
        ResponseModel responseModel = groupService.listByPage(status,page,key);
        return responseModel;
    }

    @RequestMapping(value = "${managePath}/group/delete",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@RequestParam("id") int id){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        Member loginMember = memberService.findById(validMemberTokenModel.getData().getMemberId());
        ResponseModel response = groupService.delete(loginMember,id);
        return response;
    }

    @RequestMapping(value = "${managePath}/group/changeStatus",method = RequestMethod.GET)
    @ResponseBody
    public Object changeStatus(@RequestParam("id") int id){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        ResponseModel response = groupService.changeStatus(id);
        return response;
    }



}
