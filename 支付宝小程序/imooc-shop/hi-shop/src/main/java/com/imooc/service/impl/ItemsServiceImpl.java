package com.imooc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.mapper.ItemsMapper;
import com.imooc.mapper.UserLikeItemMapper;
import com.imooc.pojo.Items;
import com.imooc.pojo.UserLikeItem;
import com.imooc.service.ItemsService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private ItemsMapper itemsMapper;
	
	@Autowired
	private UserLikeItemMapper userLikeItemMapper;
	
	@Autowired
	private Sid sid;
	
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Items> queryNewItems() {
		
		Example example = new Example(Items.class);
		example.orderBy("createTime").desc();
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("isNew", 1);
		criteria.andEqualTo("isOn", 1);
		
		List<Items> list = itemsMapper.selectByExample(example);
		return list;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Items> queryRecommendItems() {
		
		Example example = new Example(Items.class);
		example.orderBy("createTime").desc();
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("isRecommend", 1);
		criteria.andEqualTo("isOn", 1);
		
		List<Items> list = itemsMapper.selectByExample(example);
		return list;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Items> queryItemsByName(String itemName) {
		Example example = new Example(Items.class);
		example.orderBy("createTime").desc();
		Criteria criteria = example.createCriteria();
		criteria.andLike("name", "%" + itemName + "%");
		criteria.andEqualTo("canBeSearched", 1);
		
		List<Items> list = itemsMapper.selectByExample(example);
		return list;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Items queryItemsById(String itemId) {
		return itemsMapper.selectByPrimaryKey(itemId);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Items> queryItemsByCatId(String catId) {
		Example example = new Example(Items.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("catId", catId);
		criteria.andEqualTo("isOn", 1);
		
		List<Items> list = itemsMapper.selectByExample(example);
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void userLikeItem(String itemId, String userId) {
		
		// 收藏
		String uliId = sid.nextShort();
		UserLikeItem uli = new UserLikeItem();
		uli.setId(uliId);
		uli.setItemId(itemId);
		uli.setUserId(userId);
		userLikeItemMapper.insert(uli);
		
		// 收藏数量累加
		Items item = itemsMapper.selectByPrimaryKey(itemId);
		Integer likeCounts = item.getLikeCounts() + 1;
		item.setLikeCounts(likeCounts);
		itemsMapper.updateByPrimaryKeySelective(item);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void userUnLikeItem(String itemId, String userId) {
		
		// 取消收藏
		Example example = new Example(UserLikeItem.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("itemId", itemId);
		criteria.andEqualTo("userId", userId);
		userLikeItemMapper.deleteByExample(example);
		
		// 收藏数量累减
		Items item = itemsMapper.selectByPrimaryKey(itemId);
		Integer likeCounts = item.getLikeCounts() - 1;
		item.setLikeCounts(likeCounts);
		itemsMapper.updateByPrimaryKeySelective(item);
	}

	@Override
	public Integer userIsLikeItem(String itemId, String userId) {
		Example example = new Example(UserLikeItem.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("itemId", itemId);
		criteria.andEqualTo("userId", userId);
		List<UserLikeItem> list = userLikeItemMapper.selectByExample(example);
		if (list != null && !list.isEmpty() && list.size() > 0) {
			return 1;
		}
		return 0;
	}

	@Override
	public List<Items> queryItemsByIds(String itemIds) {
		
		List<String> idsList = new ArrayList<>();
		
		String ids[] = itemIds.split(",");
		for (String sid : ids) {
			if (StringUtils.isNotBlank(sid)) {
				idsList.add(sid);
			}
		}
		
		Example example = new Example(Items.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn("id", idsList);
		
		List<Items> list = itemsMapper.selectByExample(example);
		
		return list;
	}

}
