package com.lxinet.jeesns.model.system;

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
 * Created by zchuanzhao on 2017/2/14.
 */
@Table("tbl_action_log")
public class ActionLog implements Serializable {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @Column(value = "create_time", currTime = FillTime.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Column("member_id")
    private Integer memberId;
    private Member member;
    @Column("action_id")
    private Integer actionId;
    private Action action;
    @Column("remark")
    private String remark;
    @Column("action_ip")
    private String actionIp;
    @Column("type")
    private Integer type;
    @Column("foreign_id")
    private Integer foreignId;

    public ActionLog(){

    }
    public ActionLog(Integer memberId,Integer actionId,String remark,String actionIp){
        this.memberId = memberId;
        this.actionId = actionId;
        this.remark = remark;
        this.actionIp = actionIp;

    }
    public ActionLog(Integer memberId,Integer actionId,String remark,String actionIp,Integer type,Integer foreignId){
        this.memberId = memberId;
        this.actionId = actionId;
        this.remark = remark;
        this.actionIp = actionIp;
        this.type = type;
        this.foreignId = foreignId;
    }



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

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getActionIp() {
        return actionIp;
    }

    public void setActionIp(String actionIp) {
        this.actionIp = actionIp;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getForeignId() {
        return foreignId;
    }

    public void setForeignId(Integer foreignId) {
        this.foreignId = foreignId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
