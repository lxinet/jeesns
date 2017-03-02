package com.lxinet.jeesns.core.entity;

import java.util.Date;

/**
 * Created by zchuanzhao on 2017/3/1.
 */
public class Picture extends BaseEntity {
    private Integer id;
    private Date createTime;
    private Integer type;
    private String path;
    private String narrowPath;
    private String md5;
    private Integer width;
    private Integer height;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNarrowPath() {
        return narrowPath;
    }

    public void setNarrowPath(String narrowPath) {
        this.narrowPath = narrowPath;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}