package cn.jeesns.dao.system;

import cn.jeesns.model.system.Action;
import cn.jeesns.core.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
@Mapper
public interface IActionDao extends BaseMapper<Action> {
    int isenable(@Param("id") Integer id);
}
