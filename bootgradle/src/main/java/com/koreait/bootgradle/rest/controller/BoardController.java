package com.koreait.bootgradle.rest.controller;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.bootgradle.model.domain.Board;

@Controller
public class BoardController {
	
	@GetMapping("/rest/board/{board_id}")
	@ResponseBody //결과가 jsp가 아닌 json
	//반환형이 String이 아닌 vo형인 경우, converter가 지원되어야 한다.
	//스프링 설정파일 jackson jar다운받아..MessageConverter...설정
	//하지만 spingboot는 이 과정이 필요없고 이 등록이 다 설정되어있다..
	public Board getDetail(@PathVariable int board_id) {	//@PathVariable -> 경로가 아니라 변수다! 
		Board board = new Board();
		board.setBoard_id(board_id);
		board.setTitle("난 vo야");
		
		
		return board;
	}

}
