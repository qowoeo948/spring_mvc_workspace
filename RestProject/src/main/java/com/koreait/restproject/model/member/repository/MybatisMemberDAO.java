package com.koreait.restproject.model.member.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.restproject.model.domain.Member;

@Repository
public class MybatisMemberDAO implements MemberDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List selectAll() {
		return null;
	}

	@Override
	public Member select(int member_id) {
		return null;
	}

	@Override
	public void insert(Member member) throws RuntimeException{
		int result = sqlSessionTemplate.insert("Member.insert",member);
		if(result==0) {
			throw new RuntimeException("등록 에러");
		}
	}

	@Override
	public void update(Member member) {
		
	}

	@Override
	public void delete(int member_id) {
		
	}

}
