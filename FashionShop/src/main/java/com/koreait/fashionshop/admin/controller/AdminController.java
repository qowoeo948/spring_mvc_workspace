package com.koreait.fashionshop.admin.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	//관리자 모드 메인 요청
	//dd에 명시한 url-pattern의 나머지 경로를 적어야됨
	@RequestMapping(value="/secure", method=RequestMethod.GET)
	public String adminMain(HttpServletRequest request) {
		
		
		return "admin/main";
	}
	
}
