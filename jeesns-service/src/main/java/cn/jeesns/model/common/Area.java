package cn.jeesns.model.common;

import cn.jeesns.core.annotation.Column;
import cn.jeesns.core.annotation.Id;
import cn.jeesns.core.annotation.Table;
import cn.jeesns.core.enums.IdType;

/**
 * 省市区
 * Created by zchuanzhao on 2019/5/16.
 */
@Table("tbl_area")
public class Area extends BaseEntity {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @Column("name")
    private String name;
    @Column("code")
    private String code;
    @Column("fcode")
    private String fcode;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFcode() {
        return fcode;
    }

    public void setFcode(String fcode) {
        this.fcode = fcode;
    }
}
