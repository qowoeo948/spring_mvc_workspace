package com.koreait.fashionshop.exception;

public class LoginAsyncRequiredException extends RuntimeException{
	
	public LoginAsyncRequiredException(String msg) {
		super(msg);
	}
	public LoginAsyncRequiredException(String msg, Throwable e) {
		super(msg, e);
	}
}
