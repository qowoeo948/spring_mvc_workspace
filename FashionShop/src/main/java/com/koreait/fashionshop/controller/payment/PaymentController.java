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
	
	//장바구니에 상품 담기 요청
	@RequestMapping(value="/shop/cart/regist",method=RequestMethod.POST)
	@ResponseBody
	public MessageData registCart(Cart cart,HttpSession session) {

		Member member = (Member)session.getAttribute("member");
		logger.debug("product_id "+cart.getProduct_id());
		logger.debug("quantity "+cart.getQuantity());
		
		cart.setMember_id(member.getMember_id());
		PaymentService.insert(cart);
		
		//MessageConverter에 의해 VO는 JSON형태로 응답되어질 수 있다.
		//이렇게하면 저절로 json 문자열로 바꿔서 전송해줘 눈물날 일이야
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("장바구니에 상품이 담겼습니다.");
		messageData.setUrl("/shop/cart/list");
		
		return messageData;
	}
	
	//장바구니 목록 요청
	@RequestMapping(value="/shop/cart/list",method=RequestMethod.GET)
	public ModelAndView getCartList(HttpSession session) {
		//장바구니 목록 요청보다 앞서, 우선 보안처리부터 먼저 해야함
		if(session.getAttribute("member")==null) {
			//여기서 예외를 처리하면, 모든 컨트롤러 메서드 마다 로그인과 관련된 코드가 중복되므로,
			//예외를 일으켜 하나의 메서드에서 처리하도록 재사용성을 높이자
			throw new LoginRequiredException("로그인이 필요한 서비스입니다.");
		}
		
		Member member = (Member)session.getAttribute("member");
		List topList = topCategoryService.selectAll();
		List cartList = PaymentService.selectCartList(member.getMember_id());
		
		
		ModelAndView mav = new ModelAndView("shop/cart/cart_list");
		mav.addObject("topList", topList); 
		mav.addObject("cartList", cartList); 
		
		return mav;
	}
	
	//장바구니 비우기
	@RequestMapping(value="/shop/cart/del",method=RequestMethod.GET)
	public String delCart(HttpSession session) {
		//장바구니를 삭제하기 위해서는, 로그인 한 회원만 가능..
		if(session.getAttribute("member")==null) {
			throw new LoginRequiredException("로그인이 필요한 서비스입니다.");
		}
		PaymentService.delete((Member)session.getAttribute("member"));
		
		return "redirect:/shop/cart/list";
	}
	
	//장바구니 수정
	@RequestMapping(value="/shop/cart/edit",method=RequestMethod.POST)
	public ModelAndView editCart(@RequestParam("cart_id") int[] cartArray, @RequestParam("quantity") int[] qArray) {
		//넘겨받은 파라미터 출력하기!! cart_id, quantity
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
		
		//수정되었다는 메세지를 보고싶다면 message.jsp로 응답하자
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("수정이 성공하였습니다.");
		messageData.setUrl("/shop/cart/list");
		ModelAndView mav = new ModelAndView();
		mav.addObject("messageData",messageData);
		mav.setViewName("shop/error/message"); 
		
		return mav;
	}
	
	
	
	
	//장바구니와 관련된 예외처리 핸들러
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
