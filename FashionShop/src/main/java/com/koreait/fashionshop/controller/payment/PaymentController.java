package com.koreait.fashionshop.controller.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koreait.fashionshop.model.domain.Cart;
import com.koreait.fashionshop.model.payment.service.PaymentService;
@Controller
public class PaymentController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private PaymentService PaymentService;
	
	//장바구니에 상품 담기 요청
	@RequestMapping(value="/shop/cart/regist",method=RequestMethod.POST)
	public String registCart(Cart cart) {
		logger.debug("product_id "+cart.getProduct_id());
		logger.debug("quantity "+cart.getQuantity());
		
		cart.setMember_id(1);
		PaymentService.insert(cart);
		
		return null;
	}
}
