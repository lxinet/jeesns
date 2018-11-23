package com.lxinet.jeesns.service.system.impl;

import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.dao.system.IScoreRuleDao;
import com.lxinet.jeesns.model.system.ScoreRule;
import com.lxinet.jeesns.service.system.IScoreRuleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/3/24.
 */
@Service
public class ScoreRuleServiceImpl extends BaseServiceImpl<ScoreRule> implements IScoreRuleService {
    @Resource
    private IScoreRuleDao scoreRuleDao;


    @Override
    public List<ScoreRule> list() {
        return super.listAll();
    }

    @Override
    public ScoreRule findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public boolean update(ScoreRule scoreRule) {
        return super.update(scoreRule);
    }

    @Override
    public boolean enabled(int id) {
        return scoreRuleDao.enabled(id) == 1;
    }

}
