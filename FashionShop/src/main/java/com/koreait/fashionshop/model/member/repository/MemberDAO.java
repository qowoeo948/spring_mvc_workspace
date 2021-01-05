package com.koreait.fashionshop.model.member.repository;

import java.util.List;

import com.koreait.fashionshop.model.domain.Member;

public interface MemberDAO {
	public List selectAll();
	public Member select();	//회원1명 가져오기
	public void insert(Member member);
	public void update(Member member);
	public void delete(Member member);		//까다롭게 지우게 하기 위해 Member member로 받자
	
}
