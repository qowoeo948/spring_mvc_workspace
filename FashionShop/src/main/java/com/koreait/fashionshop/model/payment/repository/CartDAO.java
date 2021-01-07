package com.koreait.fashionshop.model.payment.repository;

import java.util.List;

import com.koreait.fashionshop.model.domain.Cart;
import com.koreait.fashionshop.model.domain.Member;

public interface CartDAO {
	public List selectAll();	//ȸ�� ���о��� ��� ���� ��������
	public List selectAll(int member_id);		//Ư�� ȸ���� ��ٱ��� ����
	public Cart select(int cart_id);
	public void duplicateCheck(Cart cart);	//��ٱ��� �ߺ���ǰ ���� üũ 	
	public void insert(Cart cart);
	public void update(Cart cart);
	public void delete(Cart cart);			//pk�� �̿��� ����
	public void delete(Member member);				//ȸ���� ���� ������ ������ ����
	
}
