package com.imooc.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.mapper.ItemsMapper;
import com.imooc.mapper.OrdersMapper;
import com.imooc.mapper.OrdersSubitemsMapper;
import com.imooc.pojo.Items;
import com.imooc.pojo.Orders;
import com.imooc.pojo.OrdersSubitems;
import com.imooc.service.OrderService;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.OrderStatusEnum;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ItemsMapper itemsMapper;
	
	@Autowired
	private OrdersMapper ordersMapper;
	
	@Autowired
	private OrdersSubitemsMapper ordersSubitemsMapper;
	
	@Autowired
	private Sid sid;

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public String createOrder(List<Map<String, Object>> subItemList, String buyerId, String remark, String addressId) {
		
		String orderId = sid.nextShort();
		Integer totalAmount = 0;
		// 循环保存subitem
		for (Map<String, Object> itemMap : subItemList) {
			String itemId = (String)itemMap.get("itemId");
			String countsStr = (String)itemMap.get("counts");
			Integer counts = Integer.valueOf(countsStr);
			
			// 查询item
			Items item = itemsMapper.selectByPrimaryKey(itemId);
			
			String headerImages = item.getHeaderImages();
			List<String> covers = JsonUtils.jsonToList(headerImages, String.class);
			
			
			OrdersSubitems subItem = new OrdersSubitems();
			String subItemId = sid.nextShort();
			subItem.setId(subItemId);
			subItem.setItemId(itemId);
			subItem.setBuyCounts(counts);
			subItem.setItemImage(covers.get(0));
			subItem.setOrderId(orderId);
			subItem.setPrice(item.getPriceDiscount());
			subItem.setItemName(item.getName());
			
			totalAmount += item.getPriceDiscount() * counts;
			
			ordersSubitemsMapper.insert(subItem);
		}
		
		// 保存订单
		Orders order = new Orders();
		order.setId(orderId);
		order.setAmount(totalAmount);
		order.setAddressId(addressId);
		order.setUserId(buyerId);
		order.setStatus(OrderStatusEnum.WAIT_PAY.key);
		order.setRemark(remark);
		order.setCreateTime(new Date());
		order.setUpdateTime(new Date());
		
		ordersMapper.insert(order);
		
		return orderId;
	}

	@Override
	public void changeToWaitAccept(String orderId) {
		Example example = new Example(Orders.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", orderId);
		
		Orders order = new Orders();
		order.setStatus(OrderStatusEnum.WAIT_ACCEPT.key);
		order.setUpdateTime(new Date());
		ordersMapper.updateByExampleSelective(order, example);
	}

//	@Override
//	public void changeToRefund(String orderId) {
//		Example example = new Example(Orders.class);
//		Criteria criteria = example.createCriteria();
//		criteria.andEqualTo("id", orderId);
//		
//		Orders order = new Orders();
//		order.setStatus(OrderStatusEnum.REFUND.key);
//		ordersMapper.updateByExampleSelective(order, example);		
//	}

	@Override
	public void changeToFinished(String orderId) {
		Example example = new Example(Orders.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", orderId);
		
		Orders order = new Orders();
		order.setStatus(OrderStatusEnum.FINISHED.key);
		order.setUpdateTime(new Date());
		ordersMapper.updateByExampleSelective(order, example);		
	}

	@Override
	public void changeToCanceled(String orderId) {
		Example example = new Example(Orders.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", orderId);
		
		Orders order = new Orders();
		order.setStatus(OrderStatusEnum.CANCELED.key);
		order.setUpdateTime(new Date());
		ordersMapper.updateByExampleSelective(order, example);		
	}

	@Override
	public Orders getOrderInfo(String orderId) {
		return ordersMapper.selectByPrimaryKey(orderId);
	}

	@Override
	public List<Orders> getUserOrderList(String userId, Integer orderStatus) {
		Example example = new Example(Orders.class);
		example.orderBy("updateTime").desc();
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userId", userId);
		
		if (orderStatus != null && orderStatus != 0) {
			criteria.andEqualTo("status", orderStatus);
		}
		
		List<Orders> orderList = ordersMapper.selectByExample(example);
		return orderList;
	}

	@Override
	public List<Orders> getUserOrderListByStatus(String userId, Integer status) {
		Example example = new Example(Orders.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userId", userId);
		criteria.andEqualTo("status", status);
		
		List<Orders> orderList = ordersMapper.selectByExample(example);
		return orderList;
	}

	@Override
	public List<OrdersSubitems> getOrdersSubitemsList(String orderId) {
		Example example = new Example(OrdersSubitems.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("orderId", orderId);
		
		List<OrdersSubitems> orderList = ordersSubitemsMapper.selectByExample(example);
		return orderList;
	}
	
}
