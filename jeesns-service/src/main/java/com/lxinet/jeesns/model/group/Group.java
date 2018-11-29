package com.lxinet.jeesns.model.group;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxinet.jeesns.core.annotation.Column;
import com.lxinet.jeesns.core.annotation.Id;
import com.lxinet.jeesns.core.annotation.Table;
import com.lxinet.jeesns.core.enums.FillTime;
import com.lxinet.jeesns.core.enums.IdType;
import com.lxinet.jeesns.model.member.Member;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zchuanzhao on 16/12/23.
 */
@Table("tbl_group")
public class Group implements Serializable {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(value = "create_time", currTime = FillTime.INSERT)
    private Date createTime;
    @NotBlank(message = "群组名称不能为空")
    @Column("name")
    private String name;
    @Column("logo")
    private String logo;
    @Column("creator")
    private Integer creator;
    private Member creatorMember;
    @Column("managers")
    private String managers;
    @Column("tags")
    private String tags;
    @Column("introduce")
    private String introduce;
    @Column("status")
    private Integer status;
    @Column("can_post")
    private Integer canPost;
    @Column("topic_review")
    private Integer topicReview;
    @Column("topic_count")
    private Integer topicCount;
    @Column("fans_count")
    private Integer fansCount;
    @Column("type_id")
    private Integer typeId;
    @Column("follow_pay")
    private Integer followPay;
    @Column("pay_money")
    private Double payMoney;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getManagers() {
        return managers;
    }

    public void setManagers(String managers) {
        this.managers = managers;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Member getCreatorMember() {
        return creatorMember;
    }

    public void setCreatorMember(Member creatorMember) {
        this.creatorMember = creatorMember;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCanPost() {
        return canPost;
    }

    public void setCanPost(Integer canPost) {
        this.canPost = canPost;
    }

    public Integer getTopicReview() {
        return topicReview;
    }

    public void setTopicReview(Integer topicReview) {
        this.topicReview = topicReview;
    }

    public Integer getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(Integer topicCount) {
        this.topicCount = topicCount;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public Integer getTypeId() {
        return typeId == null ? 1 : typeId ;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getFollowPay() {
        return followPay;
    }

    public void setFollowPay(Integer followPay) {
        this.followPay = followPay;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }
}