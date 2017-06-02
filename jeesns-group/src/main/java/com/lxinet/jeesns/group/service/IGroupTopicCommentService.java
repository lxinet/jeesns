package com.lxinet.jeesns.group.service;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.group.entity.GroupTopicComment;
import com.lxinet.jeesns.member.entity.Member;


/**
 * Created by zchuanzhao on 2016/12/27.
 */
public interface IGroupTopicCommentService {

    GroupTopicComment findById(int id);

    ResponseModel save(Member loginMember, String content, Integer groupTopicId);

    ResponseModel delete(Member loginMember,int id);

    ResponseModel listByGroupTopic(Page page, int groupTopicId);

    void deleteByTopic(int groupTopicId);
}
