package com.lxinet.jeesns.modules.group.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxinet.jeesns.core.entity.BaseEntity;
import com.lxinet.jeesns.modules.mem.entity.Member;
import java.util.Date;

/**
 * Created by zchuanzhao on 16/12/27.
 */
public class GroupTopicComment extends BaseEntity {
    private Integer id;

    private Date createTime;

    private Integer groupTopicId;

    private GroupTopic groupTopic;

    private Integer memberId;

    private Member member;

    private String content;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getGroupTopicId() {
        return groupTopicId;
    }

    public void setGroupTopicId(Integer groupTopicId) {
        this.groupTopicId = groupTopicId;
    }

    public GroupTopic getGroupTopic() {
        return groupTopic;
    }

    public void setGroupTopic(GroupTopic groupTopic) {
        this.groupTopic = groupTopic;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}