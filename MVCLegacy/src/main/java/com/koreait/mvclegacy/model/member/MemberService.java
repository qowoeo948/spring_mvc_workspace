package com.koreait.mvclegacy.model.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.koreait.mvclegacy.controller.admin.AdminController;

@Service
public class MemberService {
	private static final Logger logger=LoggerFactory.getLogger(MemberService.class);
	
	public void regist() {
		logger.debug("regist()메서드 호출");
		
	}
}
