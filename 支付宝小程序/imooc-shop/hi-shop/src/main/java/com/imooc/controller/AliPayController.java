package com.imooc.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.imooc.pojo.Orders;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.AlipayBizContent;
import com.imooc.service.OrderService;
import com.imooc.service.UserService;
import com.imooc.utils.AmountUtils;
import com.imooc.utils.DateUtil;
import com.imooc.utils.IMoocJSONResult;
import com.imooc.utils.JsonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
@Api(value = "支付接口", tags = { " 支付宝收银台支付" })
public class AliPayController extends BasicController {
	
	private static final String APPID = "";
	private static final String PRIVATE_KEY = "/lBitOG2uunyrrD1PY+VP8SFn/5hqJI+QR58ofOq0Ok9w5aRo+wB6vn6oTvR5MNWLtLZMQiH5VRW8HQRyHGGHo1fsSnW+JhIZ8sAY0pJ/y5ffW0D/NL2DfPTfPQA+zTW1z7NUYaEH8+vzhHPuMlofreBvU5lZMDeDQvujjfecpKwFOTQrH5J9lBpjCMWbdRNfv6GZBIW2AptLnzLscg85SoGU2I0nLgcNreE+QQzpFdv2ngDC79hRgcaZhrIAKjCT1pi6c8iWyz2py/GtaQITJjLXXtcxHcZXF24TUDB7LNIti/7eFwmFBMyMhO4/5JAgMBAAECggEAD/1BGLPnZpnv/ZJdZzdmae74Pca8RsUGup3ZGU91ACKCxtONUyQJGcYKW9X0Pr01qtyuqmy/v3fFZB3mQxsOZ4yXlXfTyFlduY4SJRCpUlR88CyFAkS/fdK+ULNE0UvOA6aDutZ+RAeKNcuHSXkjztpGRn/G5LsH0l/u+VsK4Qca3FTTB53+d9GGgd+zbotxGbc43E0fe/OHuJNDuX+uHvnBu8q6Y1T2Sc8bk8gv1NwG7gYCE/D1ZaoAmJSUmPX1JTowCZvFnCy0rdLfesBHRIFLInSVQpOV5KTE7ungf/BFhkl3ey2OOzU0EWXJ3hglWQygHNrDa+H1iAARRkRpAQKBgQC2HgKI1NoO/NDsoocZMLoHG/BznIfg8LyGYmYEBDdVVtCQdOQ6relxJvG6GR94V/JwFFWIoLEG51qEbdiqHbt2Y2D5muFvxQK8WsPZgPBfkGGwTKh6o3MJJIz7q0SoUecQNGz7RWbYNTezdrIamkAbvPSNYng6vhFYUC2vEaKmaQKBgQC0RbaufJL4vL7UZnAFM2fUUMuYi6yxex57EMjLyoq5fpUlKqY2PWIPogBVAtPy7Fl4OkGGn6mSnXcDzVbqsrxxQYJ1ymu4HEVM26JiSGdnhU5NbRoS5vK4ZSSpk/PGW4cxDAnEm2uk/4Q8jm2ZTP0+vKexvM018156GlHO8dJc4QKBgB8owUxCBtEbpm9mW3EO6jU/LYPKW2E8LzVa7pNvWTxLr0GYDliP2uoWPLOXis/fVEai5AONYtyIhu8zkCeDEMjvte7e9gVWkuJBMVMsgXQIUv6EDyt0fcEptMl2gze9U6htH9xaAn+pk08sVsrAa4mTadAq8eEzFeqjRT236blJAoGBAKeqd0BOSPvjwLEYHXQY3acRBmt47UOdOTpI4Bj65o+j9d+9jR3BdaUIjbqDiNHVlVlHBY7KjRL6NalpoD9lwfKsdURM4mc4lBPp61vx74rO54cHxEp0Vqe+Gp1GKGMC2fh6M9OSokmyBTG1XqZIfvmMyYN8EHjvQ53ZQOFy9K7BAoGAb4UlvWBMBk4buVt3vHJhIz9rSDWRa6A9ISV3hHb/tyfSNuIXgyYJajg3Z/MR8e5El0A/4W3LndJh9SJQJ7fWZ/N0nJCaDnKGts4bphA1+yVX5Jz2D3WNkzje48pGdumDdAHDqaIpUyIVbPd2Qd61ioOVRIzd5dVqEngxpNc85AA=";
	private static final String ALIPAY_PUBLIC_KEY = "+IQgAtcbB71PeUIFfRruiUrRq0afIBJyNUORMkGwySnVCCR3yuLqg7GlhMmRKM54lwZ4zvQOds2EgWs5Xv3pNqHgJR9MWcOCV9U2s4UdIS4/T3XamGmsnmUG9poPBkTL4Da1T7ySpdMz3VNGoAuImxPX/VUgxdrl3FXsI+2ZRgXCcy67V4ikfvLav7PdpDlmiPHQFis8aflM2QCDA1Zjj9Gtqr5wpmkBXi5Ca+e6N/O+EA85PMutZTYfUuEGTFAmD3mP4B4Mb/ujg89v612A3TcPgPHYhEv1aQIDAQAB";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "付款接口", notes = "支付宝小程序唤起登录后调用", httpMethod = "POST")
	@PostMapping("/alipay")
	public IMoocJSONResult alipay(
			@ApiParam(name = "orderId", 
			  value = "待付款的订单Id", 
			  required = true,
			  example = "1810109AZ0B4XD40")
			@RequestParam String orderId) throws Exception {
		
		// 0. 获取前端传入的订单编号
//		System.out.println(orderId);
		
		// 1. 获取订单信息
		Orders order = orderService.getOrderInfo(orderId);
		
		// 2. 获取用户信息
		Users user 	= userService.queryUser(order.getUserId());
		 
//		float amount = 0.01f;
		float amount = Float.valueOf(AmountUtils.changeF2Y(String.valueOf(order.getAmount())));
		String tradeDate = DateUtil.getCurrentDateString(DateUtil.ISO_EXPANDED_DATE_FORMAT);
		String itemName = "666Miss购物平台: 于 " + tradeDate + " 发生交易，完成购买";
		String aliUserId = user.getAlipayUserId();
//		String notifyUrl = "http://leechenxiang.xicp.io:32838/alipayNotify";
		String notifyUrl = "https://www.imoocdsp.com/alipayNotify";
//		String notifyUrl = "http://www.imoocdsp.com/miss/alipayNotify";
		String tradeNo = getTradeNo(orderId, amount, itemName, aliUserId, notifyUrl);
		if (StringUtils.isNotBlank(tradeNo)) {
			return IMoocJSONResult.ok(tradeNo);
		}
		return IMoocJSONResult.errorMsg("");
	}
	
	/**
	 * @Description: 支付宝异步通知，拦截器需要排除
	 */
	@ApiIgnore
	@RequestMapping(value="/alipayNotify")  
	@ResponseBody
	public String alipayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("支付成功后的支付宝异步通知");
		
