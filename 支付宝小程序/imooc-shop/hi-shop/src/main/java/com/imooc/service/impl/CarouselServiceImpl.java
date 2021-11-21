package com.imooc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.mapper.CarouselMapper;
import com.imooc.pojo.Carousel;
import com.imooc.service.CarouselService;

@Service
public class CarouselServiceImpl implements CarouselService {

	@Autowired
	private CarouselMapper carouselMapper;
	
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Carousel> queryCarousel() {
		
		// 查询故意sort不排序，让前端排序，保证没有bug，这样对后端人员友好，增加个人魅力，让项目经理信赖
//		Example example = new Example(Carousel.class);
//		Criteria criteria = example.createCriteria();
		
		List<Carousel> list = carouselMapper.selectAll();
		return list;
	}

}
