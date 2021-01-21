/*
자바도 html문서처럼, 웹 서버와 http통신이 가능하다..
*/
package network;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class ConnectManager{
	URL url;
	HttpURLConnection con;	//http통신을 위한 객체(헤더+바디를 구성하여 서버와 데이터를 주고받는 방식)
											//stateless 한 통신 

	public void requestByGet(){		//Get방식으로 요청을 시도하는 메서드
		BufferedReader buffr = null;

		try{
			url = new URL("http://192.168.1.2:8888/rest/member");	//요청 주소
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			
			//서버로 부터 응답 데이터 가져오기
			buffr = new BufferedReader(new InputStreamReader(con.getInputStream())); 		//바이트 기반 스트림 
			
			String data = null;
			StringBuilder sb = new StringBuilder();		//문자열을 누적할 객체
			while(true){
				data = buffr.readLine();	//한줄 읽어들인다.
				if(data==null)break;	//읽어들일 데이터가 없다면 무한루프 종료
				sb.append(data);//읽어들인 문자열을 누적시키자
			}

			System.out.println("서버가 보낸 응답데이터는: "+sb.toString());

			int code = con.getResponseCode();		//서버로부터 받은 응답코드 반환	(이 시점에는 이미 서버에 요청을 완료 후 응답도 받은 상태) 

			System.out.println("서버로 받은 응답코드는 "+code);
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(buffr!=null){
				try{
					buffr.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}

	//Post방식의 요청을 시도하되, JSON데이터를 전송하겠다!!
	public int requestByPost(String requestUrl,String param){
		BufferedWriter buffw = null;	//버퍼처리된 문자기반 스트림
		int code=0;	//서버의 응답 코드

		try{
			url = new URL(requestUrl);	//요청 주소
			con = (HttpURLConnection)url.openConnection();
			//데이터 형식을 헤더에 첨가해줘야, 서버측에서 제이슨 데이터가 전소되어 온것임을 안다.. 이게 바로 .HTTP프로토콜간의 약속이다
			con.setRequestProperty("Content-Type","application/json;charset=utf-8");

			con.setRequestMethod("POST");
			con.setDoOutput(true);	//서버에 데이터를 출력하기 위해 필요한 옵션!!
			//오청을 떠나기전에 준비할게 있다면 여기서 준비하자!!,json문자열을 준비하자
			//JSON 객체 자체가 아닌 문자열로 준비하는 이유는 ?	"{}"
			/*
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append(" \"m_id\":\"batman\", ");
			sb.append(" \"m_pass\":\"1234\", ");
			sb.append(" \"m_name\":\"배트맨\" ");
			sb.append("}");
			*/
			//실행중인 프로그램에서 서버로 데이터를 보내야 하므로, 출력 스트림으로 처리하자 
			buffw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(),"UTF-8"));
			buffw.write(param);
			buffw.flush();

			code = con.getResponseCode();		//요청 + 응답이 발생
			System.out.println("서버로 부터 받은 응답 코드는 "+code);
			
			
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(buffw!=null){
				try{
					buffw.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}

		return code;	//응답코드 반환

	}

	public static void main(String[] args){
		ConnectManager manager = new ConnectManager();
		//manager.requestByGet();
		manager.requestByPost();
	}
}
