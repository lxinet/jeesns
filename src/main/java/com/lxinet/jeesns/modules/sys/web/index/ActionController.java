package com.lxinet.jeesns.modules.sys.web.index;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.core.web.BaseController;
import com.lxinet.jeesns.modules.sys.entity.ActionLog;
import com.lxinet.jeesns.modules.sys.service.IActionLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 动态
 * Created by zchuanzhao on 2017/3/8.
 */
@Controller("indexActionController")
@RequestMapping("/action/")
public class ActionController extends BaseController {
    private static final String FTL_PATH = "/index/action/";
    @Resource
    private IActionLogService actionLogService;

    @RequestMapping("list")
    public String list(Model model){
        Page page = new Page(request);
        ResponseModel<ActionLog> actionList = actionLogService.memberActionLog(page,0);
        model.addAttribute("model", actionList);
        return FTL_PATH + "/list";
    }


}
