package com.imooc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.pojo.Items;
import com.imooc.pojo.vo.ItemsVO;
import com.imooc.service.CarouselService;
import com.imooc.service.ItemsService;
import com.imooc.utils.IMoocJSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value="首页", tags={" 首页相关接口"})
public class IndexController extends BasicController {

	/**
	 * 轮播图列表 接口			【done】
	 * 横向推荐商品列表 接口		【done】		
	 * 竖向新品列表 接口 			【done】
	 */
	
	@Autowired
	private CarouselService carouselService;
	
	@Autowired
	private ItemsService itemsService;
	
	@ApiOperation(value="轮播图列表", notes="用于查询首页的轮播图列表，以json数组返回", httpMethod = "POST")
//	@ApiResponses({ 
//		@ApiResponse(code = 200, message = "OK - 操作成功"),
//		@ApiResponse(code = 555, message = "接口调用异常，请联系管理员")
//	})
//	@GetMapping("/carousels")
	@PostMapping("/index/carousels")
	public IMoocJSONResult carousels() {
		return IMoocJSONResult.ok(carouselService.queryCarousel());
	}
	
	
	@ApiOperation(value="推荐商品/新品列表查询接口", notes="用于查询首页的推荐商品列表或新品列表", httpMethod = "POST")
	@PostMapping("/index/items/{listType}")
	public IMoocJSONResult items(
			@ApiParam(name = "listType", 
					  value = "查询商品列表的类型 ‘rec: 推荐商品’, ‘new: 新品’", 
					  required = true,
					  example = "rec")
			 @PathVariable String listType) {
		
		if (listType.equals("rec")) {
			List<Items> itemsList = itemsService.queryRecommendItems();
			List<ItemsVO> newList = itemsConverter(itemsList);
			return IMoocJSONResult.ok(newList);
		} else if (listType.equals("new")) {
			List<Items> itemsList = itemsService.queryNewItems();
			List<ItemsVO> newList = itemsConverter(itemsList);
			return IMoocJSONResult.ok(newList);
		} 
		
		return IMoocJSONResult.ok();
	}
	
	/**
	 * @Description: 转换商品list中headerImages，封面数组，tags
	 */
	public List<ItemsVO> itemsConverter(List<Items> itemsList) {
		
		List<ItemsVO> newList = new ArrayList<>();
		for (Items i : itemsList) {
//			ItemsVO targetItem = new ItemsVO();
//			BeanUtils.copyProperties(i, targetItem);
//			
//			String headerImages = i.getHeaderImages();
//			List<String> covers = JsonUtils.jsonToList(headerImages, String.class);
//			targetItem.setCover(covers.get(0));
//			
//			targetItem.setCoverList(covers);
//			
//			String tags = i.getTags();
//			String tagArr[] = tags.split(",");
//			List<String> tagList = new ArrayList<>();
//			for (String t : tagArr) {
//				if (StringUtils.isNotBlank(t)) {
//					tagList.add(t);
//				}
//			}
//			targetItem.setTagList(tagList);
//			
//			// 金额转换，分to元
//			try {
//				targetItem.setPriceDiscountYuan(AmountUtils.changeF2Y(String.valueOf(i.getPriceDiscount())));
//				targetItem.setPriceNormalYuan(AmountUtils.changeF2Y(String.valueOf(i.getPriceNormal())));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			
			ItemsVO targetItem = convertorItem(i);
			
			newList.add(targetItem);
		}
		return newList;
	}
	
}
