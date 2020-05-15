package cn.jeesns.dao.group;

import cn.jeesns.model.group.GroupTopicComment;
import cn.jeesns.core.dao.BaseMapper;
import cn.jeesns.core.model.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 16/12/27.
 */
@Mapper
public interface IGroupTopicCommentDao extends BaseMapper<GroupTopicComment> {

    List<GroupTopicComment> listByGroupTopic(@Param("page") Page page, @Param("groupTopicId") Integer groupTopicId);

    int deleteByTopic(@Param("groupTopicId") Integer groupTopicId);

    GroupTopicComment findById(@Param("id") Integer id);

}