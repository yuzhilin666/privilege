package com.imooc.service;

import java.util.List;
import java.util.Map;

import com.imooc.pojo.Orders;
import com.imooc.pojo.OrdersSubitems;

public interface OrderService {

	/**
	 * @Description: 创建待付款订单
	 */
	public String createOrder(List<Map<String, Object>> subItemList, String buyerId, String remark, String addressId);
	
	/**
	 * @Description: 订单状态的变更
	 */
	public void changeToWaitAccept(String orderId);
//	public void changeToRefund(String orderId);
	public void changeToFinished(String orderId);
	public void changeToCanceled(String orderId);

	/**
	 * @Description: 获取订单信息
	 */
	public Orders getOrderInfo(String orderId);
	
	/**
	 * @Description: 查询用户所有订单
	 */
	public List<Orders> getUserOrderList(String userId, Integer orderStatus);
	
	/**
	 * @Description: 查询状态查询用户所有订单
	 */
	public List<Orders> getUserOrderListByStatus(String userId, Integer status);
	
	/**
	 * @Description: 查询订单下的附属商品list
	 */
	public List<OrdersSubitems> getOrdersSubitemsList(String orderId);
}
