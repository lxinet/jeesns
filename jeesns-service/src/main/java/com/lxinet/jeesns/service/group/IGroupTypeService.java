package com.lxinet.jeesns.service.group;

import com.lxinet.jeesns.core.service.IBaseService;
import com.lxinet.jeesns.model.group.GroupType;
import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/15 下午11:13
 */
public interface IGroupTypeService extends IBaseService<GroupType> {

    List<GroupType> list();

    boolean delete(int id);

}
