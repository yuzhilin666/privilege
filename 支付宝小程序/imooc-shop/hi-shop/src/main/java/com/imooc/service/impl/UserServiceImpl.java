package com.imooc.service.impl;

import java.util.List;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private Sid sid;

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Users queryUserIsExist(String alipayUserId) {
		Example example = new Example(Users.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("alipayUserId", alipayUserId);
		
		List<Users> userList = usersMapper.selectByExample(example);
		if (userList != null && !userList.isEmpty() && userList.size() > 0) {
			return userList.get(0);	
		}
		
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void createUser(Users newUser) {
		String userId = sid.nextShort();
		
		newUser.setId(userId);
		usersMapper.insert(newUser);
	}

	@Override
	public Users queryUser(String userId) {
		return usersMapper.selectByPrimaryKey(userId);
	}
	
}
