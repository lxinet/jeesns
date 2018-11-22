package com.lxinet.jeesns.service.group.impl;

import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.dao.group.IGroupTopicFavorDao;
import com.lxinet.jeesns.model.group.GroupTopicFavor;
import com.lxinet.jeesns.service.group.IGroupTopicFavorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/2/9.
 */
@Service("groupTopicFavorService")
public class GroupTopicFavorServiceImpl extends BaseServiceImpl<GroupTopicFavor> implements IGroupTopicFavorService {
    @Resource
    private IGroupTopicFavorDao groupTopicFavorDao;

    @Override
    public GroupTopicFavor find(Integer groupTopicId, Integer memberId) {
        return groupTopicFavorDao.find(groupTopicId,memberId);
    }

    @Override
    public void save(Integer groupTopicId, Integer memberId) {
        groupTopicFavorDao.save(groupTopicId,memberId);
    }

    @Override
    public void delete(Integer groupTopicId, Integer memberId) {
        groupTopicFavorDao.delete(groupTopicId,memberId);
    }
}
