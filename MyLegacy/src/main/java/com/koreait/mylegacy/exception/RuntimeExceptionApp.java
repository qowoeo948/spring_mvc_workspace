package com.koreait.mylegacy.exception;

/*
 * 모든 예외는 최상위 객체는 그냥 Exception인데,
 * 이 예외는 크게 컴파일 타임에 처리를 강요하는 예외.
 * 런타임시 예외를 처리할 수 있는 런타임 예외가 있다..(강요하지 않음)
 * 
 * */
public class RuntimeExceptionApp {

	
	public static void main(String[] args) {
		int[]arr = new int[3];
		arr[0]=11;
		arr[1]=12;
		arr[2]=13;
		
		//비록, 아래의 코드를 대상으로 예외처리를 강요하지는 않지만, 개발자는 
    	//보다 더 안정적인 프로그램으로 제작하려면, 비정상 종료는 방지해야 한다.
		
		try {
			arr[3]=6;	//존재하지 않는 인덱스()배열의 범위를 넘어섬..
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("데이터 처리중 오류가 발생했습니다..");
			//담당자에게 이메일 발송, sms문자 전송.. 다양한 처리.. 복귀하기 위한.. 로그로 기록.
		}
		
	}
}
