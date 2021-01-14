package com.koreait.fashionshop.model.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.fashionshop.exception.QnaDMLException;
import com.koreait.fashionshop.model.common.Pager;
import com.koreait.fashionshop.model.domain.Member;
import com.koreait.fashionshop.model.domain.Qna;
import com.koreait.fashionshop.model.qna.repository.QnaDAO;

@Service
public class QnaServiceImpl implements QnaService{
	@Autowired
	private QnaDAO qnaDAO;
		
	@Override
	public List selectAll() {
		return qnaDAO.selectAll();
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
		qnaDAO.insert(qna);		
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
