package com.koreait.fashionshop.aop;
/*
 * ������ �α����� �ʿ��� ���� ���θ� ó���ϱ� ���� �ڵ��, ��Ʈ�ѷ��� �����ʰ�,
 * ���� �� ��ü�� ����ȭ���� aop�� �����ϰڴ�.
 * */


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koreait.fashionshop.exception.LoginRequiredException;

public class MemberSessionCheckAspect {
	private static final Logger logger = LoggerFactory.getLogger(MemberSessionCheckAspect.class);
	
	public Object sessionCheck(ProceedingJoinPoint joinPoint) throws Throwable{
		
		Object target = joinPoint.getTarget();	//���� ȣ���Ϸ��� �ߴ� ��ü
		logger.debug("���� ȣ���Ϸ��� �ߴ� ��ü�� "+target);
		String methodName = joinPoint.getSignature().getName();
		logger.debug("���� ȣ���Ϸ��ߴ� �޼���� "+methodName);
		Object[] args = joinPoint.getArgs();	//���� ȣ���Ϸ� �ߴ� �޼����� �Ű�����
		//������ ��û�� ������ ������ �ִ����� �Ǵ��Ͽ�, ������ ����..
		
		//������ ��� ���ؼ��� HttpServletRequest�� �ʿ��ϴ�!!
		HttpServletRequest request = null;
		
		for(Object arg : args) {
			logger.debug("�Ű��������� "+arg);
			if(arg instanceof HttpServletRequest) {	//request��ü���..
				request= (HttpServletRequest)arg;
			}
		}
		
		//1.������ ���ٸ�?? ���ܸ� �߻���ų����	--> ExceptionJandler�� ���ļ� Ŭ���̾�Ʈ���� ������ ����ó��..
		//2.������ �ִٸ�?? �״�� ���� ȣ���Ϸ��ߴ� �޼��� ����~
		HttpSession session= null;
		session=request.getSession();
		Object result;
		if(session.getAttribute("member")==null) {
			throw new LoginRequiredException("�α����� �ʿ��� �����Դϴ�.");
		}else {
			//���� ��û�� �帧�� �״�� ����..
			//���� ȣ���Ϸ����� �޼��带 ��� ȣ��
			result = joinPoint.proceed();	//���⼭ ���� �����ϹǷ� �׳� throw����
		}

		return result;
		
	}
}
