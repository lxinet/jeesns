package com.lxinet.jeesns.modules.group.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxinet.jeesns.core.entity.BaseEntity;
import com.lxinet.jeesns.modules.mem.entity.Member;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * Created by zchuanzhao on 16/12/26.
 */
public class GroupFans extends BaseEntity {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Integer groupId;
    private Group group;
    private Integer memberId;
    private Member member;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
}