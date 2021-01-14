package com.koreait.fashionshop.client.controller.cs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.exception.QnaDMLException;
import com.koreait.fashionshop.model.common.MessageData;
import com.koreait.fashionshop.model.common.Pager;
import com.koreait.fashionshop.model.domain.Qna;
import com.koreait.fashionshop.model.qna.service.QnaService;

@Controller
public class CustomerController {
	@Autowired
	private QnaService qnaService;
	
	@Autowired
	private Pager pager;
	
	@GetMapping("/cs/qna/list")
	public ModelAndView getMain(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("shop/cs/main");
		List qnaList = qnaService.selectAll();
		pager.init(request, qnaList); //넘겨진 List를 이용하여, 공식이 다시 계산됨..
		
		mav.addObject("pager", pager);
		
		return mav;
	}
	
	@GetMapping("/cs/qna/registForm")
	public ModelAndView getRegistForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("shop/cs/registForm");

		return mav;
		
	}
	
	@PostMapping("/cs/qna/regist")
	public ModelAndView registQna(HttpServletRequest request, Qna qna) {
		qnaService.insert(qna);
		ModelAndView mav = new ModelAndView("redirect:/shop/cs/qna/list");
		return mav;
	}
	
	//전역적 에러가 아닌 현재 질문게시판만의 예외를 처리
	@ExceptionHandler(QnaDMLException.class)
	public ModelAndView handleException(QnaDMLException e) {
		ModelAndView mav = new ModelAndView();
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		mav.addObject("messageData", messageData);
		mav.setViewName("shop/error/message");
		return mav;
	}

}

