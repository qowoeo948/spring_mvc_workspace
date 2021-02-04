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

	//클라이언트가 접속하면 ...
	//session = 접속한 사람
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		log.debug("클라이언트 접속함, 클라이언트 세션 아이디는{} ",session.getId());
		
		sessionList.add(session);	//접속자 추가!!
		
	}
	
	//접속 해제하면...
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		log.debug("{}가 접속 해제",session.getId());
	}
	
	//브로드 케스팅
	public void broadCast(String data){
		try {
		//모든 접속한 자를 대상으로 메시지 전송
		for(WebSocketSession session : sessionList) {
			session.sendMessage(new TextMessage(data));
			}
		
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	
}
