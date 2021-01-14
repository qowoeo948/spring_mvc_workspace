package com.koreait.fashionshop.exception;

//CRUD 작업시 발생되는 예외
public class MailSendException extends RuntimeException{
	
	public MailSendException(String msg) {
		super(msg);
	}
	public MailSendException(String msg, Throwable e) {
		super(msg, e);
	}
}
