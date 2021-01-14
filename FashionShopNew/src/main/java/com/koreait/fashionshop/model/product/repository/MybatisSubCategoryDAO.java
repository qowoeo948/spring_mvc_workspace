package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.model.domain.SubCategory;

@Repository
public class MybatisSubCategoryDAO implements SubCategoryDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return null;
	}

	@Override
	public List selectAllById(int topcategory_id) {
		return sqlSessionTemplate.selectList("SubCategory.selectAllById", topcategory_id);
	}

	@Override
	public SubCategory select(int topcategory_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(SubCategory topcategory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SubCategory topcategory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int topcategory_id) {
		// TODO Auto-generated method stub
		
	}
	
}
