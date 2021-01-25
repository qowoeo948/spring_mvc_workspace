package com.koreait.restproject.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.restproject.model.domain.Member;
import com.koreait.restproject.model.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RestController	//�Ϲ� Controller + ResponseBody  = RestController
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	//�ȵ���̵�� �ҰŴϱ� jsp�� �ָ� �ȵ�
	//jsp�������� ��ȯ���� ����, �����͸� �����ؾ���
	//���� responseBody�� �ؾ������� ������ @RestController�� �ؼ� ���ص���
	@GetMapping("/member")
	public ResponseEntity<List<Member>> getList() {
		log.debug("Rest ����Ʈ ��û�߾�?");
		List memberList = memberService.selectAll();
		
		//������ ���
		ResponseEntity entity=ResponseEntity.ok().body(memberList);
		
		return entity;
	}
	
	@PostMapping("/member")			//@RequestBody -> �� member VO�� ����� ���̽��� ���ν�Ű��� ��
	public String regist(@RequestBody Member member) {
		log.debug("����� ����?");
		log.debug("m_id: "+member.getM_id());
		log.debug("m_pass: "+member.getM_pass());
		log.debug("m_name: "+member.getM_name());
		
		memberService.regist(member);	
		
		return "regist success";		//Rest������ �����ڰ� Ŭ���̾�Ʈ���� ����ü �� ��ȯ�ؾ� �ұ�?
	}
	
}
