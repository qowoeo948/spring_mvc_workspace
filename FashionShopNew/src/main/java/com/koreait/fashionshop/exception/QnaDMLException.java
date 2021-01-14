package com.koreait.fashionshop.exception;

public class QnaDMLException extends RuntimeException{
	
	public QnaDMLException(String msg) {
		super(msg);
	}
	public QnaDMLException(String msg, Throwable e) {
		super(msg, e);
	}
}
