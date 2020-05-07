package com.lxinet.jeesns.service.member;

import com.lxinet.jeesns.core.service.BaseService;
import com.lxinet.jeesns.utils.ScoreRuleConsts;
import com.lxinet.jeesns.core.exception.OpeErrorException;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.member.ICheckinDao;
import com.lxinet.jeesns.model.member.Checkin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 签到
 * Created by zchuanzhao on 18/8/20.
 */
@Service
public class CheckinService extends BaseService<Checkin> {
    @Resource
    private ICheckinDao checkinDao;
    @Resource
    private ScoreDetailService scoreDetailService;

    public List<Checkin> list(Page page, Integer memberId) {
        List<Checkin> list = checkinDao.list(page,memberId);
        return list;
    }

    public List<Checkin> todayList(Page page) {
        List<Checkin> list = checkinDao.todayList(page);
        return list;
    }

    public List<Checkin> todayContinueList() {
        return checkinDao.todayContinueList();
    }

    public Checkin todayCheckin(Integer memberId) {
        return checkinDao.todayCheckin(memberId);
    }

    public Checkin yesterdayCheckin(Integer memberId) {
        return checkinDao.yesterdayCheckin(memberId);
    }

    @Transactional
    public boolean save(Integer memberId) {
        synchronized (this){
            if (null != todayCheckin(memberId)){
                throw new OpeErrorException("今天已经签到过了");
            }
            Checkin checkin = new Checkin();
            checkin.setMemberId(memberId);
            Checkin yesterdayCheckin = yesterdayCheckin(memberId);
            if (null != yesterdayCheckin){
                checkin.setContinueDay(yesterdayCheckin.getContinueDay() + 1);
            }else {
                checkin.setContinueDay(1);
            }
            boolean result = checkinDao.saveObj(checkin) == 1;
            if (result){
                scoreDetailService.scoreBonus(memberId, ScoreRuleConsts.CHECKIN, checkin.getId());
            }
            return result;
        }

    }
}
