package cn.jeesns.dao.system;

import cn.jeesns.model.system.ActionLog;
import cn.jeesns.core.dao.BaseMapper;
import cn.jeesns.core.model.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
@Mapper
public interface IActionLogDao extends BaseMapper<ActionLog> {

    List<ActionLog> list(@Param("page") Page page, @Param("memberId") Integer memberId);

    List<ActionLog> memberActionLog(@Param("page") Page page, @Param("memberId") Integer memberId);
}
