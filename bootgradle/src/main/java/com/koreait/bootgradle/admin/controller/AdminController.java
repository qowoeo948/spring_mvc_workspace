package com.koreait.bootgradle.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	@GetMapping("/admin/main")
	public String getMain() {
		
		return "index";	//��ҿʹ� �ٸ���, jsp��� thymeleaf�� �Ҹ���, �� ������ ������ ���� �� �ϳ��� �Ẹ��
	}
	
	

}
