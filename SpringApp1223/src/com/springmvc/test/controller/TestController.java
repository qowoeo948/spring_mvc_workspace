package com.springmvc.test.controller;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/*
 * �� Ŭ������ ��û�� ���������� ó���ϴ� ��Ʈ�ѷ� ���� ���
 * �߱��ϴ� ��ǥ: pojo(Plain Old Java Object) ������� ������ ������ ����..
 * */

public class TestController implements Controller{
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3�ܰ�: �˸´� ������ü�� �Ͻ�Ų��..
		String msg="�ȴ�";
		//4�ܰ�: ������ ���� �ִٸ� request��ü�� ����!
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg",msg);	//��
		//���� ��Ʈ�ѷ��� � �������� ������� ������ ���� ������ ������ 
		mav.setViewName("/test/result");	//��

		return mav;
		
	}
	
}
