package com.lxinet.jeesns.dao.system;

import com.lxinet.jeesns.core.dao.BaseMapper;
import com.lxinet.jeesns.dao.common.IBaseDao;
import com.lxinet.jeesns.model.system.ScoreRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zchuanzhao on 2017/3/24.
 */
@Mapper
public interface IScoreRuleDao extends BaseMapper<ScoreRule> {

    int enabled(@Param("id") int id);
}
