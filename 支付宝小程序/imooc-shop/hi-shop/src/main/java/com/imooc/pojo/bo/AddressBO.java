package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModelProperty;

public class AddressBO {

	@ApiModelProperty("用户id")
    private String userId;

	@ApiModelProperty("收件人")
    private String receiver;

	@ApiModelProperty("手机号")
    private String mobile;

	@ApiModelProperty("省份")
    private String province;

	@ApiModelProperty("城市")
    private String city;

	@ApiModelProperty("区")
    private String district;

	@ApiModelProperty("详细地址")
    private String descAddress;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDescAddress() {
		return descAddress;
	}

	public void setDescAddress(String descAddress) {
		this.descAddress = descAddress;
	}

}