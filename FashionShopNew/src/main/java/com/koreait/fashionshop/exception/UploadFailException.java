package com.koreait.fashionshop.exception;

public class UploadFailException extends RuntimeException{
	
	public UploadFailException(String msg) {
		super(msg);
	}
	public UploadFailException(String msg, Throwable e) {
		super(msg, e);
	}
}
