package com.lxinet.jeesns.service.group;

import com.lxinet.jeesns.core.exception.OpeErrorException;
import com.lxinet.jeesns.core.service.BaseService;
import com.lxinet.jeesns.dao.group.IGroupTypeDao;
import com.lxinet.jeesns.model.group.GroupType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/15 下午1:17
 */
@Service
public class GroupTypeService extends BaseService<GroupType> {
    @Resource
    private IGroupTypeDao groupTypeDao;


    public List<GroupType> list() {
        return super.listAll();
    }

    public boolean delete(int id) {
        if (id == 1){
            throw new OpeErrorException("默认分类无法删除");
        }
        boolean result = super.deleteById(id);
        return result;
    }

}
