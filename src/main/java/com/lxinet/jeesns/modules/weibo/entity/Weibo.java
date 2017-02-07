package com.lxinet.jeesns.modules.weibo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxinet.jeesns.core.entity.BaseEntity;
import com.lxinet.jeesns.modules.mem.entity.Member;

import java.util.Date;

/**
 * Created by zchuanzhao on 2016/11/25.
 */
public class Weibo extends BaseEntity {
    private Integer id;
    private Date createTime;
    private Integer memberId;
    private Member member;
    private Integer type;
    private String content;
    private Integer favor;
    private Integer status;
    private Integer commentCount;

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
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFavor() {
        return favor;
    }

    public void setFavor(Integer favor) {
        this.favor = favor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}
