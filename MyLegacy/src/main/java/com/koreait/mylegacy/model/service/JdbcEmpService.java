package com.koreait.mylegacy.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.mylegacy.model.dao.JdbcDeptDAO;
import com.koreait.mylegacy.model.dao.JdbcEmpDAO;
import com.koreait.mylegacy.model.domain.Dept;
import com.koreait.mylegacy.model.domain.Emp;
import com.koreait.mylegacy.model.pool.PoolManager;

/* MVC에서  Model 영역의 서비스를 정의한다. 
 * 서비스는 직접 로직을 수행하지는 않으며, 모델영역의 각 업무를 수행하는 객체를 
 * 제어하는 역할, 만일 Service의 존재가 없다면, 컨트롤러가 모델 영역의 복잡한 처리를
 * 직접 해야하므로, 어플리케이션 설계상 영역 구분이 약해지게 된다.. */
@Service
public class JdbcEmpService {
	@Autowired
	private PoolManager poolManager;
	@Autowired
	private JdbcDeptDAO jdbcDeptDAO;
	@Autowired
	private JdbcEmpDAO jdbcEmpDAO;
	
	//글목록
	public List selectAll() {
		List list = null;
		Connection con = poolManager.getConnection();
		jdbcEmpDAO.setCon(con);
		list = jdbcEmpDAO.selectAll();
		poolManager.freeConnection(con);
		return list;
	}
	
	//한건 (부서, 사원)
	public Dept selectDept(int deptno) {
		Dept dept = null;
		return dept;
	}
	public Emp selectEmp(int empno) {
		Emp emp = null;
		return emp;
	}
	
	//사원등록(emp, dept의 트랜잭션 관계!!)
	public int regist(Emp emp) {
		int result = 0;
		Connection con = poolManager.getConnection();
		//DAO들에게 동일한 Connection을 배분!!
		jdbcDeptDAO.setCon(con);
		jdbcEmpDAO.setCon(con);
		
		try {
			con.setAutoCommit(false);//자동 커밋 방지
			jdbcDeptDAO.regist(emp.getDept());//부서등록
			jdbcEmpDAO.regist(emp);//사원등록 
			con.commit(); //위 두개의 insert 업무수행중 에러가 없다면 트랜잭션 커밋 
			result=1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback(); //에러가 하나라도 발생한다면 트랜잭션 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		poolManager.freeConnection(con);
		
		return result;
	}
	
	//수정
	public int updateDept(Dept dept) {
		int result = 0;
		return result;
	}
	public int updateEmp(Emp emp) {
		int result = 0;
		return result;
	}
	
	//삭제
	public int deleteDept(int deptno) {
		int result = 0;
		return result;
	}
	public int deleteEmp(int empno) {
		int result = 0;
		return result;
	}

}