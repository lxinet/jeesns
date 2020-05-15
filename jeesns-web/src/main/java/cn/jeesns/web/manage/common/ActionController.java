package cn.jeesns.web.manage.common;

import cn.jeesns.interceptor.AdminLoginInterceptor;
import cn.jeesns.model.system.Action;
import cn.jeesns.model.system.ActionLog;
import cn.jeesns.service.system.ActionLogService;
import cn.jeesns.service.system.ActionService;
import cn.jeesns.core.annotation.Before;

import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.model.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
@Controller
@RequestMapping("/${jeesns.managePath}/system/action/")
@Before(AdminLoginInterceptor.class)
public class ActionController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/system/action/";
    @Resource
    private ActionService actionService;
    @Resource
    private ActionLogService actionLogService;

    @RequestMapping("list")
    public String actionList(Model model){
        List<Action> list = actionService.list();
        model.addAttribute("list",list);
        return MANAGE_FTL_PATH + "list";
    }

    @RequestMapping("edit/{id}")
    public String find(@PathVariable("id") Integer id, Model model){
        Action action = actionService.findById(id);
        model.addAttribute("action",action);
        return MANAGE_FTL_PATH + "edit";
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(Action action){
        return new Result(actionService.update(action));
    }

    @RequestMapping(value = "isenable/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Result isenable(@PathVariable("id") Integer id){
        return new Result(actionService.isenable(id));
    }

    @RequestMapping("actionLogList")
    public String actionLogList(@RequestParam(value = "memberId",required = false) Integer memberId, Model model){
        Page page = new Page(request);
        if(memberId == null){
            memberId = 0;
        }
        Result<ActionLog> list = actionLogService.listByPage(page,memberId);
        model.addAttribute("model",list);
        return MANAGE_FTL_PATH + "actionLogList";
    }

    @RequestMapping("memberActionLog")
    public String memberActionLog(@RequestParam(value = "memberId",required = false) Integer memberId, Model model){
        Page page = new Page(request);
        if(memberId == null){
            memberId = 0;
        }
        Result<ActionLog> list = actionLogService.memberActionLog(page,memberId);
        model.addAttribute("model",list);
        return MANAGE_FTL_PATH + "memberActionLog";
    }


}
