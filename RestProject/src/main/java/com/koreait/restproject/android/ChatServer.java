package com.koreait.restproject.android;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatServer extends JFrame{
	JPanel p_north;
	JTextField t_port;
	JButton bt_start;
	JTextArea area;
	JScrollPane scroll;
	Thread thread; //���� ���� ������, ���ξ����尡 ���������� accept()�� ���� �����¿� ������ �ȵǹǷ�..
	ServerSocket server;	//���� ���������� ����
	Vector<ChatThread> vec; //������ Ŭ���̾�Ʈ���� ������ ChatThread�� �����س��� �÷���..
	
	public ChatServer() {
		p_north = new JPanel();
		t_port = new JTextField("9999",20);
		bt_start = new JButton("����");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		vec = new Vector<ChatThread>();
		
		p_north.add(t_port);
		p_north.add(bt_start);
		add(p_north,BorderLayout.NORTH);
		add(scroll);
		
		bt_start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startServer();
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				stopServer();
			}
		});
		
		setSize(300,400);
		setVisible(true);
	}
	
	public void startServer() {
		thread = new Thread() {
			@Override
			public void run() {
				 try {
					server = new ServerSocket(Integer.parseInt(t_port.getText()));
					area.append("��������\n");
					
					while(true) {
						Socket socket = server.accept();	//Ŭ���̾�Ʈ ������ �� ���� ���
						area.append("�����ڰ���!\n");
					
						ChatThread chatThread = new ChatThread(socket,ChatServer.this);
						chatThread.start();//��ȭ����!
						
						//���Ϳ� ���� ������ ������ �߰�(�̷��� ó���ؾ� ������ ����� ���� �� �ִ�)
						vec.add(chatThread);
						area.append("���� ������ ��:"+vec.size());
					}
					
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		};
		thread.start();
	}
	

	public void stopServer() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	
	public static void main(String[] args) {
		new ChatServer();
	}

}
