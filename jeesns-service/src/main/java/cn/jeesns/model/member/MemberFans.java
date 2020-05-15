package cn.jeesns.model.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.jeesns.core.annotation.Column;
import cn.jeesns.core.annotation.Id;
import cn.jeesns.core.annotation.Table;
import cn.jeesns.core.enums.FillTime;
import cn.jeesns.core.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zchuanzhao on 17/2/15.
 */
@Table("tbl_member_fans")
public class MemberFans implements Serializable {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(value = "create_time", currTime = FillTime.INSERT)
    private Date createTime;
    @Column("follow_who")
    private Integer followWho;
    private Member followWhoMember;
    @Column("who_follow")
    private Integer whoFollow;
    private Member whoFollowMember;

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

    public Integer getFollowWho() {
        return followWho;
    }

    public void setFollowWho(Integer followWho) {
        this.followWho = followWho;
    }

    public Member getFollowWhoMember() {
        return followWhoMember;
    }

    public void setFollowWhoMember(Member followWhoMember) {
        this.followWhoMember = followWhoMember;
    }

    public Integer getWhoFollow() {
        return whoFollow;
    }

    public void setWhoFollow(Integer whoFollow) {
        this.whoFollow = whoFollow;
    }

    public Member getWhoFollowMember() {
        return whoFollowMember;
    }

    public void setWhoFollowMember(Member whoFollowMember) {
        this.whoFollowMember = whoFollowMember;
    }
}