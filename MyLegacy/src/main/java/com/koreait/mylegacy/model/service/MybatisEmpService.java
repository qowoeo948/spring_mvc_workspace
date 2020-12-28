package com.koreait.mylegacy.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.mylegacy.model.dao.MybatisDeptDAO;
import com.koreait.mylegacy.model.dao.MybatisEmpDAO;
import com.koreait.mylegacy.model.domain.Emp;
import com.koreait.mylegacy.mybatis.config.MybatisConfigManager;

@Service
public class MybatisEmpService {
	@Autowired
	private MybatisConfigManager manager;
	@Autowired
	private MybatisEmpDAO mybatisEmpDAO;
	@Autowired
	private MybatisDeptDAO mybatisDeptDAO;
	
	//모든 사원 레코드 가져오기
	public List selectAll() {
		List list = null;
		SqlSession sqlSession = manager.getSqlSession();
		mybatisEmpDAO.setSqlSession(sqlSession);
		list = mybatisEmpDAO.selectAll();
		manager.close(sqlSession);
		return  list;
	}
	
	//사원등록(부서등록+사원등록 = 2개의 업무로 구성된트랜잭션 상황)
	public int regist(Emp emp) {
		int result=0;
		//일 시키기전에 sqlsession 배분!
		SqlSession sqlSession = manager.getSqlSession();	//default false
		
		mybatisEmpDAO.setSqlSession(sqlSession);
		mybatisDeptDAO.setSqlSession(sqlSession);
		
		mybatisEmpDAO.insert(emp);
		mybatisDeptDAO.insert(emp.getDept());
		
		manager.close(sqlSession);
		return result;
	}
	
}
