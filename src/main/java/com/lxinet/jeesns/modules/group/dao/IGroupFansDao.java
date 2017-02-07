package com.lxinet.jeesns.modules.group.dao;

import com.lxinet.jeesns.core.dao.IBaseDao;
import com.lxinet.jeesns.modules.group.entity.Group;
import com.lxinet.jeesns.modules.group.entity.GroupFans;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 16/12/26.
 */
public interface IGroupFansDao extends IBaseDao<GroupFans> {

    /**
     * 获取群组粉丝
     * @return
     */
    List<GroupFans> listByPage(@Param("groupId") Integer groupId);

    GroupFans findByMemberAndGroup(@Param("groupId") Integer groupId,@Param("memberId") Integer memberId);

    int save(@Param("groupId") Integer groupId,@Param("memberId") Integer memberId);

    int delete(@Param("groupId") Integer groupId,@Param("memberId") Integer memberId);
}