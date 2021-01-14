package com.koreait.fashionshop.model.domain;

import lombok.Data;

@Data
public class Cart extends Product{
	private int cart_id;
	private int quantity;
	private int member_id;
	
}
