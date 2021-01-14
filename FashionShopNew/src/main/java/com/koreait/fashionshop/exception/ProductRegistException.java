package com.koreait.fashionshop.exception;

//CRUD 작업시 발생되는 예외
public class ProductRegistException extends RuntimeException{
	
	public ProductRegistException(String msg) {
		super(msg);
	}
	public ProductRegistException(String msg, Throwable e) {
		super(msg, e);
	}
}
