package com.imooc.service.impl;

import java.util.Date;
import java.util.List;

import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.mapper.AddressMapper;
import com.imooc.mapper.CategoryMapper;
import com.imooc.pojo.Address;
import com.imooc.pojo.Category;
import com.imooc.pojo.Items;
import com.imooc.pojo.bo.AddressBO;
import com.imooc.service.AddressService;
import com.imooc.service.CategoryService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired
	private Sid sid;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Address createAddress(AddressBO addressBO) {
		
		String addressId = sid.nextShort();
		
		Address address = new Address();
		BeanUtils.copyProperties(addressBO, address);
		address.setId(addressId);
		address.setCreateTime(new Date());
		
		// 查询用户是否已经有收货地址列表，如果没有，当前这个设置为默认
		List<Address> userAdressList = queryAdressOfUser(addressBO.getUserId());
		if (userAdressList == null || userAdressList.isEmpty() || userAdressList.size() == 0) {
			address.setIsDefault(1);			
		} else {
			address.setIsDefault(0);
		}
		addressMapper.insert(address);
		
		return address;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Address updateAddress(AddressBO addressBO, String addressId) {
		
		Address address = new Address();
		BeanUtils.copyProperties(addressBO, address);
		address.setId(addressId);
		addressMapper.updateByPrimaryKeySelective(address);
		
		return address;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Address> queryAdressOfUser(String userId) {
		
		Example example = new Example(Address.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userId", userId);
		
		List<Address> list = addressMapper.selectByExample(example);
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Address queryAdressOfUserDefault(String userId) {
		
		Example example = new Example(Address.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userId", userId);
		
		List<Address> list = addressMapper.selectByExample(example);
		if (list == null || list.isEmpty() || list.size() <= 0) {
			return null;
		}
		// 获取默认地址，如果没有，返回第一个
		for (Address a : list) {
			Integer isDefault = a.getIsDefault();
			if (isDefault == 1) {
				return a;
			}
			
		}
		return list.get(0);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(String addressId) {

		addressMapper.deleteByPrimaryKey(addressId);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void setDefault(String userId, String addressId) {
		
		// 把默认的设置为非默认
		Example example = new Example(Address.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userId", userId);
//		criteria.andEqualTo("id", addressId);
		criteria.andEqualTo("isDefault", 1);
		
		Address address = new Address();
//		address.setId(addressId);
//		address.setUserId(userId);
		address.setIsDefault(0);
		
		addressMapper.updateByExampleSelective(address, example);
		
//		List<Address> defaultList = addressMapper.selectByExample(example);
//		for (Address a : defaultList) {
//			Address address = new Address();
//			address.setId(addressId);
//			address.setIsDefault(0);
//			addressMapper.updateByPrimaryKey(address);
//		}
		
		
		// 设置默认
		Example doExample = new Example(Address.class);
		Criteria doCriteria = doExample.createCriteria();
		doCriteria.andEqualTo("userId", userId);
		doCriteria.andEqualTo("id", addressId);
		
		Address doAddress = new Address();
//		doAddress.setId(addressId);
//		doAddress.setUserId(userId);
		doAddress.setIsDefault(1);
		
		addressMapper.updateByExampleSelective(doAddress, doExample);
	}

	@Override
	public Address queryAdressInfo(String userId, String addressId) {
		
		Example example = new Example(Address.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userId", userId);
		criteria.andEqualTo("id", addressId);
		
		List<Address> list = addressMapper.selectByExample(example);
		if (list == null || list.isEmpty() || list.size() <= 0) {
			return null;
		}
		return list.get(0);
	}

}
