package com.lxinet.jeesns.model.group;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxinet.jeesns.core.annotation.Column;
import com.lxinet.jeesns.core.annotation.Id;
import com.lxinet.jeesns.core.annotation.Table;
import com.lxinet.jeesns.core.enums.FillTime;
import com.lxinet.jeesns.core.enums.IdType;
import com.lxinet.jeesns.model.member.Member;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zchuanzhao on 16/12/27.
 */
@Table("tbl_group_topic_comment")
public class GroupTopicComment implements Serializable {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @Column(value = "create_time", currTime = FillTime.INSERT)
    private Date createTime;
    @Column("group_topic_id")
    private Integer groupTopicId;

    private GroupTopic groupTopic;
    @Column("member_id")
    private Integer memberId;

    private Member member;
    @Column("content")
    private String content;
    @Column("comment_id")
    private Integer commentId;

    private GroupTopicComment groupTopicComment;


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

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public GroupTopicComment getGroupTopicComment() {
        return groupTopicComment;
    }

    public void setGroupTopicComment(GroupTopicComment groupTopicComment) {
        this.groupTopicComment = groupTopicComment;
    }
}