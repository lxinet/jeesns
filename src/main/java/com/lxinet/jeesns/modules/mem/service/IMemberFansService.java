package com.lxinet.jeesns.modules.mem.service;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.modules.group.entity.GroupFans;
import com.lxinet.jeesns.modules.mem.entity.Member;
import com.lxinet.jeesns.modules.mem.entity.MemberFans;
import org.apache.ibatis.annotations.Param;


/**
 * Created by zchuanzhao on 17/2/21.
 */
public interface IMemberFansService {

    ResponseModel save(Integer whoFollowId, Integer followWhoId);

    ResponseModel delete(Integer whoFollowId, Integer followWhoId);

    ResponseModel followsList(Page page,Integer whoFollowId);

    ResponseModel fansList(Page page,Integer followWhoId);

    MemberFans find(Integer whoFollowId, Integer followWhoId);
}
