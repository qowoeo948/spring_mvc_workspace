package com.koreait.fashionshop.controller.admin;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	//������ ��� ���� ��û
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public ModelAndView adminMain(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("admin/main");
		
		return mav;
	}
	
}
