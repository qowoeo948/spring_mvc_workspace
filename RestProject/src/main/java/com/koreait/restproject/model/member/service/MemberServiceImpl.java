package com.koreait.restproject.model.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.restproject.exception.MemberListException;
import com.koreait.restproject.exception.MemberUpdateException;
import com.koreait.restproject.model.domain.Member;
import com.koreait.restproject.model.member.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public List selectAll() {
		//테스트목적으로 일부러 예외 발생시키자
//		if(true) {
//			throw new MemberListException("회원목록 가져오기 실패");
//		}
		return memberDAO.selectAll();
	}

	@Override
	public Member select(int member_id) {
		return null;
	}

	@Override
	public void regist(Member member) throws MemberUpdateException{
		memberDAO.insert(member);
	}

	@Override
	public void update(Member member) {
		
	}

	@Override
	public void delete(int member_id) {
		
	}

}
