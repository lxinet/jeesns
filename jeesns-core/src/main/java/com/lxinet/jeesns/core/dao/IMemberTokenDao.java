package com.lxinet.jeesns.core.dao;

import com.lxinet.jeesns.core.model.MemberToken;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Created by zchuanzhao on 2017/7/15.
 */
public interface IMemberTokenDao extends IBaseDao<MemberToken> {

    MemberToken getByToken(@Param("token") String token);

    Integer save(@Param("memberId") Integer memberId, @Param("token") String token, @Param("expireTime") Date expireTime);

}