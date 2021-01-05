package com.koreait.fashionshop.exception;

//MailSendException
public class MailSendException extends RuntimeException{

	public MailSendException(String msg) {
		super(msg);
	}
	
	public MailSendException(String msg,Throwable e) {
		super(msg,e);
	}
	
}
