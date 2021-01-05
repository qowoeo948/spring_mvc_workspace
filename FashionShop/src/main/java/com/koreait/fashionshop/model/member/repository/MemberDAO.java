package com.koreait.fashionshop.model.member.repository;

import java.util.List;

import com.koreait.fashionshop.model.domain.Member;

public interface MemberDAO {
	public List selectAll();
	public Member select();	//ȸ��1�� ��������
	public void insert(Member member);
	public void update(Member member);
	public void delete(Member member);		//��ٷӰ� ����� �ϱ� ���� Member member�� ����
	
}
