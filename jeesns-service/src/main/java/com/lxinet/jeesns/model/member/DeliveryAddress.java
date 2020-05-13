package com.lxinet.jeesns.model.member;

import com.lxinet.jeesns.core.annotation.Column;
import com.lxinet.jeesns.core.annotation.Id;
import com.lxinet.jeesns.core.annotation.Table;
import com.lxinet.jeesns.core.enums.FillTime;
import com.lxinet.jeesns.core.enums.IdType;
import com.lxinet.jeesns.model.common.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Table("tbl_delivery_address")
public class DeliveryAddress extends BaseEntity {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @Column(value = "create_time", currTime = FillTime.INSERT)
    private Date createTime;
    @Column("member_id")
    private Integer memberId;
    private Member member;
    @Column("name")
//    @Length(max = 32, message = "收件人姓名长度必须小于等于32个字符")
    @NotBlank(message = "收件人姓名不能为空")
    private String name;
    @Column("province")
    @Length(max = 32, message = "省份名称长度必须小于等于32个字符")
    @NotBlank(message = "省份名称不能为空")
    private String province;
    @Column("city")
    @Length(max = 32, message = "城市名称长度必须小于等于32个字符")
    @NotBlank(message = "城市名称不能为空")
    private String city;
    @Column("area")
    @Length(max = 32, message = "地区名称长度必须小于等于32个字符")
    @NotBlank(message = "地区名称不能为空")
    private String area;
    @Column("address")
    @Length(max = 128, message = "地址长度必须小于等于128个字符")
    @NotBlank(message = "地址不能为空")
    private String address;
    @Column("zip")
    @Length(min = 6, max = 6, message = "邮政编码长度必须等于6个字符")
    @NotBlank(message = "邮政编码不能为空")
    private String zip;
    @Column("phone")
    @Length(max = 11, message = "手机号码长度必须小于等于11个字符")
    @NotBlank(message = "手机号码不能为空")
    private String phone;
    @Column("is_default")
    private Integer isDefault;


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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}
