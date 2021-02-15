package com.koreait.bootgradle.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class Bell {
	//spring ������ �ö󰥼��� �ڹ��ڵ忡�� ������ ������
	
	//xml���� �ۼ��ߴ�, ǥ������ ���⼭ �ۼ��ϸ� �ȴ�.
	@Around("execution(public * com.koreait.bootgradle.model..*(..))")
	public void sound(ProceedingJoinPoint joinPoint) {
		log.debug("���� ������ �������");
		try {
			joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		log.debug("���� ������ �������");
	}
	

}
