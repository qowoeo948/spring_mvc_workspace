package com.koreait.restproject.rest.websocket;

import lombok.Data;

//����ȭ�� ������ ������ ������ �����͸� �ְ� ����!!
@Data
public class SocketMessage {
	private String requestCode;	//create,read,update,delete
	private int resultCode; //200,500, ��������(�츮�� ���ϱ� ����)
	private String msg; //������ �޼���
	private String data; //json�� ���� ����

}
