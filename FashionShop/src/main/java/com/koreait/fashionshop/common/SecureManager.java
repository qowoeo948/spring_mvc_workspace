package com.koreait.fashionshop.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

/*
 * �ϴ� ��ȣȭ �� ���Ŀ�, ��ȣȭ ��ų �� ���� SHA256 �˰������� ȸ���� ��й�ȣ�� ��ȣȭ�����ִ� ��ü
 * */
@Component
public class SecureManager {
	public String getSecureData(String password) {
		StringBuffer sb = new StringBuffer();		//���ڿ��� ������ų ��ü
		
		try {
			MessageDigest digest =MessageDigest.getInstance("SHA-256");
			byte[] data = password.getBytes("UTF-8");			//�ϴ� ����Ʈ �迭�� �ɰ���
			byte[] hash = digest.digest(data);		
			
			//�ɰ��� �����͸� ������� 16���� ������ ��ȯ�Ͽ� ���ڿ��� ������ų ��
			for(int i=0;i<hash.length;i++) {
				String hex = Integer.toHexString(0xff&hash[i]);	//������ 16���� ���ڿ��� ��ȯ
				
				if(hex.length()==1) {
					sb.append("0");
				}
				//System.out.println(hex);	//�߰�����
				sb.append(hex);
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
//	public static void main(String[] args) {
//		String result = new SecureManager().getSecureData("bananaking");
//		System.out.println(result);
//	}
	
}
