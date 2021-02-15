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
	//spring 버전이 올라갈수록 자바코드에서 설정을 지원함
	
	//xml에서 작성했던, 표현식을 여기서 작성하면 된다.
	@Around("execution(public * com.koreait.bootgradle.model..*(..))")
	public void sound(ProceedingJoinPoint joinPoint) {
		log.debug("로직 수행전 딩동댕♬");
		try {
			joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		log.debug("로직 수행후 딩동댕♬");
	}
	

}
