package com.koreait.restproject.rest.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.koreait.restproject.exception.BoardUpdateException;
import com.koreait.restproject.message.Message;
import com.koreait.restproject.model.board.service.BoardService;
import com.koreait.restproject.model.domain.Board;
import com.koreait.restproject.rest.websocket.MyWebSocketHandler;
import com.koreait.restproject.rest.websocket.SocketMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController	//restful�� url�� �����ϴ� ��Ʈ�ѷ�, ���� @ResponseBody�� �̹� ó��
public class BoardController {
	@Autowired 
	private BoardService boardService;
	
	@Autowired
	private MyWebSocketHandler myWebSocketHandler;
	
	Gson gson = new Gson();
	
	//��ϰ������� ��û
	@GetMapping("/board")
	//�̹� ResponseBody�� ����� �����̹Ƿ�, �����͸� ����س��Ҵٸ�, List�� �ڵ����� Json���� ��ȯ�Ǿ� Ŭ���̾�Ʈ�� ���� ������ ����..
	public List<Board> getList() {
		List boardList = boardService.selectAll();
		return boardList;
	}
	
	//�Ѱ� �������� ��û , ���/pk		,	@PathVariable�� ���ĸ� ~~? {board_id}�̰� ���͸� ��ΰ� �ƴ϶� ������ �����ض�
	@GetMapping("/board/{board_id}")
	public Board getDetail(@PathVariable int board_id) {
		log.debug("Ŭ���̾�Ʈ�� ���� �Ķ���ʹ� "+board_id);
		Board board = boardService.select(board_id);
		
		return board;
	}
	
	//��� ��û
	@PostMapping("/board")
	//@RequestBody: Ŭ���̾�Ʈ�� ������ json�����͸� �ڹ��� ��ü�� ��ȯ (json --> java obj)
	public ResponseEntity<Board> regist(@RequestBody Board board) {
		log.debug("title is "+board.getTitle());
		log.debug("writer is "+board.getWriter());
		log.debug("content is "+board.getContent());
		
		boardService.regist(board);
		
		//�������� �̿��� ��ε�ĳ��Ʈ!!
		SocketMessage socketMessage = new SocketMessage();
		socketMessage.setRequestCode("create");
		socketMessage.setResultCode(200);
		socketMessage.setMsg("��ϼ���");
		
		String jsonString = gson.toJson(socketMessage);	//��ü�� json����

		myWebSocketHandler.broadCast(jsonString);	//�ƹ��ų� ������ ������ but Ŭ���̾�Ʈ�� ������ ��ӵȰ� ��������
		
		//ResponseEntity --> ����� �ٵ� ���� �� �ִ�
		return ResponseEntity.ok().body(board);		//board_id�� �̹� ä���� vo  (mapper���� selectkey�� �Ѱ�)
	}
	
	//���� ��û
	@PutMapping("/board")
	public ResponseEntity<Board> update(@RequestBody Board board){
		log.debug("board_id is "+board.getBoard_id());
		log.debug("title is "+board.getTitle());
		log.debug("writer is "+board.getWriter());
		log.debug("content is "+board.getContent());
		
		boardService.update(board);
		
		//�������� �̿��� ��ε�ĳ��Ʈ!!
		SocketMessage socketMessage = new SocketMessage();
		socketMessage.setRequestCode("update");
		socketMessage.setResultCode(200);
		socketMessage.setMsg("��������");
						
		String jsonString = gson.toJson(socketMessage);	//��ü�� json����

		myWebSocketHandler.broadCast(jsonString);	//�ƹ��ų� ������ ������ but Ŭ���̾�Ʈ�� ������ ��ӵȰ� ��������
		
		return ResponseEntity.ok().body(board);	
	}
	
	
	//���� ��û 
	@DeleteMapping("/board/{board_id}")
	public ResponseEntity<Message> delete(@PathVariable int board_id) {
		boardService.delete(board_id);
		Message message = new Message();
		message.setMsg("�Խù� ���� ����");
		
		//�������� �̿��� ��ε�ĳ��Ʈ!!
		SocketMessage socketMessage = new SocketMessage();
		socketMessage.setRequestCode("delete");
		socketMessage.setResultCode(200);
		socketMessage.setMsg("��������");
				
		String jsonString = gson.toJson(socketMessage);	//��ü�� json����

		myWebSocketHandler.broadCast(jsonString);	//�ƹ��ų� ������ ������ but Ŭ���̾�Ʈ�� ������ ��ӵȰ� ��������
		
		
		return ResponseEntity.ok().body(message);
	}
	
	
	
	

	
}
