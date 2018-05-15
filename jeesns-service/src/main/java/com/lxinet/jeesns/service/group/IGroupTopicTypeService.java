package com.lxinet.jeesns.service.group;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.model.group.GroupTopicType;
import com.lxinet.jeesns.model.member.Member;

import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/8 下午11:13
 */
public interface IGroupTopicTypeService {

    GroupTopicType findById(int id);

    List<GroupTopicType> list(int groupId);

    ResponseModel delete(Member member, int id);

    ResponseModel save(Member member, GroupTopicType groupTopicType);

    ResponseModel update(Member member, GroupTopicType groupTopicType);
}
