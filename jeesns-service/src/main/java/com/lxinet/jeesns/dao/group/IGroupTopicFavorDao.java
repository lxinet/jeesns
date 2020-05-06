package com.lxinet.jeesns.dao.group;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.model.group.GroupTopicFavor;
import org.apache.ibatis.annotations.*;

/**
 * 文章点赞DAO接口
 * Created by zchuanzhao on 2017/2/9.
 */
@Mapper
public interface IGroupTopicFavorDao extends BaseMapper<GroupTopicFavor> {

    @Select("select * from tbl_group_topic_favor where group_topic_id = #{groupTopicId} and member_id = #{memberId}")
    GroupTopicFavor find(@Param("groupTopicId") Integer groupTopicId, @Param("memberId") Integer memberId);

    @Insert("insert into tbl_group_topic_favor (group_topic_id,member_id,create_time) values (#{groupTopicId}, #{memberId}, now())")
    Integer save(@Param("groupTopicId") Integer groupTopicId, @Param("memberId") Integer memberId);

    @Delete("delete from tbl_group_topic_favor where group_topic_id = #{groupTopicId} and member_id = #{memberId}")
    Integer delete(@Param("groupTopicId") Integer groupTopicId, @Param("memberId") Integer memberId);
}