package com.imooc.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.imooc.pojo.Items;
import com.imooc.pojo.vo.ItemsVO;
import com.imooc.utils.AmountUtils;
import com.imooc.utils.JsonUtils;

public class BasicController {

//	@Autowired
//	public RedisOperator redis;

	public static final String REDIS_USER_TOKEN = "redis-user-token";

	// http://192.168.1.190:88
	public static final String FACE_DEFAULT = "M00/00/00/wKgBvlsb8-eADknrAAGgp0gwh24666.JPG";

//	public static MultipartFile file2Multipart() throws Exception {
//		
//	}
	
	/**
	 * @Description: 转换商品list中headerImages，封面数组，tags
	 */
	public List<ItemsVO> itemsConverter(List<Items> itemsList) {
		
		List<ItemsVO> newList = new ArrayList<>();
		for (Items i : itemsList) {
			ItemsVO targetItem = convertorItem(i);
			
			newList.add(targetItem);
		}
		return newList;
	}
	
	public ItemsVO convertorItem(Items sourceItem) {
		if (sourceItem == null) {
			return null;
		}
		
		ItemsVO targetItem = new ItemsVO();
		BeanUtils.copyProperties(sourceItem, targetItem);
		
		String headerImages = sourceItem.getHeaderImages();
		List<String> covers = JsonUtils.jsonToList(headerImages, String.class);
		targetItem.setCover(covers.get(0));
		
		targetItem.setCoverList(covers);
		
		String tags = sourceItem.getTags();
		String tagArr[] = tags.split(",");
		List<String> tagList = new ArrayList<>();
		for (String t : tagArr) {
			if (StringUtils.isNotBlank(t)) {
				tagList.add(t);
			}
		}
		targetItem.setTagList(tagList);
		
		// 金额转换，分to元
		try {
			targetItem.setPriceDiscountYuan(AmountUtils.changeF2Y(String.valueOf(sourceItem.getPriceDiscount())));
			targetItem.setPriceNormalYuan(AmountUtils.changeF2Y(String.valueOf(sourceItem.getPriceNormal())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return targetItem;
	}
}
