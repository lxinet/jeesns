package com.lxinet.jeesns.service.system.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
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
public class ScoreRuleServiceImpl implements IScoreRuleService {
    @Resource
    private IScoreRuleDao scoreRuleDao;


    @Override
    public List<ScoreRule> list() {
        return scoreRuleDao.allList();
    }

    @Override
    public ScoreRule findById(Integer id) {
        return scoreRuleDao.findById(id);
    }

    @Override
    public ResponseModel update(ScoreRule scoreRule) {
        if(scoreRuleDao.update(scoreRule) == 1){
            return new ResponseModel(0, "操作成功");
        }
        return new ResponseModel(-1, "操作失败");
    }

    @Override
    public ResponseModel enabled(int id) {
        if(scoreRuleDao.enabled(id) == 1){
            return new ResponseModel(0, "操作成功");
        }
        return new ResponseModel(-1, "操作失败");
    }

}
