package com.koreait.mylegacy.model.dao;

import java.nio.ReadOnlyBufferException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.koreait.mylegacy.exception.RegistException;
import com.koreait.mylegacy.model.domain.Emp;

@Repository
public class MybatisEmpDAO {
	private SqlSession sqlSession=null;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//목록 가져오기 
	public List selectAll() {
		List list = null;
		list=sqlSession.selectList("Emp.selectAll");
		return list;
	}
	
	//1건 등록 
	public int insert(Emp emp) throws RegistException{
		int result=0;
		result = sqlSession.insert("Emp.insert", emp); //emp안에 dept가 포함!!
		result=0;//일부터 실패로 간주..
		if(result==0) {
			throw new RegistException("사원등록에 실패하였습니다");
		}
		return result;
	}
}