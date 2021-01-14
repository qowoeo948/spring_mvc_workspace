package com.koreait.fashionshop.exception;

public class DepositFailException extends RuntimeException{
	
	public DepositFailException(String msg) {
		super(msg);
	}
	public DepositFailException(String msg, Throwable e) {
		super(msg, e);
	}

}
