package com.koreait.bootproject20210208.model.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.bootproject20210208.exception.BoardUpdateException;
import com.koreait.bootproject20210208.model.board.repository.BoardDAO;
import com.koreait.bootproject20210208.model.domain.Board;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;
		
	@Override
	public List selectAll() {
		return boardDAO.selectAll();
	}

	@Override
	public Board select(int board_id) {
		return boardDAO.select(board_id);
	}

	@Override
	public void regist(Board board) throws BoardUpdateException{
		boardDAO.insert(board);
	}

	@Override
	public void update(Board board) throws BoardUpdateException{
		boardDAO.update(board);
	}

	@Override
	public void delete(int board_id) throws BoardUpdateException{
		boardDAO.delete(board_id);
	}

}
