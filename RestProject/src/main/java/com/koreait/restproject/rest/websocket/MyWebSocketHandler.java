package com.koreait.restproject.rest.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyWebSocketHandler extends TextWebSocketHandler{
	 private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

	//Ŭ���̾�Ʈ�� �����ϸ� ...
	//session = ������ ���
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		log.debug("Ŭ���̾�Ʈ ������, Ŭ���̾�Ʈ ���� ���̵��{} ",session.getId());
		
		sessionList.add(session);	//������ �߰�!!
		
	}
	
	//���� �����ϸ�...
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		log.debug("{}�� ���� ����",session.getId());
	}
	
	//��ε� �ɽ���
	public void broadCast(String data){
		try {
		//��� ������ �ڸ� ������� �޽��� ����
		for(WebSocketSession session : sessionList) {
			session.sendMessage(new TextMessage(data));
			}
		
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	
}
