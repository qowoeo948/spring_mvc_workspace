package com.koreait.fashionshop.model.payment.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.exception.OrderSummaryRegistException;
import com.koreait.fashionshop.model.domain.OrderSummary;

@Repository
public class MybatisOrderSummaryDAO implements OrderSummaryDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void insert(OrderSummary orderSummary) throws OrderSummaryRegistException{
		int result=sqlSessionTemplate.insert("OrderSummary.insert", orderSummary);
		if(result==0) {
			throw new OrderSummaryRegistException("주문요약 등록실패");
		}
	}
	
}





