package com.imooc.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.pojo.Items;
import com.imooc.pojo.vo.ItemsVO;
import com.imooc.service.CategoryService;
import com.imooc.service.ItemsService;
import com.imooc.utils.IMoocJSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController("search")
@Api(value="搜索分类", tags={" 搜索/分类 接口"})
public class SearchController extends BasicController {

	/**
	 * TODO:
	 * 所有分类 列表 接口						【done】
	 * 根据商品名模糊搜索 接口					【done】
	 * 根据商品分类id查询商品列表 接口			【done】
	 * 点击轮播图查询 接口
	 * 		根据商品类型查询商品列表（同上）	【done】
	 * 		直接查询某个商品（根据商品id）		【done】
	 * 点击某个产品，查询产品接口（同上）		【done】
	 */
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ItemsService itemsService;
	
	@ApiOperation(value="所有分类列表", notes="用于在分类页面显示所有分分类列表，以json数组返回", httpMethod = "POST")
	@PostMapping("/cats")
	public IMoocJSONResult carousels() {
		return IMoocJSONResult.ok(categoryService.queryCats());
	}
	
	@ApiOperation(value="用户搜索商品", notes="分类页面用户模糊查询搜索的商品，若输入为空则显示商品推荐列表", httpMethod = "POST")
	@PostMapping("/items/search")
	public IMoocJSONResult searchItemsByName(
			@ApiParam(name = "itemName", 
					  value = "用户输入搜索的内容", 
					  required = false,
					  example = "衣服")
			 @RequestParam String itemName) {
		
		// TODO 录课时间不够，这里就做分页功能
		
		if (StringUtils.isBlank(itemName)) {
			// 如果商品名为空，则返回推荐商品列表
			List<Items> itemsList = itemsService.queryRecommendItems();
			List<ItemsVO> newList = itemsConverter(itemsList);
			return IMoocJSONResult.ok(newList);
		} else {
			// 如果商品名为空，则直接查询
			List<Items> itemsList = itemsService.queryItemsByName(itemName);
			List<ItemsVO> newList = itemsConverter(itemsList);
			return IMoocJSONResult.ok(newList);
		} 
	}
	
	@ApiOperation(value="查询指定商品", notes="根据具体的商品id查询商品详细内容", httpMethod = "POST")
	@PostMapping("/items/searchById")
	public IMoocJSONResult searchById(
			@ApiParam(name = "itemId", 
					  value = "商品ID - 主键", 
					  required = false,
					  example = "1001011")
			 @RequestParam String itemId) {
		
		if (StringUtils.isNotBlank(itemId)) {
			Items item = itemsService.queryItemsById(itemId);
			
			// 转换
			ItemsVO targetItem = convertorItem(item);
			
			return IMoocJSONResult.ok(targetItem);
		} 
		
		return IMoocJSONResult.errorMsg("");
	}
	
	@ApiOperation(value="查询特定类别商品列表", notes="根据具体的商品分类id查询该类别下的商品列表", httpMethod = "POST")
	@PostMapping("/items/searchByCat")
	public IMoocJSONResult searchByCat(
			@ApiParam(name = "catId", 
					  value = "商品分类的id", 
					  required = false,
					  example = "1")
			 @RequestParam String catId) {
		
		if (StringUtils.isNotBlank(catId)) {
			List<Items> itemsList = itemsService.queryItemsByCatId(catId);
			List<ItemsVO> newList = itemsConverter(itemsList);
			return IMoocJSONResult.ok(newList);
		} 
		
		return IMoocJSONResult.errorMsg("");
	}
}
