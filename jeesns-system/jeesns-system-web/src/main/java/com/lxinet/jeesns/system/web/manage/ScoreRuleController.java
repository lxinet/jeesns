package com.lxinet.jeesns.system.web.manage;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.MemberToken;
import com.lxinet.jeesns.core.web.BaseController;
import com.lxinet.jeesns.system.model.ScoreRule;
import com.lxinet.jeesns.system.service.IScoreRuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/3/24.
 */
@Controller
@RequestMapping("/${managePath}/sys/scoreRule/")
public class ScoreRuleController extends BaseController {
    @Resource
    private IScoreRuleService scoreRuleService;

    @RequestMapping("list")
    @ResponseBody
    public Object actionList(){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        List<ScoreRule> list = scoreRuleService.list();
        return list;
    }

    @RequestMapping("info")
    @ResponseBody
    public Object info(Integer id){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        return scoreRuleService.findById(id);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(ScoreRule scoreRule){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        return scoreRuleService.update(scoreRule);
    }

    @RequestMapping(value = "enabled",method = RequestMethod.GET)
    @ResponseBody
    public Object enabled(Integer id){
        ResponseModel<MemberToken> validMemberTokenModel = validMemberToken();
        if (validMemberTokenModel.getCode() == -1){
            return validMemberTokenModel;
        }
        ResponseModel responseModel = scoreRuleService.enabled(id);
        if(responseModel.getCode() == 0){
            responseModel.setCode(1);
        }
        return responseModel;
    }

}
