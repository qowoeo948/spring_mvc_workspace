package com.koreait.fashionshop.model.member.service;

import java.util.List;

import com.koreait.fashionshop.model.domain.Member;

public interface MemberService {
	public List selectAll();
	public Member select(Member member);	//ȸ��1�� ��������
	public void regist(Member member);	//insert��⿣ ��Ÿ�ʿ���׵� �ٸ��͵� �߰��ҰŰ��Ƽ� regist�� �ٲ�
	public void update(Member member);
	public void delete(Member member);		//��ٷӰ� ����� �ϱ� ���� Member member�� ����
}
