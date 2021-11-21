package com.imooc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.pojo.Items;
import com.imooc.pojo.vo.ItemsVO;
import com.imooc.service.ItemsService;
import com.imooc.utils.IMoocJSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController("item")
@Api(value="商品收藏", tags={" 商品相关 接口"})
@RequestMapping("/item")
public class ItemsController extends BasicController {

	/**
	 * TODO:
	 * 喜欢商品
	 * 不喜欢商品
	 * 查询用户是否喜欢
	 */
	
	@Autowired
	private ItemsService itemsService;
	
	@ApiOperation(value="用户收藏商品", notes="用户在商品页面点击收藏后，该商品被用户收藏", httpMethod = "POST")
	@PostMapping("/like")
	public IMoocJSONResult likeItem(
			 @ApiParam(name = "itemId", value = "商品id", required = true)
			 @RequestParam String itemId, 
			 @ApiParam(name = "userId", value = "用户id", required = true)
			 @RequestParam String userId) {
		
		try {
			itemsService.userLikeItem(itemId, userId);
		} catch (Exception e) {
			e.printStackTrace();
			return IMoocJSONResult.errorMsg("");
		}
		return IMoocJSONResult.ok();
	}
	
	@ApiOperation(value="用户取消收藏商品", notes="用户在商品页面点击取消收藏后，该商品被用户取消收藏", httpMethod = "POST")
	@PostMapping("/unlike")
	public IMoocJSONResult unlikeItem(
			 @ApiParam(name = "itemId", value = "商品id", required = true)
			 @RequestParam String itemId, 
			 @ApiParam(name = "userId", value = "用户id", required = true)
			 @RequestParam String userId) {
		
		try {
			itemsService.userUnLikeItem(itemId, userId);
		} catch (Exception e) {
			e.printStackTrace();
			return IMoocJSONResult.errorMsg("");
		}
		return IMoocJSONResult.ok();
	}
	
	@ApiOperation(value="查询用户是否收藏商品", notes="用户在进入商品详情页时，需要判断用户是否收藏了这个商品", httpMethod = "POST")
	@PostMapping("/userIsLikeItem")
	public IMoocJSONResult userIsLikeItem(
			 @ApiParam(name = "itemId", value = "商品id", required = true)
			 @RequestParam String itemId, 
			 @ApiParam(name = "userId", value = "用户id", required = true)
			 @RequestParam String userId) {
		
		return IMoocJSONResult.ok(itemsService.userIsLikeItem(itemId, userId));
	}
	
	@ApiOperation(value="查询商品列表", notes="根据传入的商品ids，查询商品列表", httpMethod = "POST")
	@PostMapping("/queryItems")
	public IMoocJSONResult queryItems(
			 @ApiParam(name = "itemIds", value = "商品id，逗号间隔", required = true)
			 @RequestParam String itemIds) {
		
		List<Items> itemsList = itemsService.queryItemsByIds(itemIds);
		List<ItemsVO> newList = itemsConverter(itemsList);
		return IMoocJSONResult.ok(newList);
	}
}
