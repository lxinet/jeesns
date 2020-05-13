package com.lxinet.jeesns.model.shop;

import com.lxinet.jeesns.core.annotation.Column;
import com.lxinet.jeesns.core.annotation.Id;
import com.lxinet.jeesns.core.annotation.Table;
import com.lxinet.jeesns.core.enums.IdType;
import java.io.Serializable;

/**
 * 商品分类
 * Created by zchuanzhao on 2019/5/15.
 */
@Table("tbl_goods_cate")
public class GoodsCate implements Serializable {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @Column("name")
    private String name;
    @Column("fid")
    private Integer fid;
    @Column("level")
    private Integer level;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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