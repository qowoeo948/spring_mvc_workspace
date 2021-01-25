package com.koreait.restproject.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.restproject.model.domain.Member;
import com.koreait.restproject.model.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

//�Ϲ����� ��û�� ó���ϴ� ��Ʈ�ѷ�
@Controller
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member")
	public List<Member> getList() {
		log.debug("�Ϲ� ����Ʈ ��û�߾�?");
		List memberList = memberService.selectAll();
		return memberList;
	}
}
