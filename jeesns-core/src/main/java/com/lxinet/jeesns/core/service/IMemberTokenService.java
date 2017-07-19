package com.lxinet.jeesns.core.service;

import com.lxinet.jeesns.core.model.MemberToken;

/**
 * Created by zchuanzhao on 2017/7/15.
 */
public interface IMemberTokenService {

    MemberToken getByToken(String token);

    void save(Integer memberId,String token);

}