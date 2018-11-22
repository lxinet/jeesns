package com.lxinet.jeesns.model.weibo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxinet.jeesns.core.annotation.Column;
import com.lxinet.jeesns.core.annotation.Id;
import com.lxinet.jeesns.core.annotation.Table;
import com.lxinet.jeesns.core.enums.FillTime;
import com.lxinet.jeesns.core.enums.IdType;
import com.lxinet.jeesns.model.picture.Picture;
import com.lxinet.jeesns.model.member.Member;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/11/25.
 */
@Table("tbl_weibo")
public class Weibo implements Serializable {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @Column(value = "create_time", currTime = FillTime.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Column("member_id")
    private Integer memberId;
    private Member member;
    @Column("type")
    private Integer type;
    @Column("content")
    private String content;
    @Column("favor")
    private Integer favor;
    @Column("status")
    private Integer status;
    @Column("comment_count")
    private Integer commentCount;
    //是否已点赞，0未点赞，1已点赞
    @Column("is_favor")
    private Integer isFavor;
    private List<Picture> pictures;
    @Column("topic_id")
    private Integer topicId;

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

    public Integer getIsFavor() {
        return isFavor;
    }

    public void setIsFavor(Integer isFavor) {
        this.isFavor = isFavor;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }
}
