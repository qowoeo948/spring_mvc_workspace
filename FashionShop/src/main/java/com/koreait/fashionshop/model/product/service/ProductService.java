package com.koreait.fashionshop.model.product.service;

import java.util.List;

import com.koreait.fashionshop.model.domain.Product;

public interface ProductService {
	public List selectAll();
	public List selectById(int subcategory_id); //하위 카테고리에 소속된 모든 상품
	public Product select(int product_id);
	public void regist(Product product);	//insert뿐만아니라 파일 업로드 등도 해야하기 떄문에 insert->regist로 이름 바꾸기
	public void update(Product product);
	public void delete(int product_id);
}
