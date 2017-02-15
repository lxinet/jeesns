package com.lxinet.jeesns.modules.sys.service;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.modules.sys.entity.Action;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
public interface IActionService {

    List<Action> list();

    Action findById(Integer id);

    ResponseModel update(Action action);

    ResponseModel isenable(Integer id);

    boolean canuse(Integer id);
}
