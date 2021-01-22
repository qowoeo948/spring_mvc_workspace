package com.koreait.restproject.message;

import lombok.Data;

@Data
public class Message {
	private String msg; //클라이언트가 받게 될 에러 메세지
	private int statusCode;  //http상태코드 200,404,500 등등
}
