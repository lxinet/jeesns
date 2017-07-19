package com.lxinet.jeesns.system.web.manage;

import com.lxinet.jeesns.core.model.MemberToken;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.web.BaseController;
import com.lxinet.jeesns.system.model.Action;
import com.lxinet.jeesns.system.model.ActionLog;
import com.lxinet.jeesns.system.service.IActionLogService;
import com.lxinet.jeesns.system.service.IActionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
@Controller
@RequestMapping("/${managePath}/sys/action/")
public class ActionController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/sys/action/";
    @Resource
    private IActionService actionService;
    @Resource
    private IActionLogService actionLogService;

    @RequestMapping("list")
    @ResponseBody
    public Object actionList(){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        List<Action> list = actionService.list();
        return list;
    }

    @RequestMapping("info")
    @ResponseBody
    public Object info(Integer id){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        return actionService.findById(id);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(Action action){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        return actionService.update(action);
    }

    @RequestMapping(value = "isenable",method = RequestMethod.GET)
    @ResponseBody
    public Object isenable(Integer id){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        return actionService.isenable(id);
    }

    @RequestMapping("actionLogList")
    @ResponseBody
    public Object actionLogList(@RequestParam(value = "memberId",required = false) Integer memberId){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        Page page = new Page(request);
        if(memberId == null){
            memberId = 0;
        }
        ResponseModel<ActionLog> list = actionLogService.listByPage(page,memberId);
        return list;
    }

    @RequestMapping("memberActionLog")
    @ResponseBody
    public Object memberActionLog(@RequestParam(value = "memberId",required = false) Integer memberId){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        Page page = new Page(request);
        if(memberId == null){
            memberId = 0;
        }
        ResponseModel<ActionLog> list = actionLogService.memberActionLog(page,memberId);
        return list;
    }


}
