package cn.jeesns.model.question;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.jeesns.core.annotation.Column;
import cn.jeesns.core.annotation.Id;
import cn.jeesns.core.annotation.Table;
import cn.jeesns.core.enums.FillTime;
import cn.jeesns.core.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 问题分类
 * @author zchuanzhao@linewell.com
 * 2018/12/7
 */
@Table("tbl_question_type")
public class QuestionType implements Serializable {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @Column(value = "create_time", currTime = FillTime.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Column("name")
    private String name;
    @Column("sort")
    private Integer sort;
    @Column("bonus_type")
    private Integer bonusType;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getBonusType() {
        return bonusType;
    }

    public void setBonusType(Integer bonusType) {
        this.bonusType = bonusType;
    }
}
