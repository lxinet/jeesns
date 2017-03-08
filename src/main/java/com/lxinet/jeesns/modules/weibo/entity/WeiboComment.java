package com.lxinet.jeesns.modules.weibo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxinet.jeesns.core.entity.BaseEntity;
import com.lxinet.jeesns.core.utils.AtUtil;
import com.lxinet.jeesns.core.utils.EmojiUtil;
import com.lxinet.jeesns.modules.mem.entity.Member;

import java.util.Date;

/**
 * Created by zchuanzhao on 2016/12/22.
 */
public class WeiboComment extends BaseEntity {
    private Integer id;
    private Date createTime;
    private Integer memberId;
    private Integer weiboId;
    private Member member;
    private Weibo weibo;
    private Integer commentId;
    private WeiboComment weiboComment;
    private String content;
    private Integer status;

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

    public Integer getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(Integer weiboId) {
        this.weiboId = weiboId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Weibo getWeibo() {
        return weibo;
    }

    public void setWeibo(Weibo weibo) {
        this.weibo = weibo;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public WeiboComment getWeiboComment() {
        return weiboComment;
    }

    public void setWeiboComment(WeiboComment weiboComment) {
        this.weiboComment = weiboComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        // 判断内容是否存在:*:格式的内容，
        // 如果有存在，默认说明是有存在Emoji，则替换相应内容，
        // 不存在Emoji则直接返回内容
        String regEmoji=".*:.*:.*";
        if(content.matches(regEmoji)){
            content = EmojiUtil.replace(content);
        }
        String regAt = ".*@.*";
        if(content.matches(regAt)){
            content = AtUtil.at(content);
        }
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
