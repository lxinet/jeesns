package cn.jeesns.model.group;

import cn.jeesns.core.annotation.Column;
import cn.jeesns.core.annotation.Id;
import cn.jeesns.core.annotation.Table;
import cn.jeesns.core.enums.FillTime;
import cn.jeesns.core.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: zchuanzhao
 * @date: 2018/5/8 下午10:59
 */
@Table("tbl_group_topic_type")
public class GroupTopicType implements Serializable {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @Column(value = "create_time", currTime = FillTime.INSERT)
    private Date createTime;
    @Column("group_id")
    private Integer groupId;
    @Column("name")
    private String name;
    @Column("juri")
    private Integer juri;

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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getJuri() {
        return juri;
    }

    public void setJuri(Integer juri) {
        this.juri = juri;
    }
}
