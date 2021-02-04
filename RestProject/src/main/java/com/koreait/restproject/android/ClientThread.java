package com.koreait.restproject.android;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//������ ������ �Է°� ����� �����ϵ�, �ٸ� ��ü�� ���������� ����� �� �־�� �ϱ� ������ 
//������� �����Ѵ�.
public class ClientThread extends Thread{
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	boolean flag = true;	//������ ���� ���θ� �����ϴ� ����, �� �����带 ���̰� �ʹٸ� false�� �ָ�ȴ�.
	
	ChatClient chatClient;
	
	public ClientThread(Socket socket,ChatClient chatClient) {
		this.socket = socket;
		this.chatClient = chatClient;
	
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//�޽��� û��
	public void listen() {
		String msg = null;
		try {
			msg = buffr.readLine();
			//�α׿� �����
			chatClient.area.append(msg+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//�޽��� ����
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(flag) {
			listen();
		}
	}

}
