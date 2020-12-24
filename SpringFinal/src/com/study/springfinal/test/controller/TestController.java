package com.study.springfinal.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/*갈수록, 시스템에 의존적이지 않는 POJO가 목표
 * Plain Olg Java Object 추구함
 * */
@Controller
public class TestController {
	
	//어떤 uri에 반응할지 즉 어떤 요청을 처리할지를 결정
	@RequestMapping(value="/hi",method=RequestMethod.GET)
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView("test/result");	//setViewName과 동일
		
		return mav;
	}
}
