package com.lxinet.jeesns.web.api;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.Result;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.model.system.ActionLog;
import com.lxinet.jeesns.service.system.ActionLogService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangchuanzhao
 * @date 2020/5/8 12:46
 */
@RestController("apiActionController")
@RequestMapping("/api/action/")
public class ActionController extends BaseController {
    @Resource
    private ActionLogService actionLogService;

    @GetMapping("list")
    public Result list(){
        Page page = new Page(request);
        Result<ActionLog> result = actionLogService.memberActionLog(page,0);
        return result;
    }


}
