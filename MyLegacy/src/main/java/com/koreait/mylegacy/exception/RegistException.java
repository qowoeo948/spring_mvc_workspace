package com.koreait.mylegacy.exception;

//이 예외는, 컴파일 타임에 강요하지 않는 런타임 예외다 , 내가 정의 하겠다.
//나만의 예외 커스터마이징
public class RegistException extends RuntimeException{
	
	public RegistException(String msg) {
		super(msg);
	}
	
	public RegistException(String msg,Throwable e) {
		super(msg,e);
	}
		
}
