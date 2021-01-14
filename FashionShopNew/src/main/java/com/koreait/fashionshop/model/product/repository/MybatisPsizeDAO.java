package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.exception.ProductRegistException;
import com.koreait.fashionshop.model.domain.Psize;

@Repository
public class MybatisPsizeDAO implements PsizeDAO{
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
	public Psize select(int psize_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Psize psize)  throws ProductRegistException{
		int result = sqlSessionTemplate.insert("Psize.insert", psize);
		if(result==0) {
			throw new ProductRegistException("사이즈 등록에 실패하였습니다");
		}
		
	}

	@Override
	public void update(Psize psize)  throws ProductRegistException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int psize_id)  throws ProductRegistException{
		// TODO Auto-generated method stub
		
	}
	
}
