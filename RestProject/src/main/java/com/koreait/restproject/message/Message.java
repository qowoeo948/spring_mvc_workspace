package com.koreait.restproject.message;

import lombok.Data;

@Data
public class Message {
	private String msg; //Ŭ���̾�Ʈ�� �ް� �� ���� �޼���
	private int statusCode;  //http�����ڵ� 200,404,500 ���
}
