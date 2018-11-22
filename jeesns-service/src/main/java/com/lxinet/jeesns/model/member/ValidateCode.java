package com.lxinet.jeesns.model.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lxinet.jeesns.core.annotation.Column;
import com.lxinet.jeesns.core.annotation.Id;
import com.lxinet.jeesns.core.annotation.Table;
import com.lxinet.jeesns.core.enums.FillTime;
import com.lxinet.jeesns.core.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 验证码
 * Created by zchuanzhao on 17/01/20.
 */
@Table("tbl_validate_code")
public class ValidateCode implements Serializable {
	@Id(value = "id", type = IdType.AUTO)
	private Integer id;
	@Column(value = "create_time", currTime = FillTime.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@Column("email")
	private String email;
	@Column("code")
	private String code;
	@Column("status")
	private Integer status;
	@Column("type")
	private Integer type;

	public ValidateCode(){

	}

	public ValidateCode(String email, String code, Integer type) {
		this.email = email;
		this.code = code;
		this.type = type;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}