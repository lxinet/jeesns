package com.lxinet.jeesns.service.group;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.model.group.GroupTopic;
import com.lxinet.jeesns.model.member.Member;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by zchuanzhao on 2016/12/26.
 */
public interface IGroupTopicService {
    GroupTopic findById(int id);

    GroupTopic findById(int id,Member loginMember);

    ResponseModel save(Member member, GroupTopic groupTopic);

    ResponseModel update(Member member, GroupTopic groupTopic);

    ResponseModel delete(Member loginMember,int id);

    ResponseModel indexDelete(HttpServletRequest request, Member loginMember, int id);

    ResponseModel listByPage(Page page, String key, int groupId, int status, int memberId);

    ResponseModel audit(Member member,int id);

    ResponseModel top(Member member,int id,int top);

    ResponseModel essence(Member member,int id,int essence);

    ResponseModel favor(Member loginMember, int id);

    List<GroupTopic> listByCustom(int gid, String sort, int num, int day, int thumbnail);
}
