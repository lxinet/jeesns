package com.lxinet.jeesns.system.dao;

import com.lxinet.jeesns.core.dao.IBaseDao;
import com.lxinet.jeesns.system.model.Action;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
public interface IActionDao extends IBaseDao<Action> {
    int isenable(@Param("id") Integer id);
}
