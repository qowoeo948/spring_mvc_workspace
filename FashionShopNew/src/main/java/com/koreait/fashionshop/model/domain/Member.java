package com.koreait.fashionshop.model.domain;

import lombok.Data;

@Data
public class Member {
	private int member_id;
	private String user_id;
	private String name;
	private String password;
	private String email_id;
	private String email_server;
	private String zipcode;
	private String addr;
	private String regdate;
}
