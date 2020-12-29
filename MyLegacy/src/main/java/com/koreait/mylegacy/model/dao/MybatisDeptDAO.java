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
	
	//1�� ��� 
	public int insert(Dept dept) throws RegistException{
		int result=0;
		result = sqlSession.insert("Dept.insert", dept); //emp�ȿ� dept�� ����!!
		//����, �μ������ �����ϸ� ���⼭ ������ ���ܸ� �߻���Ű������!!
		if(result==0) {
			//�ڹٿ����� ������ ������ �߻������ִ� ����� ���� 
			throw new RegistException("�μ���Ͽ� �����Ͽ����ϴ�");
		}
		return result;
	}
}