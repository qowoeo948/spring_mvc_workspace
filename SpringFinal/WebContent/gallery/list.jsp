<%@page import="com.study.springfinal.common.Pager"%>
<%@page import="com.study.springfinal.domain.Gallery"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
		//포워딩을 통해 넘겨받은 request객체에 담겨진 데이터 꺼내기
		List<Gallery> galleryList = (List)request.getAttribute("galleryList");
		out.print("게시물 수는: "+galleryList.size());
		Pager pager = new Pager();
		pager.init(request,galleryList);	//페이지에 대한 계산
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

<h2>회원 목록</h2>

<table>
  <tr>
    <th>No</th>
    <th>이미지</th>
    <th>제목</th>
    <th>작성자</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
	<%
	int curPos = pager.getCurPos();
	int num = pager.getNum();
	%>
	<%for(int i=1;i<=pager.getPageSize();i++){ %>
    <%if(num<1)break; %>
    <%Gallery gallery=galleryList.get(curPos++); %>
   <tr>
	<td><%=num-- %></td>
	<td><img src="/data/<%=gallery.getFilename()%>" width="50px"></td>
	<td><a href="/gallery/detail?gallery_id=<%=gallery.getGallery_id()%>"><%=gallery.getTitle() %></a></td>
	<td><%=gallery.getWriter() %></td>
	<td><%=gallery.getRegdate().substring(0,10) %></td>
	<td><%=gallery.getHit() %></td>
  </tr>
  <%} %>
  
  <tr>
    <td colspan="6" style="text-align:center">
    	[1][2][3]
    </td>
  </tr>
    <tr>
    <td colspan="6" style="text-align:center">
		<button onClick="location.href='regist_form.jsp'">글등록</button>
    </td>
  </tr>
</table>