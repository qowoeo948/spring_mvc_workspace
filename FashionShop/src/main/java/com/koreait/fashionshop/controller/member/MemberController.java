package com.koreait.fashionshop.controller.member;

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

import com.koreait.fashionshop.common.MessageData;
import com.koreait.fashionshop.exception.MailSendException;
import com.koreait.fashionshop.exception.MemberNotFoundException;
import com.koreait.fashionshop.exception.MemberRegistException;
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
	
	//ȸ�������� ��û 
	@RequestMapping(value="/shop/member/registForm", method=RequestMethod.GET)
	public ModelAndView getRegistForm() {
		List topList = topCategoryService.selectAll();
		ModelAndView mav = new ModelAndView("shop/member/signup");
		mav.addObject("topList", topList); //���
		return mav;
	}
	
	//ȸ������ ��û ó�� 
	@RequestMapping(value="/shop/member/regist", method=RequestMethod.POST, produces="text/html;charset=utf-8")
	@ResponseBody
	public String regist(Member member) {
		logger.debug("���̵� "+member.getUser_id());
		logger.debug("�̸� "+member.getName());
		logger.debug("��� "+member.getPassword());
		logger.debug("�̸���id "+member.getEmail_id());
		logger.debug("�̸���server "+member.getEmail_server());
		logger.debug("�����ȣ "+member.getZipcode());
		logger.debug("�ּ� "+member.getAddr());
		
		memberService.regist(member);
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(" \"result\":1, ");
		sb.append(" \"msg\":\"ȸ������ ����\"");
		sb.append("}");
		
		return sb.toString();
	} 

	//�α��� Ȩ ��û 
	@RequestMapping(value="/shop/member/loginForm", method=RequestMethod.GET)
	public ModelAndView getLoginForm() {
		List topList = topCategoryService.selectAll();
		ModelAndView mav = new ModelAndView("shop/member/signin");
		mav.addObject("topList", topList); //���
		
		return mav;
	}
	
	//�α��� ��û ó��
	@RequestMapping(value="/shop/member/login", method=RequestMethod.POST)
	public String login(Member member, HttpServletRequest request) {
		//db�� ���翩�� Ȯ�� 
		Member obj=memberService.select(member);
		
		//���� O : ���ǿ� ȸ������ ��Ƶα�
		HttpSession session=request.getSession();
		session.setAttribute("member", obj); //���� Ŭ���̾�Ʈ ��û�� ����� ���ǿ� ������ ���´�
		
		return "redirect:/";
	}
	
	//�α׾ƿ� ��û ó�� 
	@RequestMapping(value="/shop/member/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().invalidate(); //���� ��ȿȭ, �̽������� ����� �����Ͱ� �� ��ȿ�� �ȴ�
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("�α׾ƿ� �Ǿ����ϴ�");
		messageData.setUrl("/");
		
		ModelAndView mav = new ModelAndView("shop/error/message");
		mav.addObject("messageData", messageData);
		return mav;
	}
	
	//���� �ڵ鷯 2���� ó��
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
		mav.addObject("msg", e.getMessage()); //����ڰ� ���Ե� ���� �޽���
		mav.setViewName("shop/error/result");
		return mav;
	}

	@ExceptionHandler(MemberNotFoundException.class)
	public ModelAndView handleException(MemberNotFoundException e) {
		
		ModelAndView mav = new ModelAndView();
		
		List topList = topCategoryService.selectAll();
		mav.addObject("topList", topList); //���
		mav.addObject("msg", e.getMessage());
		mav.setViewName("shop/error/result");
		return mav;
	}
	
}