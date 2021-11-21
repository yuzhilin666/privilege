package com.imooc.service;

import java.util.List;

import com.imooc.pojo.Category;

public interface CategoryService {

	/**
	 * @Description: 查询现有的所有分类
	 */
	public List<Category> queryCats();
	
}
