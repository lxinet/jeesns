package com.lxinet.jeesns.service.group;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.group.GroupTopicComment;
import com.lxinet.jeesns.model.member.Member;


/**
 * Created by zchuanzhao on 2016/12/27.
 */
public interface IGroupTopicCommentService {

    GroupTopicComment findById(int id);

    ResponseModel save(Member loginMember, String content, Integer groupTopicId,Integer commentId);

    ResponseModel delete(Member loginMember,int id);

    ResponseModel listByGroupTopic(Page page, int groupTopicId);

    void deleteByTopic(int groupTopicId);
}
