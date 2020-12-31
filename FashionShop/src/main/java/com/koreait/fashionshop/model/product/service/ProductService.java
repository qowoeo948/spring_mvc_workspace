package com.koreait.fashionshop.model.product.service;

import java.util.List;

import com.koreait.fashionshop.model.domain.Product;

public interface ProductService {
	public List selectAll();
	public List selectById(int subcategory_id); //���� ī�װ��� �Ҽӵ� ��� ��ǰ
	public Product select(int product_id);
	public void regist(Product product);	//insert�Ӹ��ƴ϶� ���� ���ε� � �ؾ��ϱ� ������ insert->regist�� �̸� �ٲٱ�
	public void update(Product product);
	public void delete(int product_id);
}
