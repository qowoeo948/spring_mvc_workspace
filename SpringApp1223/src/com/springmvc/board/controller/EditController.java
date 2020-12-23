package com.springmvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;
import com.model2.domain.Board;

public class EditController implements Controller{
	BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String board_id = request.getParameter("board_id");
		
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		board.setBoard_id(Integer.parseInt(board_id));
		
		int result = boardDAO.update(board);
		
		ModelAndView mav = new ModelAndView();
		
		if(result==0) {
			mav.addObject("msg","��������");
			mav.setViewName("error/result");
		}else {
			//������ jsp�� �����带 ���ϴ� ���
			//mav.setViewName("board/detail");
			
			//��Ʈ�ѷ��� �ѹ� ���ļ� �� ���
			mav.setViewName("redirect:/board/detail?board_id="+board.getBoard_id());
		}
		
		return mav;
	}

}
