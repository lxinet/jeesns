package com.lxinet.jeesns.service.group.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.dao.group.IGroupTypeDao;
import com.lxinet.jeesns.model.group.GroupType;
import com.lxinet.jeesns.model.member.Member;
import com.lxinet.jeesns.service.group.IGroupTypeService;
import com.lxinet.jeesns.service.group.IGroupTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/15 下午1:17
 */
@Service
public class GroupTypeServiceImpl implements IGroupTypeService {
    @Resource
    private IGroupTypeDao groupTypeDao;

    @Override
    public GroupType findById(int id) {
        return groupTypeDao.findById(id);
    }

    @Override
    public List<GroupType> list() {
        return groupTypeDao.allList();
    }

    @Override
    public ResponseModel delete(int id) {
        int result = groupTypeDao.delete(id);
        if (result == 1){
            return new ResponseModel(0);
        }
        return new ResponseModel(-1);
    }

    @Override
    public ResponseModel save(GroupType groupType) {
        int result = groupTypeDao.save(groupType);
        if (result == 1){
            return new ResponseModel(0);
        }
        return new ResponseModel(-1);
    }

    @Override
    public ResponseModel update(GroupType groupType) {
        int result = groupTypeDao.update(groupType);
        if (result == 1){
            return new ResponseModel(0);
        }
        return new ResponseModel(-1);
    }
}
