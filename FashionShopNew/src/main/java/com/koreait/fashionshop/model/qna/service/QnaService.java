package com.koreait.fashionshop.model.qna.service;

import java.util.List;

import com.koreait.fashionshop.model.domain.Member;
import com.koreait.fashionshop.model.domain.Qna;

public interface QnaService {
	public List selectAll();
	public List selectAll(Member member);//회원의 목록만 가져오기
	public Qna select(int qna_id);
	public void insert(Qna qna);
	public void reply(Qna qna); //답변하기
	public void update(Qna qna);
	public void delete(int qna_id);	
}
