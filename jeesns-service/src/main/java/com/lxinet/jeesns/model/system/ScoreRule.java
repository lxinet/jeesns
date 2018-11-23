package com.lxinet.jeesns.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxinet.jeesns.core.annotation.Column;
import com.lxinet.jeesns.core.annotation.Id;
import com.lxinet.jeesns.core.annotation.Table;
import com.lxinet.jeesns.core.enums.FillTime;
import com.lxinet.jeesns.core.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zchuanzhao on 2017/3/24.
 */
@Table("tbl_score_rule")
public class ScoreRule implements Serializable {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @Column(value = "create_time", currTime = FillTime.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Column("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @Column("name")
    private String name;
    @Column("score")
    private Integer score;
    @Column("remark")
    private String remark;
    @Column("type")
    private String type;
    @Column("status")
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
