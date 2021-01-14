package com.koreait.fashionshop.model.product.service;

import java.util.List;

import com.koreait.fashionshop.model.domain.TopCategory;
//모든 하위 객체가 반드시 구현해야할 공통 및 필수적인 기능을 정의..
public interface TopCategoryService {
	public List selectAll();
	public TopCategory select(int topcategory_id);
	public void insert(TopCategory topcategory);
	public void update(TopCategory topcategory);
	public void delete(int topcategory_id);
	
}
