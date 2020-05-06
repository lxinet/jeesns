package com.lxinet.jeesns.service.system;

import com.lxinet.jeesns.core.exception.OpeErrorException;
import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.dao.system.IActionDao;
import com.lxinet.jeesns.model.system.Action;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
@Service("actionService")
public class ActionService extends BaseServiceImpl<Action> {
    @Resource
    private IActionDao actionDao;

    public List<Action> list() {
        return super.listAll();
    }

    @Override
    public Action findById(Integer id) {
        return super.findById(id);
    }


    public boolean isenable(Integer id) {
        if(actionDao.isenable(id) == 0){
            throw new OpeErrorException();
        }
        return true;
    }

    /**
     * 状态是否能使用
     * @param id
     * @return true可以使用，false不能使用
     */
    public boolean canuse(Integer id) {
        Action action = this.findById(id);
        if(action == null){
            return false;
        }
        if(action.getStatus() == 1){
            return false;
        }
        return true;
    }
}
