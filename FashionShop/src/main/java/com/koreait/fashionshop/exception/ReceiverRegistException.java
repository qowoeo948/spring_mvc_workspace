package com.koreait.fashionshop.exception;

//MailSendException
public class ReceiverRegistException extends RuntimeException{

	public ReceiverRegistException(String msg) {
		super(msg);
	}
	
	public ReceiverRegistException(String msg,Throwable e) {
		super(msg,e);
	}
	
}
