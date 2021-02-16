package com.koreait.bootgradle.exception;

public class FileUploadException extends RuntimeException{
	
	public FileUploadException(String msg) {
		super(msg);
	}
	
	public FileUploadException(String msg,Throwable e) {
		super(msg,e);
	}
	
	public FileUploadException(Throwable e) {
		super(e);
	}
	

}
