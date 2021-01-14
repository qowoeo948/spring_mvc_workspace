package com.koreait.fashionshop.model.member.repository;

import java.util.List;

import com.koreait.fashionshop.model.domain.Member;

public interface MemberDAO {
	public List selectAll();//모든 회원가져오기
	public Member select(Member member); //회원1명 가져오기
	public void insert(Member member);//회원등록
	public void update(Member member); //회원정보 수정
	public void delete(Member member); //회원정보 삭제
}
