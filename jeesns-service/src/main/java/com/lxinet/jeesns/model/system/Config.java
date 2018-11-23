package com.lxinet.jeesns.model.system;

import com.lxinet.jeesns.core.annotation.Column;
import com.lxinet.jeesns.core.annotation.Table;

import java.io.Serializable;

/**
 * 系统设置信息实体类
 * Created by zchuanzhao on 2016/11/26.
 */
@Table("tbl_config")
public class Config implements Serializable {
    @Column("jkey")
    private String jkey;
    @Column("jvalue")
    private String jvalue;
    @Column("description")
    private String description;

    public String getJkey() {
        return jkey;
    }

    public void setJkey(String jkey) {
        this.jkey = jkey;
    }

    public String getJvalue() {
        return jvalue;
    }

    public void setJvalue(String jvalue) {
        this.jvalue = jvalue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}