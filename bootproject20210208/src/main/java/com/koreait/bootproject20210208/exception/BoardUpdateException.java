package com.koreait.bootproject20210208.exception;

public class BoardUpdateException extends RuntimeException{
	
	public BoardUpdateException(String msg) {
		super(msg);
	}
	
	public BoardUpdateException(String msg,Throwable e) {
		super(msg,e);
	}
	
	public BoardUpdateException(Throwable e) {
		super(e);
	}


}
