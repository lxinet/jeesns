package com.lxinet.jeesns.modules.sys.entity;

import com.lxinet.jeesns.core.entity.BaseEntity;
import com.lxinet.jeesns.modules.mem.entity.Member;

import java.util.Date;

/**
 * Created by zchuanzhao on 2017/3/24.
 */
public class ScoreRule extends BaseEntity {
    private Integer id;
    private Date createTime;
    private Date updateTime;
    private String name;
    private Integer score;
    private String remark;
    private String type;
    private Integer status;

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
