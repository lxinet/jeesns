package cn.jeesns.web.api;

import cn.jeesns.model.system.ActionLog;
import cn.jeesns.service.system.ActionLogService;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.dto.Result;
import cn.jeesns.core.model.Page;
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
