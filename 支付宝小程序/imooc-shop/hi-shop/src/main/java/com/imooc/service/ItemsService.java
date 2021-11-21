package com.imooc.service;

import java.util.List;

import com.imooc.pojo.Items;

public interface ItemsService {

	/**
	 * @Description: 查询新品商品列表
	 */
	public List<Items> queryNewItems();
	
	/**
	 * @Description: 查询推荐商品列表
	 */
	public List<Items> queryRecommendItems();
	
	/**
	 * @Description: 查询推荐商品列表
	 */
	public List<Items> queryItemsByName(String itemName);

	
	/**
	 * @Description: 查询商品详情
	 */
	public Items queryItemsById(String itemId);
	
	/**
	 * @Description: 查询某一分类下的所有商品
	 */
	public List<Items> queryItemsByCatId(String catId);
	
	/**
	 * @Description: 用户收藏商品
	 */
	public void userLikeItem(String itemId, String userId);
	
	/**
	 * @Description: 用户取消收藏商品
	 */
	public void userUnLikeItem(String itemId, String userId);
	
	/**
	 * @Description: 用户是否收藏商品
	 */
	public Integer userIsLikeItem(String itemId, String userId);
	
	/**
	 * @Description: 查询某一分类下的所有商品
	 */
	public List<Items> queryItemsByIds(String itemIds);

}
