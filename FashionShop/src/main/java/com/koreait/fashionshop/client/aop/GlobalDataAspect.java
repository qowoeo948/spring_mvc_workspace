package com.koreait.fashionshop.client.aop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.model.product.service.TopCategoryService;

/*
 * ���θ� �̿�� ���������� ����� ī�װ� �޴�, ���
 * 
 * */
public class GlobalDataAspect {
	@Autowired
	private TopCategoryService topCategoryService;
	
	public Object getGlobalData(ProceedingJoinPoint joinPoint) throws Throwable{
		Object result = null;
		HttpServletRequest request=null;
		
		//�Ű� ������ ���� ��û ��ü ����!!
		for(Object arg : joinPoint.getArgs()) {
			if(arg instanceof HttpServletRequest) {
				request = (HttpServletRequest)arg;
			}
		}
		
		//topList�� �����ؾ� �ϴ� ���� �׷��� ���� ��츦 ����� ó��
		//Ŭ���̾�Ʈ�� ��û�� topList�� �ʿ�� ���� ���� ���	:/shop/member/login, /shop/member/regist, 
		String uri = request.getRequestURI();	//Ŭ���̾�Ʈ ��û URI ��Ʈ�� ����
		if(uri.equals("/shop/member/login")||
			uri.equals("/shop/member/regist")||
			uri.equals("/admin/main")||
			uri.equals("/admin/product/regist")||
			uri.equals("/admin/product/excel/registform")
		) {
			result = joinPoint.proceed();		//���� ȣ���Ϸ� �ߴ� �޼��� ȣ��!!!go ahead
			
		}else {	//�ʿ��� ���
			//topList��������
			List topList = topCategoryService.selectAll();
			Object returnObj= joinPoint.proceed();		//���� ȣ���Ϸ� �ߴ� �޼��� ȣ��
			ModelAndView mav = null;
			if(returnObj instanceof ModelAndView) {
				mav = (ModelAndView)returnObj;
				mav.addObject("topList",topList);
				result=mav;
			}
		}
			
		return result;
	}
}
