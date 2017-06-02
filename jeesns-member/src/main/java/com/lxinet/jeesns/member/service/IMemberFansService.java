package com.lxinet.jeesns.member.service;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.member.entity.MemberFans;


/**
 * Created by zchuanzhao on 17/2/21.
 */
public interface IMemberFansService {

    ResponseModel save(Integer whoFollowId, Integer followWhoId);

    ResponseModel delete(Integer whoFollowId, Integer followWhoId);

    ResponseModel followsList(Page page, Integer whoFollowId);

    ResponseModel fansList(Page page, Integer followWhoId);

    MemberFans find(Integer whoFollowId, Integer followWhoId);
}
