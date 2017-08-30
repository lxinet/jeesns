package com.lxinet.jeesns.service.system;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.model.system.Action;

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
