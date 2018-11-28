package com.lxinet.jeesns.model.member;

import com.lxinet.jeesns.core.annotation.Column;
import com.lxinet.jeesns.core.annotation.Id;
import com.lxinet.jeesns.core.annotation.Table;
import com.lxinet.jeesns.core.enums.FillTime;
import com.lxinet.jeesns.core.enums.IdType;
import com.lxinet.jeesns.model.common.BaseEntity;

import java.util.Date;

/**
 * 充值卡密
 * @author zchuanzhao@linewell.com
 * 2018/11/27
 */
@Table("tbl_cardkey")
public class Cardkey extends BaseEntity {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @Column(value = "create_time", currTime = FillTime.INSERT)
    private Date createTime;
    /**
     * 卡号
     */
    @Column("no")
    private String no;
    /**
     * 卡密金额
     */
    @Column("money")
    private Double money;
    /**
     * 过期时间
     */
    @Column("expire_time")
    private Date expireTime;
    /**
     * 状态，0未使用，1已使用
     */
    @Column("status")
    private Integer status;
    /**
     * 卡密使用的会员ID
     */
    @Column("member_id")
    private Integer memberId;
    private Member member;

    /**
     * 使用时间
     */
    @Column("use_time")
    private Date useTime;

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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }
}
