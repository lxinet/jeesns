package com.lxinet.jeesns.dao.system;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.dao.common.IBaseDao;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.system.ActionLog;
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
