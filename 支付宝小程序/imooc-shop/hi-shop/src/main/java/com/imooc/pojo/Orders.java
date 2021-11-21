package com.imooc.pojo;

import java.util.Date;
import javax.persistence.*;

public class Orders {
    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    /**
     * 收货地址关联表id
     */
    @Column(name = "address_id")
    private String addressId;

    /**
     * 订单总金额
     */
    private Integer amount;

    /**
     * 订单状态：
10：待付款
20：待发货
30：待收货
40：已退货
50：付款中
60：已完成
70：订单取消（人为主动取消）
80：交易关闭（长时间未付款，系统识别后自动关闭）
     */
    private Integer status;

    /**
     * 订单备注
     */
    private String remark;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取收货地址关联表id
     *
     * @return address_id - 收货地址关联表id
     */
    public String getAddressId() {
        return addressId;
    }

    /**
     * 设置收货地址关联表id
     *
     * @param addressId 收货地址关联表id
     */
    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    /**
     * 获取订单总金额
     *
     * @return amount - 订单总金额
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置订单总金额
     *
     * @param amount 订单总金额
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 获取订单状态：
10：待付款
20：待发货
30：待收货
40：已退货
50：付款中
60：已完成
70：订单取消（人为主动取消）
80：交易关闭（长时间未付款，系统识别后自动关闭）
     *
     * @return status - 订单状态：
10：待付款
20：待发货
30：待收货
40：已退货
50：付款中
60：已完成
70：订单取消（人为主动取消）
80：交易关闭（长时间未付款，系统识别后自动关闭）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态：
10：待付款
20：待发货
30：待收货
40：已退货
50：付款中
60：已完成
70：订单取消（人为主动取消）
80：交易关闭（长时间未付款，系统识别后自动关闭）
     *
     * @param status 订单状态：
10：待付款
20：待发货
30：待收货
40：已退货
50：付款中
60：已完成
70：订单取消（人为主动取消）
80：交易关闭（长时间未付款，系统识别后自动关闭）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取订单备注
     *
     * @return remark - 订单备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置订单备注
     *
     * @param remark 订单备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}