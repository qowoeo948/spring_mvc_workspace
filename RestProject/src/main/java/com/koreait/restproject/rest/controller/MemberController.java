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

@RestController	//일반 Controller + ResponseBody  = RestController
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	//안드로이드로 할거니까 jsp로 주면 안됨
	//jsp페이지를 반환하지 말고, 데이터를 전송해야함
	//원래 responseBody를 해야하지만 위에서 @RestController로 해서 안해도됨
	@GetMapping("/member")
	public ResponseEntity<List<Member>> getList() {
		log.debug("Rest 리스트 요청했어?");
		List memberList = memberService.selectAll();
		
		//성공인 경우
		ResponseEntity entity=ResponseEntity.ok().body(memberList);
		
		return entity;
	}
	
	@PostMapping("/member")			//@RequestBody -> 이 member VO와 날라온 제이슨을 매핑시키라는 뜻
	public String regist(@RequestBody Member member) {
		log.debug("등록을 원해?");
		log.debug("m_id: "+member.getM_id());
		log.debug("m_pass: "+member.getM_pass());
		log.debug("m_name: "+member.getM_name());
		
		memberService.regist(member);	
		
		return "regist success";		//Rest에서는 개발자가 클라이언트에게 도대체 뭘 반환해야 할까?
	}
	
}