//		return "success";
		
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//			valueStr = java.net.URLDecoder.decode(valueStr, "UTF-8");
			params.put(name, valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, "utf-8", "RSA2"); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		
		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
		if(signVerified) {//验证成功
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			// 付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
			
			if(trade_status.equals("TRADE_FINISHED")){
				// 交易结束，不可退款
			} else if (trade_status.equals("TRADE_SUCCESS")){
				// 交易支付成功
				
//				ordersService.displayPayNotice(out_trade_no, trade_no, CurrencyUtils.getYuan2Fen(total_amount), PaymentMethodEnum.alipay.key);
				
				// 支付成功，修改订单状态
				orderService.changeToWaitAccept(out_trade_no);
			}
			
			System.out.println("********************** 支付成功(支付宝异步通知) - 时间: " + DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN) + " **********************");
    		System.out.println("* 订单号: " + out_trade_no);
    		System.out.println("* 支付宝交易号: " + trade_no);
    		System.out.println("* 实付金额: " + total_amount);
    		System.out.println("* 交易状态: " + trade_status);
    		System.out.println("*****************************************************************************************");
			
			return "success";
		}else {
			//验证失败
			System.out.println("验签失败");
			return "fail";
		}
	}
	
	// 服务端 请求支付宝 获得交易号
	private String getTradeNo(String merchantOrderId, float amount, String itemName, String aliUserId, String notify_url) throws Exception {
		//实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", 
				APPID, 
				PRIVATE_KEY, 
				"json", 
				"utf-8", 
				ALIPAY_PUBLIC_KEY, 
				"RSA2");
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.create.
		AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
		
		AlipayBizContent bizContent = new AlipayBizContent(merchantOrderId, amount, itemName, aliUserId);
		request.setNotifyUrl(notify_url);
		//SDK已经封装掉了公共参数，这里只需要传入业务参数
		request.setBizContent(JsonUtils.objectToJson(bizContent));
		try {
		    //使用的是execute
		    AlipayTradeCreateResponse response = alipayClient.execute(request);
		    if(response.isSuccess()){
		    	String tradeNo = response.getTradeNo();			// 获取返回的tradeNO
		    	System.out.println("支付宝交易号 tradeNO 获取成功：" + tradeNo);
				return tradeNo;
			}
		} catch (AlipayApiException e) {
		    e.printStackTrace();
		}
		return "";
	}
		
}
