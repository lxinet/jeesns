package com.lxinet.jeesns.service.group;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.model.group.GroupType;
import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/15 下午11:13
 */
public interface IGroupTypeService {

    GroupType findById(int id);

    List<GroupType> list();

    ResponseModel delete(int id);

    ResponseModel save(GroupType groupType);

    ResponseModel update(GroupType groupType);
}
