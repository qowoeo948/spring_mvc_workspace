package com.koreait.fashionshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	//������ ��� ���� ��û
	@RequestMapping(value="/admin")
	public String adminMain() {
		
		return "admin/main";
	}
	
}
