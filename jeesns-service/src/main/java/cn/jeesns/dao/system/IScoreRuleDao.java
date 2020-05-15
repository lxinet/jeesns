package cn.jeesns.dao.system;

import cn.jeesns.model.system.ScoreRule;
import cn.jeesns.core.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zchuanzhao on 2017/3/24.
 */
@Mapper
public interface IScoreRuleDao extends BaseMapper<ScoreRule> {

    int enabled(@Param("id") int id);
}
