package com.imooc.utils;

/**
 * 
 * @Title: OrderStatusEnum.java
 * @Package com.itzixi.common.enums
 * @Description: 订单状态
 * Copyright: Copyright (c) 2016
 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
 * 
 * @author leechenxiang
 * @date 2017年4月24日 下午4:31:20
 * @version V1.0
 */
public enum OrderStatusEnum {

//	订单状态：
//	10：待付款
//	20：已付款，待收货
//	30：已退货		【废弃】
//	40：已完成
//	50：已取消（人为主动取消）
//	60：交易关闭（长时间未付款，系统识别后自动关闭）
	
	WAIT_PAY(10, "待付款"),			// 代付款		
	WAIT_ACCEPT(20, "待收货"),				// 待收货
//	REFUND(30, "已退货"),				// 已退货
	FINISHED(40, "已完成"),				// 已完成
	CANCELED(50, "已取消");			// 已取消
	
	public final int key;
	public final String value;
	
	OrderStatusEnum(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public static String getName(int key) {
		for (OrderStatusEnum status : OrderStatusEnum.values()) {
			if (status.getKey() == key) {
				return status.value;
			}
		}
		return null;
	}
	 
	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
