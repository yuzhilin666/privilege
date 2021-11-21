package com.imooc.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.pojo.Address;
import com.imooc.pojo.bo.AddressBO;
import com.imooc.service.AddressService;
import com.imooc.utils.IMoocJSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value="收货地址", tags={" 收货地址 相关接口"})
@RequestMapping("address")
public class AddressController extends BasicController {

	/**
	 * 查询用户的收货地址列表		【done】
	 * 创建收货地址				【done】
	 * 修改收货地址				【done】
	 * 删除收货地址				【done】
	 * 设置默认收货地址			【done】
	 */
	
	@Autowired
	private AddressService addressService;
	
	@ApiOperation(value="创建/修改收货地址", notes="用户创建/修改收货地址的接口", httpMethod = "POST")
	@PostMapping("/createOrUpdate")
	public IMoocJSONResult createOrUpdate(
			@ApiParam(name = "addressBO", 
			  value = "用户收货地址包装类", 
			  required = true,
			  example = "addressBO")
			 AddressBO addressBO,
			@ApiParam(name = "addressId", 
			  value = "修改的地址id", 
			  required = false,
			  example = "addressId")
			@RequestParam String addressId) {
		
		if (addressBO == null) {
			return IMoocJSONResult.errorMsg(null);
		} 
		
		if (StringUtils.isBlank(addressBO.getCity()) ||
				StringUtils.isBlank(addressBO.getDescAddress()) ||
//				StringUtils.isBlank(addressBO.getDistrict()) ||
				StringUtils.isBlank(addressBO.getMobile()) ||
//				StringUtils.isBlank(addressBO.getProvince()) ||
				StringUtils.isBlank(addressBO.getReceiver()) ||
				StringUtils.isBlank(addressBO.getUserId()) ) {
			return IMoocJSONResult.errorMsg("部分参数不能为空...");
		}
		
		Address address = new Address();
		if ( StringUtils.isBlank(addressId) ) {
			address = addressService.createAddress(addressBO);
		} else {
			address = addressService.updateAddress(addressBO, addressId);
		}
				
		return IMoocJSONResult.ok(address);
	}
	
//	@ApiOperation(value="修改收货地址", notes="用户修改收货地址的接口", httpMethod = "POST")
//	@PostMapping("/update/{addressId}")
//	public IMoocJSONResult update(
//			@ApiParam(name = "addressBO", 
//			  value = "用户收货地址包装类", 
//			  required = true,
//			  example = "addressBO")
//			@RequestBody AddressBO addressBO,
//			@ApiParam(name = "addressId", 
//			  value = "要修改的收货地址id", 
//			  required = true,
//			  example = "1001")
//			@PathVariable String addressId) {
//		
//		if (addressBO == null || StringUtils.isBlank(addressId)) {
//			return IMoocJSONResult.errorMsg(null);
//		} 
//		
//		if (StringUtils.isBlank(addressBO.getCity()) ||
//				StringUtils.isBlank(addressBO.getDescAddress()) ||
////				StringUtils.isBlank(addressBO.getDistrict()) ||
//				StringUtils.isBlank(addressBO.getMobile()) ||
//				StringUtils.isBlank(addressBO.getProvince()) ||
//				StringUtils.isBlank(addressBO.getReceiver()) ||
//				StringUtils.isBlank(addressBO.getUserId()) ) {
//			return IMoocJSONResult.errorMsg("部分参数不能为空...");
//		}
//		
//		addressService.updateAddress(addressBO, addressId);
//		
//		return IMoocJSONResult.ok();
//	}
	
	@ApiOperation(value="查询用户收货地址列表", notes="查询用户收货地址列表", httpMethod = "POST")
	@PostMapping("/addressList/{userId}")
	public IMoocJSONResult addressList(
			@ApiParam(name = "userId", 
					  value = "传入用户的id", 
					  required = true,
					  example = "1001")
			 @PathVariable String userId) {

		if (StringUtils.isBlank(userId)) {
			return IMoocJSONResult.errorMsg("用户id不能为空...");
		}
		
		List<Address> addressList = addressService.queryAdressOfUser(userId);
		
		return IMoocJSONResult.ok(addressList);
	}
	
	@ApiOperation(value="查询用户默认收货地址", notes="查询用户默认的收货地址，如果没有默认，则返回第一个地址，如果啥都没，返回空", httpMethod = "POST")
	@PostMapping("/default/{userId}")
	public IMoocJSONResult defaultAddress(
			@ApiParam(name = "userId", 
					  value = "传入用户的id", 
					  required = true,
					  example = "1001")
			 @PathVariable String userId) {

		if (StringUtils.isBlank(userId)) {
			return IMoocJSONResult.errorMsg("用户id不能为空...");
		}
		
		Address address = addressService.queryAdressOfUserDefault(userId);
		return IMoocJSONResult.ok(address);
	}
	
	@ApiOperation(value="查询地址", notes="根据地址id以及用户id查询某个地址详情", httpMethod = "POST")
	@PostMapping("/fetch")
	public IMoocJSONResult fetch(
			@ApiParam(name = "addressId", 
					  value = "传入地址的id", 
					  required = true,
					  example = "1001")
			@RequestParam String addressId,
			@ApiParam(name = "userId", 
			  value = "传入用户的id", 
			  required = true,
			  example = "1001")
			@RequestParam String userId) {

		if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)) {
			return IMoocJSONResult.errorMsg("参数不能为空...");
		}
		
		Address address = addressService.queryAdressInfo(userId, addressId);
		return IMoocJSONResult.ok(address);
	}
	
	@ApiOperation(value="删除用户收货地址", notes="用户删除某个收货地址", httpMethod = "POST")
	@PostMapping("/delete/{addressId}")
	public IMoocJSONResult delete(
			@ApiParam(name = "addressId", 
			  value = "要删除的收货地址id", 
			  required = true,
			  example = "1001")
			@PathVariable String addressId) {

		if (StringUtils.isBlank(addressId)) {
			return IMoocJSONResult.errorMsg("收货地址id不能为空...");
		}
		
		addressService.delete(addressId);
		
		return IMoocJSONResult.ok();
	}
	
	@ApiOperation(value="设置默认的用户收货地址", notes="用户设置某个收货地址为默认", httpMethod = "POST")
	@PostMapping("/setDefault")
	public IMoocJSONResult setDefault(
			@ApiParam(name = "userId", 
			  value = "传入用户的id", 
			  required = true,
			  example = "10011")
			@RequestParam String userId,
			@ApiParam(name = "addressId", 
			  value = "要设置的收货地址id", 
			  required = true,
			  example = "10012")
			@RequestParam String addressId) {

		if (StringUtils.isBlank(addressId)) {
			return IMoocJSONResult.errorMsg("收货地址id不能为空...");
		}
		
		if (StringUtils.isBlank(userId)) {
			return IMoocJSONResult.errorMsg("用户id不能为空...");
		}
		
		addressService.setDefault(userId, addressId);
		
		return IMoocJSONResult.ok();
	}
}
