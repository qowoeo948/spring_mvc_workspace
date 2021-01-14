package com.koreait.fashionshop.client.controller.payment;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.exception.CartException;
import com.koreait.fashionshop.exception.LoginRequiredException;
import com.koreait.fashionshop.model.common.MessageData;
import com.koreait.fashionshop.model.domain.Cart;
import com.koreait.fashionshop.model.domain.Member;
import com.koreait.fashionshop.model.domain.OrderSummary;
import com.koreait.fashionshop.model.domain.Receiver;
import com.koreait.fashionshop.model.payment.service.PaymentService;
import com.koreait.fashionshop.model.product.service.TopCategoryService;
@Controller
public class PaymentController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private PaymentService paymentService;
	
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
		paymentService.insert(cart);
		
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
	public ModelAndView getCartList(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Member member = (Member)session.getAttribute("member");
		List topList = topCategoryService.selectAll();
		List cartList = paymentService.selectCartList(member.getMember_id());
		
		
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
		paymentService.delete((Member)session.getAttribute("member"));
		
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
		
		paymentService.update(cartList);
		
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
	
	
	//üũ�ƿ� ������ ��û 
	@GetMapping("/shop/payment/form")
	public String payForm(Model model,HttpSession session) {
		List topList = topCategoryService.selectAll();
		model.addAttribute("topList", topList); //ModelAndView������ Model�� ���..
		
		//�������� �������� 
		List paymethodList = paymentService.selectPaymethodList();
		model.addAttribute("paymethodList", paymethodList);
		
		//��ٱ��� ���� ��������
		Member member = (Member)session.getAttribute("member");
		List cartList = paymentService.selectCartList(member.getMember_id());
		model.addAttribute("cartList", cartList);
		
		return "shop/payment/checkout";
	}
	
	@PostMapping("/shop/payment/regist")
	public String pay(HttpSession session, OrderSummary orderSummary, Receiver receiver) {
		logger.debug("���� ��� �̸� "+receiver.getReceiver_name());
		logger.debug("���� ��� ����ó "+receiver.getReceiver_phone());
		logger.debug("���� ��� �ּ� "+receiver.getReceiver_addr());
		logger.debug("��������� "+orderSummary.getPaymethod_id());
		logger.debug("total_price "+orderSummary.getTotal_price());
		logger.debug("total_pay "+orderSummary.getTotal_pay());
		Member member=(Member)session.getAttribute("member");
		orderSummary.setMember_id(member.getMember_id()); //ȸ�� pk
		
		paymentService.registOrder(orderSummary, receiver);
		
		return "";
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
	
}
