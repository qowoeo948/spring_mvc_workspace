package com.koreait.fashionshop.exception;

//CRUD 작업시 발생되는 예외
public class MemberRegistException extends RuntimeException{
	
	public MemberRegistException(String msg) {
		super(msg);
	}
	public MemberRegistException(String msg, Throwable e) {
		super(msg, e);
	}
}
