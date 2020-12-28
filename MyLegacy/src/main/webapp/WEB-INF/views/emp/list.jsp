<%@page import="com.koreait.mylegacy.model.domain.Emp"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	List<Emp> empList = (List)request.getAttribute("empList");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원목록</title>
<style>
</style>
<script>
</script>
</head>
<body>
	<table width="100%" border="1px">
		<tr>
			<td>부서번호</td>
			<td>부서명</td>
			<td>부서위치</td>
			<td>사원번호</td>
			<td>사원명</td>
			<td>급여</td>
		</tr>
		<%for(int i=0;i<empList.size();i++){ %>
		<%Emp emp = empList.get(i); %>
		<tr>
			<td><%=emp.getDept().getDeptno() %></td>
			<td><%=emp.getDept().getDname() %></td>
			<td><%=emp.getDept().getLoc() %></td>
			<td><%=emp.getEmpno() %></td>
			<td><%=emp.getEname() %></td>
			<td><%=emp.getSal() %></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="6">
			<button onclick="location.href='/emp/registform';">사원등록</button>
			</td>
		</tr>
	</table>
</body>
</html>