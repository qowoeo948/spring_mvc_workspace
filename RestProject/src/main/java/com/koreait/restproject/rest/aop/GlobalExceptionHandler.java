package com.koreait.restproject.rest.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.restproject.exception.BoardUpdateException;
import com.koreait.restproject.exception.MemberListException;
import com.koreait.restproject.message.Message;

import lombok.extern.slf4j.Slf4j;

//Rest��û�� ó���ϴ� ��Ʈ�ѷ��鿡�� �߻��ϴ� ��� ���ܴ� ���⼭ ��Ƴ���
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	//ȸ������� �����ö� �߻��ϴ� ����ó��
	@ExceptionHandler(MemberListException.class)
	@ResponseBody
	//string�� �����Ͱ� �ȵǰ� ��ü�� ���� �־��ָ� ���̽������Ͱ� �ۿ����ؼ� ����� �ɰ��� 
	public ResponseEntity<Message> handle(MemberListException e) {
		//����� �����͸� �����ؼ� ������ Ŭ���̾�Ʈ��, ����� ���ܸ� ó������...
		
		log.debug("Rest ��û �� �߻��ϴ� ����ó��");
		Message message = new Message();
		message.setMsg(e.getMessage());	//Ŭ���̾�Ʈ�� �ް� �� �޼���
		message.setStatusCode(500);

		//������������ http���������� ������ �������� ���� ��ü�� �����ȴ�. ���� �� ��ü�� �̿��ϸ�
		//Ŭ���̾�Ʈ���� �����͸� �����ϴ°� �ƴ϶� ������ ���µ� �Բ� ������ �� �ִ�.
		//ResponseEntity�� HttpEntity�� ��ӹ��� http���䰴ü
		ResponseEntity entity = new ResponseEntity<Message>(message, null, HttpStatus.BAD_REQUEST);
		
		return entity;
	}
	
	
	//�Խ��� ���� ����ó��
	@ExceptionHandler(BoardUpdateException.class)
	public ResponseEntity<Message> handleException(BoardUpdateException e) {
		Message message = new Message();
		message.setMsg(e.getMessage());	//�����޽��� ����
		
		//��ȣ �ȿ� �� �͵� (������ ������ ��ü(��������), ����� , ����status) ����� ���� ������
		ResponseEntity entity = new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}

}
