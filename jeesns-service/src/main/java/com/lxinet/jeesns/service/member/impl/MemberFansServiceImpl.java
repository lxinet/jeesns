package com.lxinet.jeesns.service.member.impl;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.dao.member.IMemberFansDao;
import com.lxinet.jeesns.model.member.MemberFans;
import com.lxinet.jeesns.service.member.IMemberFansService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/21.
 */
@Service("memberFansServiceImpl")
public class MemberFansServiceImpl implements IMemberFansService {
    @Resource
    private IMemberFansDao memberFansDao;

    @Override
    public MemberFans find(Integer whoFollowId, Integer followWhoId) {
        return memberFansDao.find(whoFollowId,followWhoId);
    }

    /**
     * 关注
     */
    @Override
    public ResponseModel save(Integer whoFollowId, Integer followWhoId) {
        if(memberFansDao.find(whoFollowId,followWhoId) == null){
            if(memberFansDao.save(whoFollowId,followWhoId) == 1){
                return new ResponseModel(1,"关注成功");
            }
        }else {
            //已经关注了
            return new ResponseModel(0,"关注成功");
        }
        return new ResponseModel(-1,"关注失败");
    }

    /**
     * 取消关注
     */
    @Override
    public ResponseModel delete(Integer whoFollowId, Integer followWhoId) {
        if(memberFansDao.delete(whoFollowId,followWhoId) > 0){
            return new ResponseModel(1,"取消关注成功");
        }
        return new ResponseModel(-1,"取消关注失败");
    }

    @Override
    public ResponseModel followsList(Page page, Integer whoFollowId) {
        List<MemberFans> list = memberFansDao.followsList(page, whoFollowId);
        ResponseModel model = new ResponseModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public ResponseModel fansList(Page page, Integer followWhoId) {
        List<MemberFans> list = memberFansDao.fansList(page, followWhoId);
        ResponseModel model = new ResponseModel(0,page);
        model.setData(list);
        return model;
    }


}
