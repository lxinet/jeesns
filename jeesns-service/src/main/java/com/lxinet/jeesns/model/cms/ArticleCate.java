package com.lxinet.jeesns.model.cms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxinet.jeesns.core.annotation.Column;
import com.lxinet.jeesns.core.annotation.Id;
import com.lxinet.jeesns.core.annotation.Table;
import com.lxinet.jeesns.core.enums.FillTime;
import com.lxinet.jeesns.core.enums.IdType;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章栏目实体类
 * Created by zchuanzhao on 2016/11/26.
 */
@Table("tbl_article_cate")
public class ArticleCate implements Serializable {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @Column("fid")
    @Digits(integer = 11,fraction = 0,message = "上级栏目不能为空")
    private Integer fid;
    @Column("name")
    @NotBlank(message = "栏目名称不能为空")
    private String name;
    @Column("status")
    private Integer status;
    @Column("sort")
    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}