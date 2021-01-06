package com.koreait.fashionshop.model.payment.repository;

import java.util.List;

import com.koreait.fashionshop.model.domain.Cart;

public interface CartDAO {
	public List selectAll();	//ȸ�� ���о��� ��� ���� ��������
	public List selectAll(int member_id);		//Ư�� ȸ���� ��ٱ��� ����
	public Cart select(int cart_id);
	
	public void insert(Cart cart);
	public void update(Cart cart);
	public void delete(Cart cart);			//ȸ��id�� ���� ������ ������ ���� 
	
}
