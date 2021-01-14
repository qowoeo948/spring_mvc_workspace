package com.koreait.fashionshop.client.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.exception.MailSendException;
import com.koreait.fashionshop.exception.MemberNotFoundException;
import com.koreait.fashionshop.exception.MemberRegistException;
import com.koreait.fashionshop.model.common.MessageData;
import com.koreait.fashionshop.model.domain.Member;
import com.koreait.fashionshop.model.member.service.MemberService;
import com.koreait.fashionshop.model.product.service.TopCategoryService;

@Controller
public class MemberController {
	private static final Logger logger=LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	//회원가입폼 요청 
	@RequestMapping(value="/member/registForm", method=RequestMethod.GET)
	public ModelAndView getRegistForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("shop/member/signup");
		return mav;
	}
	
	//회원가입 요청 처리 
	@RequestMapping(value="/member/regist", method=RequestMethod.POST, produces="text/html;charset=utf-8")
	@ResponseBody
	public String regist(Member member) {
		logger.debug("아이디 "+member.getUser_id());
		logger.debug("이름 "+member.getName());
		logger.debug("비번 "+member.getPassword());
		logger.debug("이메일id "+member.getEmail_id());
		logger.debug("이메일server "+member.getEmail_server());
		logger.debug("우편번호 "+member.getZipcode());
		logger.debug("주소 "+member.getAddr());
		
		memberService.regist(member);
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(" \"result\":1, ");
		sb.append(" \"msg\":\"회원가입 성공\"");
		sb.append("}");
		
		return sb.toString();
	} 

	//로그인 홈 요청 
	@RequestMapping(value="/member/loginForm", method=RequestMethod.GET)
	public ModelAndView getLoginForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("shop/member/signin");
		return mav;
	}
	
	//로그인 요청 처리
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String login(Member member, HttpServletRequest request) {
		//db에 존재여부 확인 
		Member obj=memberService.select(member);
		
		//존재 O : 세션에 회원정보 담아두기
		HttpSession session=request.getSession();
		session.setAttribute("member", obj); //현재 클라이언트 요청과 연계된 세션에 보관해 놓는다
		
		return "redirect:/shop/main";
	}
	
	//로그아웃 요청 처리 
	@RequestMapping(value="/member/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().invalidate(); //세션 무효화, 이시점부터 담겨진 데이터가 다 무효가 된다
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("로그아웃 되었습니다");
		messageData.setUrl("/shop/main");
		
		ModelAndView mav = new ModelAndView("shop/error/message");
		mav.addObject("messageData", messageData);
		return mav;
	}
	
	//예외 핸들러 2가지 처리
	@ExceptionHandler(MemberRegistException.class)
	@ResponseBody
	public String handleException(MemberRegistException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(" \"result\":0, ");
		sb.append(" \"msg\":\""+e.getMessage()+"\"");
		sb.append("}");
		
		return sb.toString();
	}
	
	@ExceptionHandler(MailSendException.class)
	public ModelAndView handleException(MailSendException e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", e.getMessage()); //사용자가 보게될 에러 메시지
		mav.setViewName("shop/error/result");
		return mav;
	}

	@ExceptionHandler(MemberNotFoundException.class)
	public ModelAndView handleException(MemberNotFoundException e) {
		
		ModelAndView mav = new ModelAndView();
		
		List topList = topCategoryService.selectAll();
		mav.addObject("topList", topList); //담기
		mav.addObject("msg", e.getMessage());
		mav.setViewName("shop/error/result");
		return mav;
	}
	
}












