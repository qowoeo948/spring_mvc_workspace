package com.koreait.fashionshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//������ ��忡���� ��ǰ�� ���� ��û ó��
@Controller
public class ProductController {
	
	//����ī�װ� ��������
	
	//����ī�װ� ��������

	//��ǰ���
	@RequestMapping(value="/admin/product/list",method=RequestMethod.GET)
	public ModelAndView getProductList() {
		ModelAndView mav = new ModelAndView("admin/product/product_list");
		return mav;
	}
	
	//��ǰ ��� ��
	@RequestMapping(value="/admin/product/registform")
	public String registForm() {
		return "admin/product/regist_form";
	}
	
	//��ǰ��
	
	//��ǰ���
	
	//��ǰ ����
	
	//��ǰ ����
	
}
