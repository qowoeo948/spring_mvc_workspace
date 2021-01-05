package com.koreait.fashionshop.controller.member;

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
import com.koreait.fashionshop.exception.MemberRegistException;
import com.koreait.fashionshop.model.domain.Member;
import com.koreait.fashionshop.model.member.service.MemberService;
import com.koreait.fashionshop.model.member.service.MemberServiceImpl;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;

	//ȸ�������� ��û
	@RequestMapping(value="/shop/member/registForm",method=RequestMethod.GET)
	public String getRegistForm() {
		
		return "shop/member/signup";
	}
	
	//ȸ������ ��ûó��
	@RequestMapping(value="/shop/member/regist", method=RequestMethod.POST, produces="text/html;charset=utf-8")
	@ResponseBody
	public String regist(Member member) {
		logger.debug("���̵� "+member.getUser_id());
		logger.debug("�̸� "+member.getName());
		logger.debug("��� "+member.getPassword());
		logger.debug("�̸���ID "+member.getEmail_id());
		logger.debug("����ID "+member.getEmail_server());
		logger.debug("�����ȣ "+member.getZipcode());
		logger.debug("�ּ� "+member.getAddr());
		
		memberService.regist(member);
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(" \"result\":1, ");
		sb.append(" \"msg\":\"ȸ�����Լ���\"");
		sb.append("}");
		
		return sb.toString();
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
		mav.addObject("msg",e.getMessage());	//����ڰ� ���Ե� ���� �޼���
		mav.setViewName("shop/error/result");
		return mav;
	}
	

	
}
