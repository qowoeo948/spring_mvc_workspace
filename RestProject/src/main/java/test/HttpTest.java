package test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * 자바 스탠다드에서는 Socket(stateful:연결 지속 됨 )통신 뿐만 아니라, 
 * 웹서버와 HTTP통신(statelsee)도 지원한다
 * 
 * */
public class HttpTest {
	URL url;
	HttpURLConnection con;	//HTTP 통신객체
	OutputStream os ;
	
	public HttpTest() {
		try {
			url = new URL("http://localhost:8888/rest/member");	//접속하고자 하는 요청 주소 명시
			con = (HttpURLConnection)url.openConnection();	//커넥션 객체 생성
			//post 방식 지정
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json;utf-8");	//http통신시 헤더 정보 구성
			//웹서버에 요청 시작	(출력스트림을 얻어와 출력을 해야함)
			con.setDoOutput(true);
			os = con.getOutputStream();
			os.write(0);
			os.flush();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(os!=null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public static void main(String[] args) {
		new HttpTest();
	}

}
