package com.koreait.fashionshop.common;

import lombok.Data;

@Data
public class MessageData {
	private int resultCode;		//����, ���� ���� �Ǵ� �ڵ�
	private String msg;	//Ŭ���̾�Ʈ�� ���� �� �޼���
	private String url;			//���Ӱ� ��û �� �������� �ִٸ� �� url

}
