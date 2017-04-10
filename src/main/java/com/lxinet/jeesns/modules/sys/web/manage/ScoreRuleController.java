package com.lxinet.jeesns.modules.sys.web.manage;

import com.lxinet.jeesns.core.annotation.Before;
import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.interceptor.AdminLoginInterceptor;
import com.lxinet.jeesns.core.web.BaseController;
import com.lxinet.jeesns.modules.sys.entity.Action;
import com.lxinet.jeesns.modules.sys.entity.ActionLog;
import com.lxinet.jeesns.modules.sys.entity.ScoreRule;
import com.lxinet.jeesns.modules.sys.service.IActionLogService;
import com.lxinet.jeesns.modules.sys.service.IActionService;
import com.lxinet.jeesns.modules.sys.service.IScoreRuleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/3/24.
 */
@Controller
@RequestMapping("/${managePath}/sys/scoreRule/")
@Before(AdminLoginInterceptor.class)
public class ScoreRuleController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/sys/scoreRule/";
    @Resource
    private IScoreRuleService scoreRuleService;

    @RequestMapping("list")
    public String actionList(Model model){
        List<ScoreRule> list = scoreRuleService.list();
        model.addAttribute("list",list);
        return MANAGE_FTL_PATH + "list";
    }

    @RequestMapping("edit/{id}")
    public String find(@PathVariable("id") Integer id, Model model){
        ScoreRule scoreRule = scoreRuleService.findById(id);
        model.addAttribute("scoreRule",scoreRule);
        return MANAGE_FTL_PATH + "edit";
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(ScoreRule scoreRule){
        ResponseModel responseModel = scoreRuleService.update(scoreRule);
        if(responseModel.getCode() == 0){
            responseModel.setCode(3);
        }
        return responseModel;
    }

    @RequestMapping(value = "enabled/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object enabled(@PathVariable("id") Integer id){
        ResponseModel responseModel = scoreRuleService.enabled(id);
        if(responseModel.getCode() == 0){
            responseModel.setCode(1);
        }
        return responseModel;
    }

}
