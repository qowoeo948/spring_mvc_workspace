package com.koreait.fashionshop.model.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Product {
	private int product_id;
	private int subcategory_id;
	private String product_name;
	private int price;
	private String brand;
	private String detail;
	private String filename;
	
	//�̹��� �ϳ��� �ڵ����� ó���ϴ� ��ü ����
	//��, �̸��� html�� ���ε� ������Ʈ �Ķ���͸�� ��ġ ���Ѿ� �ڵ����� ���ε� ó�����ش�..
	private MultipartFile repImg;		//��ǥ �̹���
	private MultipartFile addImg[];	//�߰� �̹����� ���û����̸� ���ÿ� �迭�̴�.

	//���󰪵�
	String[] color;
	//�������
	String[] fit;
}
