package com.imooc.service;

import com.imooc.pojo.Users;

public interface UserService {

	/**
	 * @Description: 查询用户是否存在
	 */
	public Users queryUserIsExist(String alipayUserId);
	
	/**
	 * @Description: 查询用户
	 */
	public Users queryUser(String userId);

	/**
	 * @Description: 创建新用户
	 */
	public void createUser(Users newUser);
	
}
