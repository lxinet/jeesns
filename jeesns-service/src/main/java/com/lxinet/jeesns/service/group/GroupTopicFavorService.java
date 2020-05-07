package com.lxinet.jeesns.service.group;

import com.lxinet.jeesns.core.service.BaseService;
import com.lxinet.jeesns.dao.group.IGroupTopicFavorDao;
import com.lxinet.jeesns.model.group.GroupTopicFavor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/2/9.
 */
@Service("groupTopicFavorService")
public class GroupTopicFavorService extends BaseService<GroupTopicFavor> {
    @Resource
    private IGroupTopicFavorDao groupTopicFavorDao;

    public GroupTopicFavor find(Integer groupTopicId, Integer memberId) {
        return groupTopicFavorDao.find(groupTopicId,memberId);
    }

    public void save(Integer groupTopicId, Integer memberId) {
        groupTopicFavorDao.save(groupTopicId,memberId);
    }

    public void delete(Integer groupTopicId, Integer memberId) {
        groupTopicFavorDao.delete(groupTopicId,memberId);
    }
}
