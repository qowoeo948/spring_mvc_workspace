package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import com.koreait.fashionshop.model.domain.Color;

public interface ColorDAO {
	public List selectAll();//모든 목록 가져오기 
	public List selectById(int product_id); //fk에 소속된 목록 가져오기
	public Color select(int color_id);
	public void insert(Color color);
	public void update(Color color);
	public void delete(int color_id);
}
