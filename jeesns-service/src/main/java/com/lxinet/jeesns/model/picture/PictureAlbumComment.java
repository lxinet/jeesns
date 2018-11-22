package com.lxinet.jeesns.model.picture;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxinet.jeesns.core.annotation.Column;
import com.lxinet.jeesns.core.annotation.Id;
import com.lxinet.jeesns.core.annotation.Table;
import com.lxinet.jeesns.core.enums.FillTime;
import com.lxinet.jeesns.core.enums.IdType;
import org.aspectj.weaver.Member;

import java.util.Date;

@Table("tbl_picture_album_comment")
public class PictureAlbumComment {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @Column(value = "create_time", currTime = FillTime.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Column("member_id")
    private Integer memberId;

    private Member member;

    @Column("picture_album_id")
    private Integer pictureAlbumId;

    private PictureAlbum pictureAlbum;

    @Column("content")
    private String content;

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

    public Integer getPictureAlbumId() {
        return pictureAlbumId;
    }

    public void setPictureAlbumId(Integer pictureAlbumId) {
        this.pictureAlbumId = pictureAlbumId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public PictureAlbum getPictureAlbum() {
        return pictureAlbum;
    }

    public void setPictureAlbum(PictureAlbum pictureAlbum) {
        this.pictureAlbum = pictureAlbum;
    }
}