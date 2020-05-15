package cn.jeesns.dao.group;

import cn.jeesns.model.group.GroupTopicType;
import cn.jeesns.core.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/8 下午10:59
 */
@Mapper
public interface IGroupTopicTypeDao extends BaseMapper<GroupTopicType> {

    List<GroupTopicType> list(@Param("groupId") Integer groupId);

}