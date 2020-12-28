package com.koreait.mylegacy.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.koreait.mylegacy.model.domain.Emp;

@Repository
public class MybatisEmpDAO {
	private SqlSession sqlSession =null;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//��� ��������
	public List selectAll() {
		List list = null;
		list = sqlSession.selectList("Emp.selectAll");
		
		return list;
	}
	
	//1�� ���
	public int insert(Emp emp) {
		int result =0;
		result = sqlSession.insert("Emp.insert",emp); //emp�ȿ� dept�� ���ԵǾ��ִ�.
		return result;
	}
	
}
