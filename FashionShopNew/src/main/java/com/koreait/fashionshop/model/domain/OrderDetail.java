package com.koreait.fashionshop.model.domain;

import lombok.Data;

@Data
public class OrderDetail {
	private int order_detail_id;	
	private int order_summary_id;	
	private int product_id;	
	private int price;	
	private int quantity;	
	private String color;

}
