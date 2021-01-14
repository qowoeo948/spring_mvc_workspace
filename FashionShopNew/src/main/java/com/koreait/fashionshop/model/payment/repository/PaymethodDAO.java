package com.koreait.fashionshop.model.payment.repository;

import java.util.List;

import com.koreait.fashionshop.model.domain.OrderDetail;
import com.koreait.fashionshop.model.domain.OrderSummary;

public interface PaymethodDAO {
	public List selectAll();
}
