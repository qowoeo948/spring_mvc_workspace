package com.koreait.restproject.client.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.koreait.restproject.exception.MemberListException;

import lombok.extern.slf4j.Slf4j;

//�Ϲݿ�û�� ���� �۷ι� ����ó��
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
		
		//ȸ������� �����ö� �߻��ϴ� ����ó��
		@ExceptionHandler(MemberListException.class)
		public String handle(MemberListException e) {
			log.debug("�Ϲݿ�û �� �߻��ϴ� ����ó��");
			return null;
		}

	}
	
