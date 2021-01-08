package com.koreait.fashionshop.model.payment.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisPaymethodDAO implements PaymethodDAO{
	@Autowired
	public SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("Paymethod.selectAll");
	}

	@Override
	public void registOrder() {
		// TODO Auto-generated method stub
		
	}

}
