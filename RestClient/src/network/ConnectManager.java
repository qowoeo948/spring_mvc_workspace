/*
�ڹٵ� html����ó��, �� ������ http����� �����ϴ�..
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
	HttpURLConnection con;	//http����� ���� ��ü(���+�ٵ� �����Ͽ� ������ �����͸� �ְ��޴� ���)
											//stateless �� ��� 

	public void requestByGet(){		//Get������� ��û�� �õ��ϴ� �޼���
		BufferedReader buffr = null;

		try{
			url = new URL("http://192.168.1.2:8888/rest/member");	//��û �ּ�
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			
			//������ ���� ���� ������ ��������
			buffr = new BufferedReader(new InputStreamReader(con.getInputStream())); 		//����Ʈ ��� ��Ʈ�� 
			
			String data = null;
			StringBuilder sb = new StringBuilder();		//���ڿ��� ������ ��ü
			while(true){
				data = buffr.readLine();	//���� �о���δ�.
				if(data==null)break;	//�о���� �����Ͱ� ���ٸ� ���ѷ��� ����
				sb.append(data);//�о���� ���ڿ��� ������Ű��
			}

			System.out.println("������ ���� ���䵥���ʹ�: "+sb.toString());

			int code = con.getResponseCode();		//�����κ��� ���� �����ڵ� ��ȯ	(�� �������� �̹� ������ ��û�� �Ϸ� �� ���䵵 ���� ����) 

			System.out.println("������ ���� �����ڵ�� "+code);
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

	//Post����� ��û�� �õ��ϵ�, JSON�����͸� �����ϰڴ�!!
	public int requestByPost(String requestUrl,String param){
		BufferedWriter buffw = null;	//����ó���� ���ڱ�� ��Ʈ��
		int code=0;	//������ ���� �ڵ�

		try{
			url = new URL(requestUrl);	//��û �ּ�
			con = (HttpURLConnection)url.openConnection();
			//������ ������ ����� ÷�������, ���������� ���̽� �����Ͱ� ���ҵǾ� �°����� �ȴ�.. �̰� �ٷ� .HTTP�������ݰ��� ����̴�
			con.setRequestProperty("Content-Type","application/json;charset=utf-8");

			con.setRequestMethod("POST");
			con.setDoOutput(true);	//������ �����͸� ����ϱ� ���� �ʿ��� �ɼ�!!
			//��û�� ���������� �غ��Ұ� �ִٸ� ���⼭ �غ�����!!,json���ڿ��� �غ�����
			//JSON ��ü ��ü�� �ƴ� ���ڿ��� �غ��ϴ� ������ ?	"{}"
			/*
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append(" \"m_id\":\"batman\", ");
			sb.append(" \"m_pass\":\"1234\", ");
			sb.append(" \"m_name\":\"��Ʈ��\" ");
			sb.append("}");
			*/
			//�������� ���α׷����� ������ �����͸� ������ �ϹǷ�, ��� ��Ʈ������ ó������ 
			buffw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(),"UTF-8"));
			buffw.write(param);
			buffw.flush();

			code = con.getResponseCode();		//��û + ������ �߻�
			System.out.println("������ ���� ���� ���� �ڵ�� "+code);
			
			
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

		return code;	//�����ڵ� ��ȯ

	}

	public static void main(String[] args){
		ConnectManager manager = new ConnectManager();
		//manager.requestByGet();
		manager.requestByPost();
	}
}