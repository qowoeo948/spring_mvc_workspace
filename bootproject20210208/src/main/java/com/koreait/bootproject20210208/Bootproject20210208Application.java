package com.koreait.bootproject20210208;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.koreait.bootproject20210208.test.Dog;

@SpringBootApplication
public class Bootproject20210208Application {

	public static void main(String[] args) {
		SpringApplication.run(Bootproject20210208Application.class, args);
	}
	
	//Spring2.5 버전부터는 xml을 사용하지 않고, 자바코드에서도 빈들을 생성 및 설정할 수 있는 방법을 제공한다.
	@Bean
	public Dog dog() {		//이때 사용되는 어노테이션이 @Bean   == <bean id="dog" class="..Dog">
		return new Dog();
	}
	
}
