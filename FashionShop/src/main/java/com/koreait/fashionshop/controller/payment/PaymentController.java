package com.koreait.fashionshop.controller.payment;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.common.MessageData;
import com.koreait.fashionshop.exception.CartException;
import com.koreait.fashionshop.exception.LoginRequiredException;
import com.koreait.fashionshop.model.domain.Cart;
import com.koreait.fashionshop.model.domain.Member;
import com.koreait.fashionshop.model.payment.service.PaymentService;
import com.koreait.fashionshop.model.product.service.TopCategoryService;
@Controller
public class PaymentController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private PaymentService PaymentService;
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	//��ٱ��Ͽ� ��ǰ ��� ��û
	@RequestMapping(value="/shop/cart/regist",method=RequestMethod.POST)
	@ResponseBody
	public MessageData registCart(Cart cart,HttpSession session) {

		Member member = (Member)session.getAttribute("member");
		logger.debug("product_id "+cart.getProduct_id());
		logger.debug("quantity "+cart.getQuantity());
		
		cart.setMember_id(member.getMember_id());
		PaymentService.insert(cart);
		
		//MessageConverter�� ���� VO�� JSON���·� ����Ǿ��� �� �ִ�.
		//�̷����ϸ� ������ json ���ڿ��� �ٲ㼭 �������� ������ ���̾�
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("��ٱ��Ͽ� ��ǰ�� �����ϴ�.");
		messageData.setUrl("/shop/cart/list");
		
		return messageData;
	}
	
	//��ٱ��� ��� ��û
	@RequestMapping(value="/shop/cart/list",method=RequestMethod.GET)
	public ModelAndView getCartList(HttpSession session) {
		//��ٱ��� ��� ��û���� �ռ�, �켱 ����ó������ ���� �ؾ���
		if(session.getAttribute("member")==null) {
			//���⼭ ���ܸ� ó���ϸ�, ��� ��Ʈ�ѷ� �޼��� ���� �α��ΰ� ���õ� �ڵ尡 �ߺ��ǹǷ�,
			//���ܸ� ������ �ϳ��� �޼��忡�� ó���ϵ��� ���뼺�� ������
			throw new LoginRequiredException("�α����� �ʿ��� �����Դϴ�.");
		}
		
		Member member = (Member)session.getAttribute("member");
		List topList = topCategoryService.selectAll();
		List cartList = PaymentService.selectCartList(member.getMember_id());
		
		
		ModelAndView mav = new ModelAndView("shop/cart/cart_list");
		mav.addObject("topList", topList); 
		mav.addObject("cartList", cartList); 
		
		return mav;
	}
	
	//��ٱ��� ����
	@RequestMapping(value="/shop/cart/del",method=RequestMethod.GET)
	public String delCart(HttpSession session) {
		//��ٱ��ϸ� �����ϱ� ���ؼ���, �α��� �� ȸ���� ����..
		if(session.getAttribute("member")==null) {
			throw new LoginRequiredException("�α����� �ʿ��� �����Դϴ�.");
		}
		PaymentService.delete((Member)session.getAttribute("member"));
		
		return "redirect:/shop/cart/list";
	}
	
	//��ٱ��� ����
	@RequestMapping(value="/shop/cart/edit",method=RequestMethod.POST)
	public ModelAndView editCart(@RequestParam("cart_id") int[] cartArray, @RequestParam("quantity") int[] qArray) {
		//�Ѱܹ��� �Ķ���� ����ϱ�!! cart_id, quantity
		logger.debug("cartArray length: "+cartArray.length);
		logger.debug("qArray length: "+qArray.length);
		
		List cartList = new ArrayList();
		for(int i=0;i<cartArray.length;i++) {
		Cart cart = new Cart();
		cart.setCart_id(cartArray[i]);
		cart.setQuantity(qArray[i]);
		cartList.add(cart);
		}
		
		PaymentService.update(cartList);
		
		//�����Ǿ��ٴ� �޼����� ����ʹٸ� message.jsp�� ��������
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("������ �����Ͽ����ϴ�.");
		messageData.setUrl("/shop/cart/list");
		ModelAndView mav = new ModelAndView();
		mav.addObject("messageData",messageData);
		mav.setViewName("shop/error/message"); 
		
		return mav;
	}
	
	
	
	
	//��ٱ��Ͽ� ���õ� ����ó�� �ڵ鷯
	@ExceptionHandler(CartException.class)
	@ResponseBody
	public MessageData handleException(CartException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		
		return messageData;
	}
	
	@ExceptionHandler(LoginRequiredException.class)
	public ModelAndView handleException(LoginRequiredException e) {
		ModelAndView mav = new ModelAndView();
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		mav.addObject("messageData",messageData);
		mav.setViewName("/shop/error/message");
		return mav;
	}
	
	
}
