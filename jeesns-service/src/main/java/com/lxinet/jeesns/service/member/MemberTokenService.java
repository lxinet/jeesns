package com.lxinet.jeesns.service.member;

import com.lxinet.jeesns.core.service.BaseService;
import com.lxinet.jeesns.dao.member.IMemberTokenDao;
import com.lxinet.jeesns.model.member.MemberToken;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zchuanzhao on 16/9/29.
 */
@Service("memberTokenService")
public class MemberTokenService extends BaseService<MemberToken>{
    @Resource
    private IMemberTokenDao memberTokenDao;

    public MemberToken getByToken(String token) {
        return memberTokenDao.getByToken(token);
    }

    public void save(Integer memberId,String token) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY,1);
        memberTokenDao.save(memberId,token,calendar.getTime());
    }
}
