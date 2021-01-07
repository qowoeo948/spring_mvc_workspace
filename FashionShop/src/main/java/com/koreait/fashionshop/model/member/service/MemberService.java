package com.koreait.fashionshop.model.member.service;

import java.util.List;

import com.koreait.fashionshop.model.domain.Member;

public interface MemberService {
	public List selectAll();
	public Member select(Member member);	//회원1명 가져오기
	public void regist(Member member);	//insert라기엔 기타필요사항등 다른것도 추가할거같아서 regist로 바꿈
	public void update(Member member);
	public void delete(Member member);		//까다롭게 지우게 하기 위해 Member member로 받자
}
