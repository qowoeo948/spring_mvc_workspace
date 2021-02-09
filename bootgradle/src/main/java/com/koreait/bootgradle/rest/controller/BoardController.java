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
	@ResponseBody //����� jsp�� �ƴ� json
	//��ȯ���� String�� �ƴ� vo���� ���, converter�� �����Ǿ�� �Ѵ�.
	//������ �������� jackson jar�ٿ�޾�..MessageConverter...����
	//������ spingboot�� �� ������ �ʿ���� �� ����� �� �����Ǿ��ִ�..
	public Board getDetail(@PathVariable int board_id) {	//@PathVariable -> ��ΰ� �ƴ϶� ������! 
		Board board = new Board();
		board.setBoard_id(board_id);
		board.setTitle("�� vo��");
		
		
		return board;
	}

}
