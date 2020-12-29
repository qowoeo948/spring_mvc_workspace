package com.koreait.mylegacy.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.koreait.mylegacy.exception.RegistException;
import com.koreait.mylegacy.model.domain.Dept;

@Repository
public class MybatisDeptDAO {
	private SqlSession sqlSession=null;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//1건 등록 
	public int insert(Dept dept) throws RegistException{
		int result=0;
		result = sqlSession.insert("Dept.insert", dept); //emp안에 dept가 포함!!
		//만일, 부서등록이 실패하면 여기서 억지로 예외를 발생시키버리자!!
		if(result==0) {
			//자바에서는 에러를 억지로 발생시켜주는 기능이 지원 
			throw new RegistException("부서등록에 실패하였습니다");
		}
		return result;
	}
}