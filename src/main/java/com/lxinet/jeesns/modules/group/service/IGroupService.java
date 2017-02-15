package com.lxinet.jeesns.modules.group.service;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.modules.cms.entity.ArticleCate;
import com.lxinet.jeesns.modules.group.entity.Group;
import com.lxinet.jeesns.modules.mem.entity.Member;

import java.util.List;


/**
 * Created by zchuanzhao on 16/12/23.
 */
public interface IGroupService {

    Group findById(int id);

    ResponseModel save(Member loginMember, Group group);

    ResponseModel update(Member loginMember, Group group);

    ResponseModel delete(Member loginMember, int id);

    ResponseModel listByPage(int status,Page page, String key);

    ResponseModel follow(Member loginMember, Integer groupId,int type);

    ResponseModel changeStatus(int id);
}
