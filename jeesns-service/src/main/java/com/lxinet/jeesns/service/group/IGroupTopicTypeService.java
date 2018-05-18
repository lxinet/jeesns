package com.lxinet.jeesns.service.group;

import com.lxinet.jeesns.core.dto.ResultModel;
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

    ResultModel delete(Member member, int id);

    ResultModel save(Member member, GroupTopicType groupTopicType);

    ResultModel update(Member member, GroupTopicType groupTopicType);
}
