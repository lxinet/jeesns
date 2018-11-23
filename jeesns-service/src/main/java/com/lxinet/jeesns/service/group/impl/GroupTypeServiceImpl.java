package com.lxinet.jeesns.service.group.impl;

import com.lxinet.jeesns.core.exception.OpeErrorException;
import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.dao.group.IGroupTypeDao;
import com.lxinet.jeesns.model.group.GroupType;
import com.lxinet.jeesns.service.group.IGroupTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/15 下午1:17
 */
@Service
public class GroupTypeServiceImpl extends BaseServiceImpl<GroupType> implements IGroupTypeService {
    @Resource
    private IGroupTypeDao groupTypeDao;


    @Override
    public List<GroupType> list() {
        return super.listAll();
    }

    @Override
    public boolean delete(int id) {
        if (id == 1){
            throw new OpeErrorException("默认分类无法删除");
        }
        boolean result = super.deleteById(id);
        return result;
    }

}
