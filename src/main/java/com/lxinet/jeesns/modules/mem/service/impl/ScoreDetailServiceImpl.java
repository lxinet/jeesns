package com.lxinet.jeesns.modules.mem.service.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.modules.mem.dao.IScoreDetailDao;
import com.lxinet.jeesns.modules.mem.entity.ScoreDetail;
import com.lxinet.jeesns.modules.mem.service.IMemberService;
import com.lxinet.jeesns.modules.mem.service.IScoreDetailService;
import com.lxinet.jeesns.modules.sys.entity.ScoreRule;
import com.lxinet.jeesns.modules.sys.service.IScoreRuleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/21.
 */
@Service
public class ScoreDetailServiceImpl implements IScoreDetailService {
    @Resource
    private IScoreDetailDao scoreDetailDao;
    @Resource
    private IScoreRuleService scoreRuleService;
    @Resource
    private IMemberService memberService;

    @Override
    public ResponseModel<ScoreDetail> list(Page page, Integer memberId) {
        List<ScoreDetail> list = scoreDetailDao.listByPage(page,memberId);
        ResponseModel model = new ResponseModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public ResponseModel save(ScoreDetail scoreDetail) {
        if(scoreDetailDao.save(scoreDetail) > 0){
            return new ResponseModel(0);
        }
        return new ResponseModel(-1,"保存失败");
    }

    /**
     * 是否能奖励，true表示可以奖励
     * @param memberId
     * @param scoreRuleId
     * @param type
     * @return
     */
    @Override
    public boolean canBonus(int memberId, int scoreRuleId, String type) {
        List<ScoreDetail> list = scoreDetailDao.canBonus(memberId,scoreRuleId,type);
        return list.size() == 0;
    }

    /**
     * 根据会员、获取奖励的外键、奖励规则ID获取奖励激励，不包括foreign_id=0
     * @param memberId
     * @param scoreRuleId
     * @param forgignId
     * @return
     */
    @Override
    public ScoreDetail findByForeignAndRule(int memberId, int scoreRuleId, int forgignId) {
        return scoreDetailDao.findByForeignAndRule(memberId,scoreRuleId,forgignId);
    }
}
