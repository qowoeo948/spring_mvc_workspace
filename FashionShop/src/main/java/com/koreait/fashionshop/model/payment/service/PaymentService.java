package com.koreait.fashionshop.model.payment.service;

import java.util.List;

import com.koreait.fashionshop.model.domain.Cart;

public interface PaymentService {
	//��ٱ��� ���� ����
	public List selectCartList();	//ȸ�� ���о��� ��� ���� ��������
	public List selectCartList(int member_id);		//Ư�� ȸ���� ��ٱ��� ����
	public Cart selectCart(int cart_id);
	public void insert(Cart cart);
	public void update(Cart cart);
	public void delete(Cart cart);			//ȸ��id�� ���� ������ ������ ���� 
	
	
	//���� ����
	
}
