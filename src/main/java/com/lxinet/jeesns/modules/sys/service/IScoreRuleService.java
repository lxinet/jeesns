package com.lxinet.jeesns.modules.sys.service;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.modules.sys.entity.ScoreRule;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
public interface IScoreRuleService {

    List<ScoreRule> list();

    ScoreRule findById(Integer id);

    ResponseModel update(ScoreRule scoreRule);

    ResponseModel enabled(int id);

    void scoreRuleBonus(int memberId, int scoreRuleId);

    void scoreRuleBonus(int memberId, int scoreRuleId, int foreignId);

    void scoreRuleCancelBonus(int memberId, int scoreRuleId, int foreignId);

}
