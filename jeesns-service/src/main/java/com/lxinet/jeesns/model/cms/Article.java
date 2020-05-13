package com.lxinet.jeesns.model.cms;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxinet.jeesns.core.annotation.Column;
import com.lxinet.jeesns.core.annotation.Id;
import com.lxinet.jeesns.core.annotation.Table;
import com.lxinet.jeesns.core.enums.FillTime;
import com.lxinet.jeesns.core.enums.IdType;
import com.lxinet.jeesns.model.member.Member;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章实体类
 * Created by zchuanzhao on 2016/11/26.
 */
@Table("tbl_article")
public class Article implements Serializable {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @Column("collect_time")
    private Date collectTime;
    @Digits(integer = 11,fraction = 0,message = "栏目不能为空")
    @Column("cate_id")
    private Integer cateId;
    @Column("status")
    private Integer status;
    @Column("title")
    @NotBlank(message = "文章标题不能为空")
    private String title;
    @Column("member_id")
    private Integer memberId;
    private Member member;
    @Column(value = "create_time", currTime = FillTime.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Column("description")
    private String description;
    @Column("keywords")
    private String keywords;
    @Column("view_rank")
    @Digits(integer = 1,fraction = 0,message = "浏览权限只能是数字")
    private Integer viewRank;
    @Column("view_count")
    @Digits(integer = 11,fraction = 0,message = "浏览数只能是数字")
    private Integer viewCount;
    @Column("writer")
    private String writer;
    @Column("source")
    private String source;
    @Column("pub_time")
    private Date pubTime;
    @Column("update_time")
    private Date updateTime;
    @Column("thumbnail")
    private String thumbnail;
    @Column("last_reply")
    private Date lastReply;
    @Column("can_reply")
    private Integer canReply;
    @Column("good_num")
    private Integer goodNum;
    @Column("bad_num")
    private Integer badNum;
    @Column("check_admin")
    private Integer checkAdmin;
    @Column("content")
    @NotBlank(message = "文章内容不能为空")
    private String content;
    /**
     * 喜欢数量
     */
    @Column("favor")
    private Integer favor;

    /**
     * 会员是否已点击喜欢
     */
    private Integer isFavor;

    private ArticleCate articleCate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public ArticleCate getArticleCate() {
        return articleCate;
    }

    public void setArticleCate(ArticleCate articleCate) {
        this.articleCate = articleCate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getViewRank() {
        return viewRank;
    }

    public void setViewRank(Integer viewRank) {
        this.viewRank = viewRank;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getThumbnail() {
        return "".equals(thumbnail) ? null:thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Date getLastReply() {
        return lastReply;
    }

    public void setLastReply(Date lastReply) {
        this.lastReply = lastReply;
    }

    public Integer getCanReply() {
        return canReply;
    }

    public void setCanReply(Integer canReply) {
        this.canReply = canReply;
    }

    public Integer getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(Integer goodNum) {
        this.goodNum = goodNum;
    }

    public Integer getBadNum() {
        return badNum;
    }

    public void setBadNum(Integer badNum) {
        this.badNum = badNum;
    }

    public Integer getCheckAdmin() {
        return checkAdmin;
    }

    public void setCheckAdmin(Integer checkAdmin) {
        this.checkAdmin = checkAdmin;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFavor() {
        return favor;
    }

    public void setFavor(Integer favor) {
        this.favor = favor;
    }

    public Integer getIsFavor() {
        return isFavor;
    }

    public void setIsFavor(Integer isFavor) {
        this.isFavor = isFavor;
    }
}