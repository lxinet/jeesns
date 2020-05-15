package cn.jeesns.web.front;

import cn.jeesns.model.system.ActionLog;
import cn.jeesns.service.system.ActionLogService;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.model.Page;
import cn.jeesns.core.utils.JeesnsConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 动态
 * Created by zchuanzhao on 2017/3/8.
 */
@Controller("frontActionController")
@RequestMapping("/action/")
public class ActionController extends BaseController {
    @Resource
    private ActionLogService actionLogService;
    @Resource
    private JeesnsConfig jeesnsConfig;

    @RequestMapping("list")
    public String list(Model model){
        Page page = new Page(request);
        Result<ActionLog> actionList = actionLogService.memberActionLog(page,0);
        model.addAttribute("model", actionList);
        return jeesnsConfig.getFrontTemplate() + "/action/list";
    }


}
