package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.model.domain.Color;

@Repository
public class MybatisColorDAO implements ColorDAO{
	 
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectById(int product_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color select(int color_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Color color) {
		sqlSessionTemplate.insert("Color.insert",color);
		
	}

	@Override
	public void update(Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int color_id) {
		// TODO Auto-generated method stub
		
	}

}
