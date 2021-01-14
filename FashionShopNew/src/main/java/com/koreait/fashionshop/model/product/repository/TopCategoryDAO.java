package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import com.koreait.fashionshop.model.domain.TopCategory;

public interface TopCategoryDAO {
	//CRUD
	public List selectAll();
	public TopCategory select(int topcategory_id);
	public void insert(TopCategory topcategory);
	public void update(TopCategory topcategory);
	public void delete(int topcategory_id);
	
}
