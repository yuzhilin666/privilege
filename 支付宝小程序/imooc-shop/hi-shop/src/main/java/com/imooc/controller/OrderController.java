package com.imooc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.pojo.Orders;
import com.imooc.pojo.OrdersSubitems;
import com.imooc.pojo.vo.OrdersSubitemsVO;
import com.imooc.pojo.vo.OrdersVO;
import com.imooc.service.OrderService;
import com.imooc.utils.AmountUtils;
import com.imooc.utils.IMoocJSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value="订单业务与支付", tags={" 处理订单业务"})
@RequestMapping("order")
public class OrderController extends BasicController {

//	订单状态：
//	10：待付款
//	20：待收货
//	30：已退货
//	40：已完成
//	50：已取消（人为主动取消）
//	60：交易关闭（长时间未付款，系统识别后自动关闭）
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * TODO:
	 * 根据订单状态查询订单列表
	 * 提交订单接口 - 创建订单（待付款）
	 * 订单支付成功后的接口（待发货）
	 */
	
	/**
	 * 分割商品id以及购买件数 1001-5,2001-3,5001-1,
	 */
	@ApiOperation(value="生成[待付款]订单", notes="分割商品id以及购买件数 例：1001|5,2001|3,5001|1,  【返回的订单id需要传参到支付页面】", httpMethod = "POST")
	@PostMapping("/createOrder")
	public IMoocJSONResult createOrder(
			 @ApiParam(name = "itemStr", value = "商品id与件数的组装字符串", required = true)
			 @RequestParam String itemStr,
			 @ApiParam(name = "buyerId", value = "购买者用户的id", required = true)
			 @RequestParam String buyerId,
			 @ApiParam(name = "remark", value = "用户备注", required = false)
			 @RequestParam String remark,
			 @ApiParam(name = "addressId", value = "用户地址id", required = false)
			 @RequestParam String addressId) {
		
		if (StringUtils.isBlank(itemStr) || StringUtils.isBlank(buyerId)) {
			return IMoocJSONResult.errorMsg("参数不正确...");
		}
		
		if (remark.length() > 15) {
			return IMoocJSONResult.errorMsg("备注不能超过15字...");
		}
		
		// 分割为商品id和件数
		String itemSingle[] = itemStr.split(",");
		if (itemSingle == null || itemSingle.length <= 0) {
			return IMoocJSONResult.errorMsg("参数不正确...");
		}
		
		List<Map<String, Object>> subItemList = new ArrayList<>();
		for (String str : itemSingle) {
			String property[] = str.split("\\|");
			Map<String, Object> itemMap = new HashMap<>();
			itemMap.put("itemId", property[0]);
			itemMap.put("counts", property[1]);
			subItemList.add(itemMap);
		}
		
		if (subItemList == null || subItemList.isEmpty() || subItemList.size() <= 0) {
			return IMoocJSONResult.errorMsg("参数不正确...");
		}
		
		// 生成待付款订单
		String orderId = orderService.createOrder(subItemList, buyerId, remark, addressId);
		
		// 返回待付款的订单id
		return IMoocJSONResult.ok(orderId);
	}
	
	@ApiOperation(value="订单状态变更: 待付款 -> 待收货", notes="用户付款完毕后，订单状态变为[待收货]", httpMethod = "POST")
	@PostMapping("/changeToWaitAccept")
	public IMoocJSONResult changeToWaitAccept(
			 @ApiParam(name = "orderId", value = "订单编号", required = true)
			 @RequestParam String orderId) {
		
		if (StringUtils.isBlank(orderId)) {
			return IMoocJSONResult.errorMsg("参数不正确...");
		}
		
		orderService.changeToWaitAccept(orderId);
		
		// 返回待付款的订单id
		return IMoocJSONResult.ok();
	}
	
//	@ApiOperation(value="订单状态变更: 待收货 -> 已退货", notes="用户不满意，订单状态变为[已退货]", httpMethod = "POST")
//	@PostMapping("/changeTorRefund")
//	public IMoocJSONResult changeTorRefund(
//			 @ApiParam(name = "orderId", value = "订单编号", required = true)
//			 @RequestParam String orderId) {
//		
//		if (StringUtils.isBlank(orderId)) {
//			return IMoocJSONResult.errorMsg("参数不正确...");
//		}
//		
//		orderService.changeToRefund(orderId);
//		
//		// 返回待付款的订单id
//		return IMoocJSONResult.ok();
//	}
	
	@ApiOperation(value="订单状态变更: 待收货 -> 已完成", notes="用户收到货之后，订单状态变为[已完成]", httpMethod = "POST")
	@PostMapping("/changeToFinished")
	public IMoocJSONResult changeToFinished(
			 @ApiParam(name = "orderId", value = "订单编号", required = true)
			 @RequestParam String orderId) {
		
		if (StringUtils.isBlank(orderId)) {
			return IMoocJSONResult.errorMsg("参数不正确...");
		}
		
		orderService.changeToFinished(orderId);
		
		// 返回待付款的订单id
		return IMoocJSONResult.ok();
	}
	
	@ApiOperation(value="订单状态变更: 待付款 -> 已取消", notes="用户不高兴付款直接取消订单，订单状态变为[已取消]", httpMethod = "POST")
	@PostMapping("/changeToCanceled")
	public IMoocJSONResult changeToCanceled(
			 @ApiParam(name = "orderId", value = "订单编号", required = true)
			 @RequestParam String orderId) {
		
		if (StringUtils.isBlank(orderId)) {
			return IMoocJSONResult.errorMsg("参数不正确...");
		}
		
		orderService.changeToCanceled(orderId);
		
		// 返回待付款的订单id
		return IMoocJSONResult.ok();
	}
	
	@ApiOperation(value="查询所有订单", notes="根据用户id查询该用户的所有状态订单", httpMethod = "POST")
	@PostMapping("/queryAllOrders")
	public IMoocJSONResult queryAllOrders(
			 @ApiParam(name = "userId", value = "用户id", required = true)
			 @RequestParam String userId,
			 @ApiParam(name = "orderStatus", value = "订单状态  【为空或者为0，则查询全部】", required = true)
			 @RequestParam Integer orderStatus) throws Exception {
		
		if (StringUtils.isBlank(userId)) {
			return IMoocJSONResult.errorMsg("参数不正确...");
		}
		
		List<Orders> orders = orderService.getUserOrderList(userId, orderStatus);
		// 循环orders，把每个订单的商品都构建成这个orders的一个属性
		List<OrdersVO> ordersVOList = new ArrayList<>(); 
		for (Orders o : orders) {
			OrdersVO ordersVO = new OrdersVO();
			BeanUtils.copyProperties(o, ordersVO);
			
			// 查询订单下的附属商品list
			List<OrdersSubitems> subItemsList = orderService.getOrdersSubitemsList(o.getId());
			List<OrdersSubitemsVO> subItemsVOList = new ArrayList<>(); 
			for (OrdersSubitems item : subItemsList) {
				String yuan = AmountUtils.changeF2Y(String.valueOf(item.getPrice()));
				OrdersSubitemsVO ordersSubitemsVO = new OrdersSubitemsVO();
				BeanUtils.copyProperties(item, ordersSubitemsVO);
				ordersSubitemsVO.setPriceY(yuan);
				subItemsVOList.add(ordersSubitemsVO);
			}
			ordersVO.setSubItemsList(subItemsVOList);
			
			ordersVO.setAmountY(AmountUtils.changeF2Y(String.valueOf(o.getAmount())));
			
			ordersVOList.add(ordersVO);
		}
		
		return IMoocJSONResult.ok(ordersVOList);
	}
}
