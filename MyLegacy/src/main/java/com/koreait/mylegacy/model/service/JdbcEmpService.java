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

/* MVC����  Model ������ ���񽺸� �����Ѵ�. 
 * ���񽺴� ���� ������ ���������� ������, �𵨿����� �� ������ �����ϴ� ��ü�� 
 * �����ϴ� ����, ���� Service�� ���簡 ���ٸ�, ��Ʈ�ѷ��� �� ������ ������ ó����
 * ���� �ؾ��ϹǷ�, ���ø����̼� ����� ���� ������ �������� �ȴ�.. */
@Service
public class JdbcEmpService {
	@Autowired
	private PoolManager poolManager;
	@Autowired
	private JdbcDeptDAO jdbcDeptDAO;
	@Autowired
	private JdbcEmpDAO jdbcEmpDAO;
	
	//�۸��
	public List selectAll() {
		List list = null;
		Connection con = poolManager.getConnection();
		jdbcEmpDAO.setCon(con);
		list = jdbcEmpDAO.selectAll();
		poolManager.freeConnection(con);
		return list;
	}
	
	//�Ѱ� (�μ�, ���)
	public Dept selectDept(int deptno) {
		Dept dept = null;
		return dept;
	}
	public Emp selectEmp(int empno) {
		Emp emp = null;
		return emp;
	}
	
	//������(emp, dept�� Ʈ����� ����!!)
	public int regist(Emp emp) {
		int result = 0;
		Connection con = poolManager.getConnection();
		//DAO�鿡�� ������ Connection�� ���!!
		jdbcDeptDAO.setCon(con);
		jdbcEmpDAO.setCon(con);
		
		try {
			con.setAutoCommit(false);//�ڵ� Ŀ�� ����
			jdbcDeptDAO.regist(emp.getDept());//�μ����
			jdbcEmpDAO.regist(emp);//������ 
			con.commit(); //�� �ΰ��� insert ���������� ������ ���ٸ� Ʈ����� Ŀ�� 
			result=1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback(); //������ �ϳ��� �߻��Ѵٸ� Ʈ����� �ѹ�
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
	
	//����
	public int updateDept(Dept dept) {
		int result = 0;
		return result;
	}
	public int updateEmp(Emp emp) {
		int result = 0;
		return result;
	}
	
	//����
	public int deleteDept(int deptno) {
		int result = 0;
		return result;
	}
	public int deleteEmp(int empno) {
		int result = 0;
		return result;
	}

}