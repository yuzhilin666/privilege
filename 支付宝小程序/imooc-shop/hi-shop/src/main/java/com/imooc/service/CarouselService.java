package com.imooc.service;

import java.util.List;

import com.imooc.pojo.Carousel;

public interface CarouselService {

	/**
	 * @Description: 查询首页轮播图列表
	 */
	public List<Carousel> queryCarousel();
	
}
