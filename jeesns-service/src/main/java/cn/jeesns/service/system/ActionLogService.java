package cn.jeesns.service.system;

import cn.jeesns.core.dto.Result;
import cn.jeesns.core.model.Page;
import cn.jeesns.core.service.BaseService;
import cn.jeesns.dao.system.IActionLogDao;
import cn.jeesns.model.system.Action;
import cn.jeesns.model.system.ActionLog;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
@Service("actionLogService")
public class ActionLogService extends BaseService<ActionLog> {
    @Resource
    private ActionService actionService;
    @Resource
    private IActionLogDao actionLogDao;

    public Result<ActionLog> listByPage(Page page, Integer memberId) {
        List<ActionLog> list = actionLogDao.list(page, memberId);
        Result model = new Result(0, page);
        model.setData(list);
        return model;
    }

    public Result<ActionLog> memberActionLog(Page page, Integer memberId) {
        List<ActionLog> list = actionLogDao.memberActionLog(page, memberId);
        Result model = new Result(0, page);
        model.setData(list);
        return model;
    }

    @Override
    public ActionLog findById(Integer id) {
        return super.findById(id);
    }

    public void save(String actionIp, Integer memberId, Integer actionId) {
        this.save(actionIp,memberId,actionId,"",0,0);
    }

    public void save(String actionIp, Integer memberId, Integer actionId, String remark) {
        this.save(actionIp,memberId,actionId,remark,0,0);
    }

    public void save(String actionIp, Integer memberId, Integer actionId, String remark, Integer type, Integer foreignId) {
        Action action = actionService.findById(actionId);
        if(action != null){
            if(action.getStatus() == 0){
                ActionLog actionLog = new ActionLog(memberId,actionId,remark,actionIp,type,foreignId);
                super.save(actionLog);
            }
        }
    }

}
