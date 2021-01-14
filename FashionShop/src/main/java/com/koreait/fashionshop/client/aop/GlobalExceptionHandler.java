package com.koreait.fashionshop.client.aop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.exception.LoginAsyncRequiredException;
import com.koreait.fashionshop.exception.LoginRequiredException;
import com.koreait.fashionshop.model.common.MessageData;

/*
 * ��� ��Ʈ�ѷ����� ������ ����ó�� �ڵ鷯�� �ۼ��ϸ� �ڵ��ߺ��� �߻��Ѵ�.
 * ��Ʈ�ѷ����� �߻��ϴ� ��� ���ܸ� �����ϴ� ��ü�� �����ϸ�, ���뿹��ó����
 * �ۼ�����
 * */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(LoginRequiredException.class)
	public ModelAndView handleException(LoginRequiredException e) {
		ModelAndView mav = new ModelAndView();
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		mav.addObject("messageData",messageData);
		mav.setViewName("/shop/error/message");
		return mav;
	}
	
	@ExceptionHandler(LoginAsyncRequiredException.class)
	@ResponseBody
	public MessageData handleException(LoginAsyncRequiredException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		
		return messageData;
	}
}
