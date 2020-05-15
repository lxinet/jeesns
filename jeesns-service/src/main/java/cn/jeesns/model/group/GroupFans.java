package cn.jeesns.model.group;

import cn.jeesns.model.member.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.jeesns.core.annotation.Column;
import cn.jeesns.core.annotation.Table;
import cn.jeesns.core.enums.FillTime;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zchuanzhao on 16/12/26.
 */
@Table("tbl_group_fans")
public class GroupFans implements Serializable {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(value = "create_time", currTime = FillTime.INSERT)
    private Date createTime;
    @Column("group_id")
    private Integer groupId;
    private Group group;
    @Column("member_id")
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