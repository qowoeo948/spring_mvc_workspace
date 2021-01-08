package com.koreait.fashionshop.model.domain;

import lombok.Data;

@Data
public class Receiver {
	private int receiver_id;
	private int order_summary_id;
	private String receiver_name;
	private String receiver_addr;
	private String receiver_phone;
}
