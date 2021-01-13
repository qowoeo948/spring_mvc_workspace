<%@page import="com.koreait.fashionshop.model.domain.Qna"%>
<%@page import="com.koreait.fashionshop.model.common.Pager"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Pager pager= (Pager)request.getAttribute("pager");	
	List<Qna> qnaList = pager.getList();
	out.print(pager);
	
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
	
	<style type="text/css">
	.container {
	  border-radius: 5px;
	  background-color: #f2f2f2;
	  padding: 20px;
	}
	
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
	
	.pageNum{
	font-size:15pt;
	color:red;
	font-weight:bold;
	
	</style>
	<script>
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
		   <%int num = pager.getNum(); %>
	  	  <%int curPos = pager.getCurPos(); %>
	  	  
		  <%for(int i=0;i<pager.getPageSize();i++){ %>
		  <%if(num<1) break; %>
		  <%Qna qna = qnaList.get(curPos++); %>
		  <tr>
		    <td><%=num-- %></td>
		    <td><%=qna.getWriter() %></td>
		    <td><%=qna.getTitle() %></td>
		    <td><%=qna.getRegdate() %></td>
		    <td><%=qna.getHit() %></td>
		  </tr>
		  <%} %>
		  <tr>
		<td colspan="5" style="text-align:center">

		<%if((pager.getFirstPage()-1)>=1){   //페이지가 있다면%> 
		<a href="/shop/cs/qna/list?currentPage=<%=pager.getFirstPage()-1%>">◀</a>
		<%}else{ %>
		<a href="javascript:alert('처음 페이지입니다');">◀</a>
		<%} %>
		
			<%for(int i=pager.getFirstPage();i<=pager.getLastPage();i++){ %>
				<%if(i>pager.getTotalPage()) break; //페이지를 출력하는i가 총페이지수에 도달하면 반복문 탈출 %>
			<a href="/shop/cs/qna/list?currentPage=<%=i%>" <%if(pager.getCurrentPage()==i){ %> class="pageNum" <%} %>>[<%=i %>]</a>
			
			<%} %>
			<%if((pager.getLastPage()+1)<pager.getTotalPage()){ %>
			<a href="/shop/cs/qna/list?currentPage=<%=pager.getLastPage()+1%>">▶</a>
			<%}else{ %>
				<a href="javascript:alert('마지막 페이지입니다');">▶</a>		
			<%} %>		
		</td>
	</tr>
		  
		  
		  
		  <tr>
		  	<td colspan="5" style="text-align:center">
		  		<button onClick="location.href='/shop/cs/qna/registForm';">글등록</button>
		  	</td>
		  </tr>
		</table>

	</div>	
        <!-- ****** Footer Area Start ****** -->
        <%@ include file="../inc/footer.jsp" %>
        <!-- ****** Footer Area End ****** -->
    <!-- /.wrapper end -->
</body>

</html>