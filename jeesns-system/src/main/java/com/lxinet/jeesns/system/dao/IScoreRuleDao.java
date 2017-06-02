package com.lxinet.jeesns.system.dao;

import com.lxinet.jeesns.core.dao.IBaseDao;
import com.lxinet.jeesns.system.entity.ScoreRule;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zchuanzhao on 2017/3/24.
 */
public interface IScoreRuleDao extends IBaseDao<ScoreRule> {

    int enabled(@Param("id") int id);
}
