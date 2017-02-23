package com.lxinet.jeesns.modules.mem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxinet.jeesns.core.entity.BaseEntity;
import java.util.Date;

/**
 * Created by zchuanzhao on 17/2/15.
 */
public class MemberFans extends BaseEntity {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Integer followWho;
    private Member followWhoMember;
    private Integer whoFollow;
    private Member whoFollowMember;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getFollowWho() {
        return followWho;
    }

    public void setFollowWho(Integer followWho) {
        this.followWho = followWho;
    }

    public Member getFollowWhoMember() {
        return followWhoMember;
    }

    public void setFollowWhoMember(Member followWhoMember) {
        this.followWhoMember = followWhoMember;
    }

    public Integer getWhoFollow() {
        return whoFollow;
    }

    public void setWhoFollow(Integer whoFollow) {
        this.whoFollow = whoFollow;
    }

    public Member getWhoFollowMember() {
        return whoFollowMember;
    }

    public void setWhoFollowMember(Member whoFollowMember) {
        this.whoFollowMember = whoFollowMember;
    }
}