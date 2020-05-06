package com.lxinet.jeesns.service.group;

import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.dao.group.IGroupTopicTypeDao;
import com.lxinet.jeesns.model.group.GroupTopicType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/9 下午1:17
 */
@Service
public class GroupTopicTypeService extends BaseServiceImpl<GroupTopicType> {
    @Resource
    private IGroupTopicTypeDao groupTopicTypeDao;

    public GroupTopicType findById(int id) {
        return super.findById(id);
    }

    public List<GroupTopicType> list(int groupId) {
        return groupTopicTypeDao.list(groupId);
    }


}
