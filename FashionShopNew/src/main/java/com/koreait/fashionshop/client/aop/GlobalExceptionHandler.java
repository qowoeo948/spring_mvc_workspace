package com.koreait.fashionshop.client.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.exception.LoginAsyncRequiredException;
import com.koreait.fashionshop.exception.LoginRequiredException;
import com.koreait.fashionshop.model.common.MessageData;

/*모든 컨트롤러마다 일일이 예외처리 핸들러를 작성하면 코드 중복이 발생하므로, 
 *컨트롤러에서 발생하는 모든 예외를 감지하는 객체를 정의하여, 공통예외처리를 
 *작성하자!!
 * */
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(LoginRequiredException.class)
	public ModelAndView handleException(LoginRequiredException e) {
		ModelAndView mav = new ModelAndView();
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		mav.addObject("messageData", messageData);
		mav.setViewName("shop/error/message");
		
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
