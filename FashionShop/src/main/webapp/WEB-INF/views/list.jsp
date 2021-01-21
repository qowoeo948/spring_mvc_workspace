<%@page import="test.Hardware"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	List<Hardware> testList = (List)request.getAttribute("testList");
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>
</head>
<body>

<table>
	<%for(int i =0;i<testList.size();i++){%>
	<%Hardware hardware = testList.get(i); //각 칸에 들어간 vo끄집어 내기 %>
 		<tr>
			<td><%=hardware.getHardware_id() %></td>
			<td><%=hardware.getName() %></td>
			<td><%=hardware.getBrand() %></td>
			<td><%=hardware.getPrice() %></td>
		</tr>
  	<%}%>

</table>

</body>
</html>
