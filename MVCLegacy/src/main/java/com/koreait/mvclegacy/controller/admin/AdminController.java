package com.koreait.mvclegacy.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koreait.mvclegacy.model.member.MemberService;



/*
 * �����ڱ�ɰ� ���õ� ��� ��û�� ó���ϴ� ��Ʈ�ѷ�
 * */

@Controller
public class AdminController {
	private static final Logger logger=LoggerFactory.getLogger(AdminController.class);
	//All < DEBUG < INFO < WARN< ERROR < FATAL< OFF
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/main", method = RequestMethod.GET)
	public String test() {
		logger.debug("test() �޼��� ȣ��");
		logger.debug("���ο��� ������� MemberService�� �ּҰ� "+memberService);
		memberService.regist();
		
		return null;
	}
}
