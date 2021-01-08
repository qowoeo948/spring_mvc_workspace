package com.koreait.fashionshop.model.payment.service;

import java.util.List;

import com.koreait.fashionshop.model.domain.Cart;
import com.koreait.fashionshop.model.domain.Member;
import com.koreait.fashionshop.model.domain.OrderDetail;
import com.koreait.fashionshop.model.domain.OrderSummary;
import com.koreait.fashionshop.model.domain.Receiver;

public interface PaymentService {
	//��ٱ��� ���� ����
	public List selectCartList();	//ȸ�� ���о��� ��� ���� ��������
	public List selectCartList(int member_id);		//Ư�� ȸ���� ��ٱ��� ����
	public Cart selectCart(int cart_id);
	public void insert(Cart cart);
	public void update(List<Cart> cartList);	//�ϰ� ���� �Ѱ��� �ƴ϶� �ٷ��� ������
	public void delete(Cart cart);			//pk ���� ������ ������ ���� 
	public void delete(Member member);			//ȸ��id�� ���� ������ ������ ���� 
	
	
	//���� ����
	public List selectPaymethodList();
	public void registOrder(OrderSummary orderSummary, Receiver receiver);		//�߿�� Ʈ����� ó���� �䱸�Ǵ� �޼���	
}
