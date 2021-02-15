package com.koreait.bootgradle.exception;

public class PhotoUpdateException extends RuntimeException{
	
	public PhotoUpdateException(String msg) {
		super(msg);
	}
	
	public PhotoUpdateException(String msg,Throwable e) {
		super(msg,e);
	}
	
	public PhotoUpdateException(Throwable e) {
		super(e);
	}
	

}
