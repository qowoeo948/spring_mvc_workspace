package com.koreait.fashionshop.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.fashionshop.model.common.MessageData;

@Controller
public class QnaController {
	private static final Logger logger=LoggerFactory.getLogger(QnaController.class);
	
	@RequestMapping(value="/cs/qna/list", method=RequestMethod.GET)
	@ResponseBody
	public MessageData getQnaList(){
		logger.debug("Qna request received!!");
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("this is qna list");
		
		return messageData;
	}
}
