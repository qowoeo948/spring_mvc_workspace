package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import com.koreait.fashionshop.model.domain.SubCategory;

public interface SubCategoryDAO {
	//CRUD
	public List selectAll();//모든 레코드 가져오기 
	public List selectAllById(int topcategory_id);//선택한 상위 카테고리에 소속된 하위카테고리 목록가져오기	
	public SubCategory select(int topcategory_id);
	public void insert(SubCategory topcategory);
	public void update(SubCategory topcategory);
	public void delete(int topcategory_id);

}
