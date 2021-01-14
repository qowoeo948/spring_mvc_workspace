package com.koreait.fashionshop.admin.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	//������ ��� ���� ��û
	//dd�� ����� url-pattern�� ������ ��θ� ����ߵ�
	@RequestMapping(value="/secure", method=RequestMethod.GET)
	public String adminMain(HttpServletRequest request) {
		
		
		return "admin/main";
	}
	
}
