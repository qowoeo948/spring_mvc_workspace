package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.model.domain.Image;

@Repository
public class MybatisImageDAO implements ImageDAO{
	
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
	public Image select(int image_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Image image) {
		sqlSessionTemplate.insert("Image.insert",image);
		
	}

	@Override
	public void update(Image image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int image_id) {
		// TODO Auto-generated method stub
		
	}

}
