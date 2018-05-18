package com.lxinet.jeesns.service.system;

import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.model.system.ScoreRule;

import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
public interface IScoreRuleService {

    List<ScoreRule> list();

    ScoreRule findById(Integer id);

    ResultModel update(ScoreRule scoreRule);

    ResultModel enabled(int id);

}
