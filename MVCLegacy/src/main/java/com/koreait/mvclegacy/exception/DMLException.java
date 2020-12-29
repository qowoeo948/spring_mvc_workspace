package com.koreait.mvclegacy.exception;

public class DMLException extends RuntimeException{
	public DMLException(String msg) {
		super(msg);
	}

	public DMLException(String msg,Throwable e) {
		super(msg,e);
	}
	
}
