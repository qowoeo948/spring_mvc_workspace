<%@page import="com.koreait.fashionshop.model.domain.Qna"%>
<%@page import="com.koreait.fashionshop.model.common.Pager"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	Pager pager = (Pager)request.getAttribute("pager");
	List<Qna> qnaList = pager.getList();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>Karl - Fashion Ecommerce Template | Home</title>
	<%@ include file="../inc/header.jsp" %>
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
	.container {
	  border-radius: 5px;
	  background-color: #f2f2f2;
	  padding: 20px;
	}
	</style>	
	<script>
		function login(){
			$("#loginForm").attr({
				action:"/shop/member/login",
				method:"post"
			});		
			
			$("#loginForm").submit();
		}
	</script>		
</head>

<body>
    	<%@include file="../inc/top.jsp" %>
        <!-- ****** Top Discount Area End ****** -->
		<div class="container">   
			<table>
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일</th>
					<th>조회수</th>
				</tr>
				<%for(int i=0;i<qnaList.size();i++){ %>
				<%Qna qna = qnaList.get(i); %>
				<tr>
					<td>Jill</td>
					<td><%=qna.getTitle() %></td>
					<td><%=qna.getWriter() %></td>
					<td><%=qna.getRegdate()%></td>
					<td><%=qna.getHit() %></td> 
				</tr>
				<%}%>
				<tr>
					<td colspan="5">
						<button onClick="location.href='/shop/cs/qna/registForm';">글등록</button>
					</td>
				</tr>
			</table>
		</div>	
        <!-- ****** Footer Area Start ****** -->
        <%@ include file="../inc/footer.jsp" %>
        <!-- ****** Footer Area End ****** -->
    </div>
    <!-- /.wrapper end -->
</body>

</html>