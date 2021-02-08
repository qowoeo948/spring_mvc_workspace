package com.koreait.bootproject20210208.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.bootproject20210208.model.board.service.BoardService;
import com.koreait.bootproject20210208.model.domain.Board;
import com.koreait.bootproject20210208.test.Dog;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private Dog dog;
	
	@GetMapping("/rest/test")
	public ResponseEntity<List<Board>> getTest() {		//처음엔 반환형을 String으로 주면 뷰를 jsp로 생각하기떄문에 @ResponseBody, @RestController로 바꾸기 
											//바꾸면 json이다..
		log.debug("목록을 원해?");
		List<Board> boardList = new ArrayList<Board>();
		Board board = new Board();
		board.setBoard_id(1);
		board.setTitle(dog.getName());
		board.setWriter("홍길동");
		board.setContent("냉무");
		board.setRegdate("2021-02-18");
		board.setHit(25);
		boardList.add(board);
		//만일 컨버터가 없었다면? Gson이용 .. jsonString 만들어서 출력, but 컨버터 이용하면 이런 과정조차 불필요
		
		return ResponseEntity.ok().body(boardList);
	}
	
	@GetMapping("/rest/board")
	public ResponseEntity<List<Board>> getList(){
		List<Board> boardList = boardService.selectAll();
		
		return ResponseEntity.ok().body(boardList);
	}
	

}
