package com.koreait.fashionshop.model.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.fashionshop.exception.MailSendException;
import com.koreait.fashionshop.exception.MemberNotFoundException;
import com.koreait.fashionshop.exception.MemberRegistException;
import com.koreait.fashionshop.model.common.MailSender;
import com.koreait.fashionshop.model.common.SecureManager;
import com.koreait.fashionshop.model.domain.Member;
import com.koreait.fashionshop.model.member.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDAO;
	
	//메일발송 객체
	@Autowired
	private MailSender mailSender;
	
	//암호화 객체 
	@Autowired
	private SecureManager secureManager;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member select(Member member) throws MemberNotFoundException{
		//유저가 전송한 파라미터비번을 해시값으로 변환하여 아래의 메서드 호출 
		String hash = secureManager.getSecureData(member.getPassword()); 
		member.setPassword(hash); //VO에 해시값 대입!!
		Member obj = memberDAO.select(member);
		return obj;
	}

	@Override
	public void regist(Member member) throws MemberRegistException, MailSendException{
		//DB에 넣기 + 이메일보내기 + 문자발송..
		
		//암호화 처리 
		String secureData = secureManager.getSecureData(member.getPassword());
		member.setPassword(secureData); //변환시켜 다시 VO에 대입
		
		memberDAO.insert(member);
		
		String name=member.getName();
		String addr=member.getAddr();
		String email = member.getEmail_id()+"@"+member.getEmail_server();
		
		mailSender.send(email , name+"님 [패션샵]가입축하드려요", addr+"에 거주하세요? 감사합니당");
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
