package cn.jeesns.web.manage.member;

import cn.jeesns.interceptor.AdminLoginInterceptor;
import cn.jeesns.model.system.ScoreRule;
import cn.jeesns.service.system.ScoreRuleService;
import cn.jeesns.core.annotation.Before;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.dto.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/3/24.
 */
@Controller
@RequestMapping("/${jeesns.managePath}/system/scoreRule/")
@Before(AdminLoginInterceptor.class)
public class ScoreRuleController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/system/scoreRule/";
    @Resource
    private ScoreRuleService scoreRuleService;

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
    public Result update(ScoreRule scoreRule){
        return new Result(scoreRuleService.update(scoreRule));
    }

    @RequestMapping(value = "enabled/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Result enabled(@PathVariable("id") Integer id){
        return new Result(scoreRuleService.enabled(id));
    }

}
