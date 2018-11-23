package com.lxinet.jeesns.model.picture;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxinet.jeesns.core.annotation.Column;
import com.lxinet.jeesns.core.annotation.Id;
import com.lxinet.jeesns.core.annotation.Table;
import com.lxinet.jeesns.core.enums.FillTime;
import com.lxinet.jeesns.core.enums.IdType;
import com.lxinet.jeesns.model.member.Member;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zchuanzhao on 2017/3/1.
 */
@Table("tbl_picture")
public class Picture implements Serializable {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @Column(value = "create_time", currTime = FillTime.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Column("member_id")
    private Integer memberId;
    private Member member;
    @Column("type")
    private Integer type;
    @Column("foreign_id")
    private Integer foreignId;
    @Column("path")
    private String path;
    @Column("thumbnail_path")
    private String thumbnailPath;
    @Column("small_path")
    private String smallPath;
    @Column("md5")
    private String md5;
    @Column("width")
    private Integer width;
    @Column("height")
    private Integer height;
    @Column("description")
    private String description;
    @Column("comment_count")
    private Integer commentCount;
    @Column("favor_count")
    private Integer favorCount;
    @Column("album_id")
    private Integer albumId;
    @Column("picture_album")
    private PictureAlbum pictureAlbum;
    //是否已点赞，0未点赞，1已点赞
    @Column("is_favor")
    private Integer isFavor;

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

    public Integer getForeignId() {
        return foreignId;
    }

    public void setForeignId(Integer foreignId) {
        this.foreignId = foreignId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
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

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getFavorCount() {
        return favorCount;
    }

    public void setFavorCount(Integer favorCount) {
        this.favorCount = favorCount;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSmallPath() {
        return smallPath;
    }

    public void setSmallPath(String smallPath) {
        this.smallPath = smallPath;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public PictureAlbum getPictureAlbum() {
        return pictureAlbum;
    }

    public void setPictureAlbum(PictureAlbum pictureAlbum) {
        this.pictureAlbum = pictureAlbum;
    }

    public Integer getIsFavor() {
        return isFavor;
    }

    public void setIsFavor(Integer isFavor) {
        this.isFavor = isFavor;
    }
}