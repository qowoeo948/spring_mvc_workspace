package com.koreait.bootgradle.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminBoardController {
	
	//府胶飘 夸没贸府
	@GetMapping("/admin/board")
	public String getList() {
		
		return "admin/board/list";
	}

}
