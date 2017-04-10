package com.lxinet.jeesns.modules.group.service;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.entity.Page;
import com.lxinet.jeesns.modules.cms.entity.Article;
import com.lxinet.jeesns.modules.group.entity.GroupTopic;
import com.lxinet.jeesns.modules.mem.entity.Member;


/**
 * Created by zchuanzhao on 2016/12/26.
 */
public interface IGroupTopicService {
    GroupTopic findById(int id);

    GroupTopic findById(int id,Member loginMember);

    ResponseModel save(Member member, GroupTopic groupTopic);

    ResponseModel update(Member member, GroupTopic groupTopic);

    ResponseModel delete(Member loginMember,int id);

    ResponseModel indexDelete(Member loginMember,int id);

    ResponseModel listByPage(Page page, String key, int groupId,int status,int memberId);

    ResponseModel audit(Member member,int id);

    ResponseModel top(Member member,int id,int top);

    ResponseModel essence(Member member,int id,int essence);

    ResponseModel favor(Member loginMember, int id);
}
