package com.lxinet.jeesns.modules.group.dao;

import com.lxinet.jeesns.core.dao.IBaseDao;
import com.lxinet.jeesns.core.entity.Page;
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
    List<GroupFans> listByPage(@Param("page") Page page, @Param("groupId") Integer groupId);

    GroupFans findByMemberAndGroup(@Param("groupId") Integer groupId,@Param("memberId") Integer memberId);

    int save(@Param("groupId") Integer groupId,@Param("memberId") Integer memberId);

    int delete(@Param("groupId") Integer groupId,@Param("memberId") Integer memberId);


    /**
     * 获取用户关注的群组列表
     * @param page
     * @param memberId
     * @return
     */
    List<Group> listByMember(@Param("page") Page page, @Param("memberId") Integer memberId);
}