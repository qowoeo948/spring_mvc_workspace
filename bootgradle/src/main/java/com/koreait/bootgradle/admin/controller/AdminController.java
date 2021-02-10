package com.koreait.bootgradle.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	@GetMapping("/admin/main")
	public String getMain() {
		
		return "index";	//평소와는 다르게, jsp대신 thymeleaf라 불리는, 뷰 영역의 템프릿 엔진 중 하나를 써보자
	}
	
	

}
