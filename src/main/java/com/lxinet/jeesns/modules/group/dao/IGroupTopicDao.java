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

    /**
     * 置顶
     * @param top 0取消置顶，1置顶，2超级置顶
     * @return
     */
    int top(@Param("id") Integer id,@Param("top") Integer top);

    /**
     * 加精
     * @param essence 0取消加精，1加精
     * @return
     */
    int essence(@Param("id") Integer id,@Param("essence") Integer essence);
}