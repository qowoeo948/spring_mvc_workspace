<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../inc/header.jsp" %>
<style>
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
	$(function() {
		 CKEDITOR.replace( 'detail' );
	});

</script>
</head>
<body>
<%@ include file="../inc/main_navi.jsp" %>

<h3>Contact Form</h3>

<div class="container">
  <form>
  	<select>
  		<option>상위 카테고리 선택</option>
  	</select>
  	
  	<select>
  		<option>하위 카테고리 선택</option>
  	</select>
  	
    <input type="text" name="title" placeholder="상품명">
    <input type="text" name="writer" placeholder="가격">
    <input type="text" name="writer" placeholder="브랜드">
    
     <!-- 파일 최대 4개까지 지원 -->
    <!-- 대표 이미지 -->
   <p> 대표이미지: <input type="file"></p>
    <!--추가 이미지 -->
   <p> 추가이미지: <input type="file"></p>
   <p> 추가이미지: <input type="file"></p>
   <p> 추가이미지: <input type="file"></p>
   <p> 추가이미지: <input type="file"></p>
   
   <!-- 지원 사이즈 선택 -->
   <p>
   		XS<input type="checkbox">
   		S<input type="checkbox">
   		M<input type="checkbox">
   		L<input type="checkbox">
   		XL<input type="checkbox">
   		XXL<input type="checkbox">
   </p>
   
   <!-- COLOR -->
	<p>
		컬러 피커를 가져올 예정
	</p>
    
    <textarea id="detail" name="detail" placeholder="상세 정보" style="height:200px"></textarea>
   
    <input type="button" value="글등록" onClick="regist()">
    <input type="button" value="목록보기" onClick="location.href='/client/notice/list'">
  </form>
</div>

</body>
</html>