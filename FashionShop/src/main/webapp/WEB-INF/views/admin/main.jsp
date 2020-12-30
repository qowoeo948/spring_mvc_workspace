<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="./inc/header.jsp" %>
<style>
</style>
</head>
<body>
<!-- 메인 네비게이션 (main_navi.jsp) 영역 : 상대경로를 이용하자 jsp는 이제 보안된 폴더에 있으므로
	웹사이트 루트를 기준으로 한 경로는 막혀있다. 따라서 상대경로로 가자. main.jsp를 기준으로 같은 경로에 있는 ./inc/main_navi.jsp-->
<%@ include file="./inc/main_navi.jsp" %>

<h3>Dropdown Menu inside a Navigation Bar</h3>
<p>Hover over the "Dropdown" link to see the dropdown menu.</p>

</body>
</html>