package com.koreait.mylegacy.exception;

//�� ���ܴ�, ������ Ÿ�ӿ� �������� �ʴ� ��Ÿ�� ���ܴ� , ���� ���� �ϰڴ�.
//������ ���� Ŀ���͸���¡
public class RegistException extends RuntimeException{
	
	public RegistException(String msg) {
		super(msg);
	}
	
	public RegistException(String msg,Throwable e) {
		super(msg,e);
	}
		
}
