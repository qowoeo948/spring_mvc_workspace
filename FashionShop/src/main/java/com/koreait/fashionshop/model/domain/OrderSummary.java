package com.koreait.fashionshop.model.domain;

import lombok.Data;

@Data
public class OrderSummary {
	private int order_summary_id;
	private int member_id;
	private int total_price;
	private int total_pay;
	private String orderdate;
	private int order_state_id;
	private int paymethod_id;
}
