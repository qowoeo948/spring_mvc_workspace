package com.koreait.fashionshop.model.qna.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.exception.QnaDMLException;
import com.koreait.fashionshop.model.domain.Member;
import com.koreait.fashionshop.model.domain.Qna;

@Repository
public class MybatisQnaDAO implements QnaDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("Qna.selectAll");
	}

	@Override
	public List selectAll(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Qna select(int qna_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Qna qna) throws QnaDMLException{
		int result = sqlSessionTemplate.insert("Qna.insert", qna);
		if(result==0) {
			throw new QnaDMLException("질문등록에 실패하였습니다");
		}
	}

	@Override
	public void reply(Qna qna) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Qna qna) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int qna_id) {
		// TODO Auto-generated method stub
		
	}
	
}
