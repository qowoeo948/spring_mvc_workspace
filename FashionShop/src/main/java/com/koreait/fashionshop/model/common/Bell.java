package com.koreait.fashionshop.model.common;

import org.springframework.stereotype.Component;

@Component
public class Bell {
	public void sound() {
		System.out.println("딩동♣");
	}
	
	public void ding() {
		System.out.println("빠라빠빠♣");
	}
}
