<%@page import="com.koreait.mvclegacy.model.domain.Notice"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Notice notice = (Notice)request.getAttribute("notice");

%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<script>
	function edit(){
		if(confirm("수정하시겠습니까?")){
			
		var form = document.querySelector("form");
		form.action="/client/notice/edit";
		form.method="post";
		form.submit();
		}
	}
	
	function del(){
		if(confirm("삭제하시겠습니까?")){
			
		var form = document.querySelector("form");
		form.action="/client/notice/del";
		form.method="post";
		form.submit();
		}
	}
	
</script>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form>
    <input type="hidden" name="notice_id" value="<%=notice.getNotice_id()%>">

    <input type="text" name="title" value="<%=notice.getTitle() %>">
    <input type="text" name="writer" value="<%=notice.getWriter() %>">
    <textarea name="content"  style="height:200px"><%=notice.getContent() %></textarea>

    <input type="button" value="글수정" onClick="edit()">
    <input type="button" value="글삭제" onClick="del()">
    <input type="button" value="목록보기" onClick="location.href='/client/notice/list'">
  </form>
</div>

</body>
</html>