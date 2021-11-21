package com.imooc.pojo.vo;

import java.util.Date;
import java.util.List;

import com.imooc.pojo.OrdersSubitems;

public class OrdersVO {
	
    private String id;

    private String userId;

    private String addressId;

    private Integer amount;
    
    private String amountY;

    private Integer status;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private List<OrdersSubitemsVO> subItemsList;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public List<OrdersSubitemsVO> getSubItemsList() {
		return subItemsList;
	}
	public void setSubItemsList(List<OrdersSubitemsVO> subItemsList) {
		this.subItemsList = subItemsList;
	}
	public String getAmountY() {
		return amountY;
	}
	public void setAmountY(String amountY) {
		this.amountY = amountY;
	}
    
}