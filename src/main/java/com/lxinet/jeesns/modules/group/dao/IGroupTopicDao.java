package com.lxinet.jeesns.modules.group.dao;

import com.lxinet.jeesns.core.dao.IBaseDao;
import com.lxinet.jeesns.modules.group.entity.GroupTopic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 16/12/26.
 */
public interface IGroupTopicDao extends IBaseDao<GroupTopic> {

    int save(@Param("groupId") Integer groupId, @Param("archiveId") Integer archiveId,@Param("status") Integer status);

    List<GroupTopic> listByPage(@Param("key") String key, @Param("groupId") Integer groupId,@Param("status") Integer status);

    int audit(@Param("id") Integer id);

    GroupTopic findById(@Param("id") Integer id,@Param("loginMemberId") Integer loginMemberId);
}