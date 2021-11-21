package com.imooc.pojo;

import java.util.Date;
import javax.persistence.*;

public class Address {
    @Id
    private String id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 收件人
     */
    private String receiver;

    /**
     * 手机号
     */
    private String mobile;

    private String province;

    private String city;

    private String district;

    /**
     * 详细地址
     */
    @Column(name = "desc_address")
    private String descAddress;

    /**
     * 是否默认地址
1：是
0：否
     */
    @Column(name = "is_default")
    private Integer isDefault;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取收件人
     *
     * @return receiver - 收件人
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * 设置收件人
     *
     * @param receiver 收件人
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * 获取详细地址
     *
     * @return desc_address - 详细地址
     */
    public String getDescAddress() {
        return descAddress;
    }

    /**
     * 设置详细地址
     *
     * @param descAddress 详细地址
     */
    public void setDescAddress(String descAddress) {
        this.descAddress = descAddress;
    }

    /**
     * 获取是否默认地址
1：是
0：否
     *
     * @return is_default - 是否默认地址
1：是
0：否
     */
    public Integer getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否默认地址
1：是
0：否
     *
     * @param isDefault 是否默认地址
1：是
0：否
     */
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}