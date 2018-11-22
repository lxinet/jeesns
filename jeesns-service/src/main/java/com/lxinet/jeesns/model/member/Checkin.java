package com.lxinet.jeesns.model.member;

import com.lxinet.jeesns.core.annotation.Column;
import com.lxinet.jeesns.core.annotation.Id;
import com.lxinet.jeesns.core.annotation.Table;
import com.lxinet.jeesns.core.enums.FillTime;
import com.lxinet.jeesns.core.enums.IdType;
import com.lxinet.jeesns.model.common.BaseEntity;

import java.util.Date;

/**
 * 签到
 * @author zchuanzhao@linewell.com
 * 2018/8/20
 */
@Table("tbl_checkin")
public class Checkin extends BaseEntity {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @Column(value = "create_time", currTime = FillTime.INSERT)
    private Date createTime;
    @Column("member_id")
    private Integer memberId;
    private Member member;
    @Column("continue_day")
    private Integer continueDay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getContinueDay() {
        return continueDay;
    }

    public void setContinueDay(Integer continueDay) {
        this.continueDay = continueDay;
    }
}