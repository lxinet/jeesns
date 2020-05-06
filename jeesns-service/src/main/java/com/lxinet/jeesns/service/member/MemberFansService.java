package com.lxinet.jeesns.service.member;

import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.exception.OpeErrorException;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.service.impl.BaseServiceImpl;
import com.lxinet.jeesns.dao.member.IMemberFansDao;
import com.lxinet.jeesns.model.member.MemberFans;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/21.
 */
@Service("memberFansServiceImpl")
public class MemberFansService extends BaseServiceImpl<MemberFans> {
    @Resource
    private IMemberFansDao memberFansDao;

    public MemberFans find(Integer whoFollowId, Integer followWhoId) {
        return memberFansDao.find(whoFollowId,followWhoId);
    }

    /**
     * 关注
     */
    public boolean save(Integer whoFollowId, Integer followWhoId) {
        if(memberFansDao.find(whoFollowId,followWhoId) != null){
            throw new OpeErrorException("已经关注");
        }
        return memberFansDao.save(whoFollowId,followWhoId) == 1;
    }

    /**
     * 取消关注
     */
    public boolean delete(Integer whoFollowId, Integer followWhoId) {
        return memberFansDao.delete(whoFollowId,followWhoId) > 0;
    }

    public ResultModel followsList(Page page, Integer whoFollowId) {
        List<MemberFans> list = memberFansDao.followsList(page, whoFollowId);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    public ResultModel fansList(Page page, Integer followWhoId) {
        List<MemberFans> list = memberFansDao.fansList(page, followWhoId);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }


}
