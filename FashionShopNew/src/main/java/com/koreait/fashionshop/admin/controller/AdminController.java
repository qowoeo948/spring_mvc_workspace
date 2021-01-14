package com.koreait.fashionshop.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	//관리자모드 메인 요청
	//dd에명시한 url-pattern의 나머지 경로로 매핑을 함
	@RequestMapping("/secure")
	public String adminMain(HttpServletRequest request) {
		return "admin/main";
	}
}





