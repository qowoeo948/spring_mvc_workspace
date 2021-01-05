package com.koreait.fashionshop.model.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.fashionshop.common.MailSender;
import com.koreait.fashionshop.common.SecureManager;
import com.koreait.fashionshop.exception.MailSendException;
import com.koreait.fashionshop.exception.MemberRegistException;
import com.koreait.fashionshop.model.domain.Member;
import com.koreait.fashionshop.model.member.repository.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	//이메일 발송 객체
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
	public Member select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void regist(Member member) throws MemberRegistException,MailSendException {
		//DB에 넣기 + 이메일 보내기 + 문자발송 등등...
		
		//db넣기 전에 패스워드 암호화 처리
		String secureData = secureManager.getSecureData(member.getPassword());
		member.setPassword(secureData);  	//변환시켜 다시 vo에 대입
		
		//db
		memberDAO.insert(member);
		
		//이메일
		String email = member.getEmail_id()+"@"+member.getEmail_server();
		String name = member.getName();
		String addr = member.getAddr();
		mailSender.send(email, name+"님 [패션샵]가입축하드려요", addr+"에 거주하시죠? 감사합니당당당");
		
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
