package com.lxinet.jeesns.model.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxinet.jeesns.core.annotation.Column;
import com.lxinet.jeesns.core.annotation.Id;
import com.lxinet.jeesns.core.annotation.Table;
import com.lxinet.jeesns.core.enums.FillTime;
import com.lxinet.jeesns.core.enums.IdType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 会员实体类
 * Created by zchuanzhao on 16/9/26.
 */
@Table("tbl_member")
public class Member implements Serializable {
	@Id(value = "id", type = IdType.AUTO)
	private Integer id;
	@Column(value = "create_time", currTime = FillTime.INSERT)
	private Date createTime;
	//分组ID
	@Column("group_id")
	private Integer groupId;
	//会员名称
	@Column("name")
	private String name;

	//邮箱
	@Column("email")
	private String email;

	//手机号码
	@Column("phone")
	private String phone;

	//密码
	@Column("password")
	private String password;

	//性别
	@Column("sex")
	private String sex;

	//头像
	@Column("avatar")
	private String avatar;

	//注册IP
	@Column("regip")
	private String regip;

	//登录次数
	@Column("login_count")
	private Integer loginCount;

	//本次登录时间
	@Column("curr_login_time")
	private Date currLoginTime;

	//本次登录IP
	@Column("curr_login_ip")
	private String currLoginIp;

	//最后登录时间
	@Column("last_login_time")
	private Date lastLoginTime;

	//最后登录IP
	@Column("last_login_ip")
	private String lastLoginIp;

	//更新资料时间
	@Column("update_time")
	private Date updateTime;

	//金额
	@Column("money")
	private Double money;

	//积分
	@Column("score")
	private Integer score;

	//是否已激活，0未激活，1已激活
	@Column("is_active")
	private Integer isActive;

	//0禁用，1启用
	@Column("status")
	private Integer status;

	//生日
	@Column("birthday")
	private String birthday;

	//居住省份
	@Column("addprovince")
	private String addprovince;

	//居住城市
	@Column("addcity")
	private String addcity;

	//居住地区
	@Column("addarea")
	private String addarea;

	//居住地址
	@Column("address")
	private String address;

	//QQ
	@Column("qq")
	private String qq;

	//微信
	@Column("wechat")
	private String wechat;

	//联系手机号
	@Column("contact_phone")
	private String contactPhone;

	//联系邮箱
	@Column("contact_email")
	private String contactEmail;

	//个人网站
	@Column("website")
	private String website;

	//个人介绍
	@Column("introduce")
	private String introduce;

	//是否管理员
	@Column("is_admin")
	private Integer isAdmin;

	//关注会员数
	@Column("follows")
	private Integer follows;

	//粉丝数
	@Column("fans")
	private Integer fans;

	//私信列表
	private List<Message> messages;

	//0普通会员，1是VIP
	@Column("is_vip")
	private Integer isVip;

	//会员等级ID
	@Column("member_level_id")
	private Integer memberLevelId;

	private MemberLevel memberLevel;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRegip() {
		return regip;
	}

	public void setRegip(String regip) {
		this.regip = regip;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddprovince() {
		return addprovince;
	}

	public void setAddprovince(String addprovince) {
		this.addprovince = addprovince;
	}

	public String getAddcity() {
		return addcity;
	}

	public void setAddcity(String addcity) {
		this.addcity = addcity;
	}

	public String getAddarea() {
		return addarea;
	}

	public void setAddarea(String addarea) {
		this.addarea = addarea;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Date getCurrLoginTime() {
		return currLoginTime;
	}

	public void setCurrLoginTime(Date currLoginTime) {
		this.currLoginTime = currLoginTime;
	}

	public String getCurrLoginIp() {
		return currLoginIp;
	}

	public void setCurrLoginIp(String currLoginIp) {
		this.currLoginIp = currLoginIp;
	}

	public Integer getFollows() {
		return follows;
	}

	public void setFollows(Integer follows) {
		this.follows = follows;
	}

	public Integer getFans() {
		return fans;
	}

	public void setFans(Integer fans) {
		this.fans = fans;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Integer getIsVip() {
		return isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}

	public Integer getMemberLevelId() {
		return memberLevelId;
	}

	public void setMemberLevelId(Integer memberLevelId) {
		this.memberLevelId = memberLevelId;
	}

	public MemberLevel getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(MemberLevel memberLevel) {
		this.memberLevel = memberLevel;
	}
}