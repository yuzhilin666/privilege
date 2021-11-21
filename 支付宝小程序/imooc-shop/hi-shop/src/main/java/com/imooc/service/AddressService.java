package com.imooc.service;

import java.util.List;

import com.imooc.pojo.Address;
import com.imooc.pojo.bo.AddressBO;

public interface AddressService {

	/**
	 * @Description: 创建收货地址
	 */
	public Address createAddress(AddressBO addressBO);
	
	/**
	 * @Description: 修改收货地址
	 */
	public Address updateAddress(AddressBO addressBO, String addressId);
	
	/**
	 * @Description: 查询用户的所有收货地址列表
	 */
	public List<Address> queryAdressOfUser(String userId);
	
	/**
	 * @Description: 删除用户收货地址
	 */
	public void delete(String addressId);
	
	/**
	 * @Description: 设置默认收货地址
	 */
	public void setDefault(String userId, String addressId);
	
	/**
	 * @Description: 获取用户默认地址
	 */
	public Address queryAdressOfUserDefault(String userId);
	
	/**
	 * @Description: 根据id查询用户的某个地址
	 */
	public Address queryAdressInfo(String userId, String addressId);
}
