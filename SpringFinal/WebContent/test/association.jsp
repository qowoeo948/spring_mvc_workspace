<%@page import="com.study.springfinal.domain.Emp"%>
<%@page import="com.study.springfinal.domain.Dept"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.study.springfinal.mybatis.config.MybatisConfigManager"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//데이터베이스가 제대로 연동됐는지 확인하는 거 /test/contest.jsp ㄱㄱ
	//InitialContext context = new InitialContext();
	//DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	//out.print(ds.getConnection());
%>
<%!
	MybatisConfigManager manager = MybatisConfigManager.getInstance();
%>
<%
	//service메서드 영역
	SqlSession sqlSession = manager.getSqlSession();
	List<Emp> list = sqlSession.selectList("Emp.selectAll");
	out.print("검색된 총 사원수: "+list.size());
%>
