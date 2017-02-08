package com.lxinet.jeesns.modules.weibo.entity;

import com.lxinet.jeesns.core.entity.BaseEntity;
import java.util.Date;

/**
 * Created by zchuanzhao on 2017/2/8.
 */
public class WeiboFavor extends BaseEntity {
    private Integer id;
    private Date createTime;
    private Integer memberId;
    private Integer weiboId;

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

    public Integer getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(Integer weiboId) {
        this.weiboId = weiboId;
    }
}
