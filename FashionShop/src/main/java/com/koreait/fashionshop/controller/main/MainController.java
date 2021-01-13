package com.koreait.fashionshop.controller.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.model.product.service.TopCategoryService;

@Controller
public class MainController {
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request,String name) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");		//메인페이지
		
		return mav;
	}
}
