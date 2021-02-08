package com.koreait.bootproject20210208.model.board.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.bootproject20210208.exception.BoardUpdateException;
import com.koreait.bootproject20210208.model.domain.Board;

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
		//result=0;	//�Ϻη� ���� �߻���Ű��
		if(result==0) {
			throw new BoardUpdateException("��� ����");
		}
	}

	@Override
	public void update(Board board) throws BoardUpdateException{
		int result = sqlSessionTemplate.update("Board.update",board);
		if(result==0) {
			throw new BoardUpdateException("���� ����");
		}
	}

	@Override
	public void delete(int board_id) throws BoardUpdateException{
		int result = sqlSessionTemplate.delete("Board.delete",board_id);
		if(result==0) {
			throw new BoardUpdateException("���� ����");
		}
	}

}
