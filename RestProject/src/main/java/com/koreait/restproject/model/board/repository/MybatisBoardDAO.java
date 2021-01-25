package com.koreait.restproject.model.board.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.restproject.exception.BoardUpdateException;
import com.koreait.restproject.model.domain.Board;

@Repository
public class MybatisBoardDAO implements BoardDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("Board.selectAll");
	}

	@Override
	public Board select(int board_id) {
		return sqlSessionTemplate.selectOne("Board.select",board_id);
	}

	@Override
	public void insert(Board board) throws BoardUpdateException{
		int result = sqlSessionTemplate.insert("Board.insert",board);
		//result=0;	//일부러 예외 발생시키기
		if(result==0) {
			throw new BoardUpdateException("등록 실패");
		}
	}

	@Override
	public void update(Board board) throws BoardUpdateException{
		int result = sqlSessionTemplate.update("Board.update",board);
		if(result==0) {
			throw new BoardUpdateException("수정 실패");
		}
	}

	@Override
	public void delete(int board_id) throws BoardUpdateException{
		int result = sqlSessionTemplate.delete("Board.delete",board_id);
		if(result==0) {
			throw new BoardUpdateException("삭제 실패");
		}
	}

}
