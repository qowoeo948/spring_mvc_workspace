package com.koreait.fashionshop.common;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;

/*��¥, ��ȭ �� ���� ����ϴ� ǥ�⿡ ���� ó��*/
public class Formatter {
	
	//���ڸ� �ش� �ý����� ��ȭ�� ��ȯ�Ͽ� ��ȯ�ϴ� �޼���, 3�ڸ����� ��ǥ ó��
	public static String getCurrency(long number) {
		String currency = Currency.getInstance(Locale.KOREA).getSymbol();	//�ش� ���� ��ȭ ��ȯ
		DecimalFormat df = new DecimalFormat("###,###.###");
		String result = currency + df.format(number);
		
		return result;
	}
	
//	public static void main(String[] args) {
//		System.out.println(getCurrency(200000));
//	}

}
