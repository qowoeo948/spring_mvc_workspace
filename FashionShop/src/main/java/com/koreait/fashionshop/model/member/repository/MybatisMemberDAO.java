package com.koreait.fashionshop.model.member.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.exception.MemberNotFoundException;
import com.koreait.fashionshop.exception.MemberRegistException;
import com.koreait.fashionshop.model.domain.Member;

@Repository
public class MybatisMemberDAO implements MemberDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	//�α��� ����
	@Override
	public Member select(Member member) throws MemberNotFoundException{ 
		Member obj = sqlSessionTemplate.selectOne("Member.select",member);
		if(obj==null) {		//�ùٸ��� ���� ����
			throw new MemberNotFoundException("�α��� ������ �ùٸ��� �ʽ��ϴ�.");
		}
		return obj;
	}

	@Override
	public void insert(Member member) throws MemberRegistException{
		int result = sqlSessionTemplate.insert("Member.insert",member);
		
		if(result==0) {
			throw new MemberRegistException("ȸ�����Կ� �����Ͽ����ϴ�");
		}
	}

	@Override
	public void update(Member member) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Member member) {
		// TODO Auto-generated method stub
		
	}

}
