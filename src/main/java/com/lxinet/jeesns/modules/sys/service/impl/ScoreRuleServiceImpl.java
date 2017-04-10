package com.lxinet.jeesns.modules.sys.service.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.modules.mem.entity.ScoreDetail;
import com.lxinet.jeesns.modules.mem.service.IMemberService;
import com.lxinet.jeesns.modules.mem.service.IScoreDetailService;
import com.lxinet.jeesns.modules.sys.dao.IScoreRuleDao;
import com.lxinet.jeesns.modules.sys.entity.ScoreRule;
import com.lxinet.jeesns.modules.sys.service.IScoreRuleService;
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
    @Resource
    private IScoreDetailService scoreDetailService;
    @Resource
    private IMemberService memberService;


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
        return new ResponseModel(0, "操作失败");
    }

    @Override
    public ResponseModel enabled(int id) {
        if(scoreRuleDao.enabled(id) == 1){
            return new ResponseModel(0, "操作成功");
        }
        return new ResponseModel(0, "操作失败");
    }


    /**
     * 根据积分规则奖励
     * @param memberId
     * @param scoreRuleId
     */
    @Override
    public void scoreRuleBonus(int memberId, int scoreRuleId) {
        this.scoreRuleBonus(memberId,scoreRuleId,0);
    }

    /**
     * 根据积分规则奖励
     * @param memberId
     * @param scoreRuleId
     * @param foreignId
     */
    @Override
    public void scoreRuleBonus(int memberId, int scoreRuleId, int foreignId) {
        ScoreRule scoreRule = this.findById(scoreRuleId);
        if(scoreRule != null){
            if(scoreRule.getScore() != 0){
                String type = scoreRule.getType();
                boolean canBonus = true;
                //unlimite为不限制奖励次数
                if(!"unlimite".equals(type)){
                    canBonus = scoreDetailService.canBonus(memberId, scoreRuleId, type);
                }
                if(canBonus){
                    //每个会员、每个奖励规则、每个外键（不包含0）只能奖励一次
                    if(scoreDetailService.findByForeignAndRule(memberId, scoreRuleId, foreignId) == null){
                        memberService.updateScore(scoreRule.getScore(), memberId);
                        ScoreDetail scoreDetail = new ScoreDetail();
                        scoreDetail.setType(1);
                        scoreDetail.setMemberId(memberId);
                        scoreDetail.setForeignId(foreignId);
                        scoreDetail.setScore(scoreRule.getScore());
                        String remark = scoreRule.getName();
                        if(foreignId > 0){
                            remark += " #"+foreignId;
                        }
                        scoreDetail.setRemark(remark);
                        scoreDetail.setScoreRuleId(scoreRuleId);
                        scoreDetailService.save(scoreDetail);
                    }

                }
            }
        }
    }

    @Override
    public void scoreRuleCancelBonus(int memberId, int scoreRuleId, int foreignId) {
        ScoreDetail scoreDetail = scoreDetailService.findByForeignAndRule(memberId, scoreRuleId, foreignId);
        if(scoreDetail != null){
            //扣除积分
            memberService.updateScore(-scoreDetail.getScore(), memberId);
            ScoreDetail scoreDetailCancel = new ScoreDetail();
            scoreDetail.setType(2);
            scoreDetailCancel.setMemberId(memberId);
            scoreDetailCancel.setForeignId(foreignId);
            scoreDetailCancel.setScore(-scoreDetail.getScore());
            scoreDetailCancel.setRemark("撤销积分奖励 #"+scoreDetail.getId());
            scoreDetailCancel.setScoreRuleId(scoreRuleId);
            scoreDetailService.save(scoreDetailCancel);
        }
    }
}
