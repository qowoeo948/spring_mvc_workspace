package com.koreait.fashionshop.model.product.service;

import java.util.List;

import com.koreait.fashionshop.model.domain.SubCategory;

public interface SubCategoryService {
	//CRUD
	public List selectAll();//��� ���ڵ� ��������
	public List selectAllById(int topcategory_id); //������ ���� ī�װ��� �Ҽӵ� ����ī�װ� ��� ��������
	public SubCategory select(int topcategory_id);
	public void insert(SubCategory topCategory);
	public void update(SubCategory topCategory);
	public void delete(int topcategory_id);
	
}
