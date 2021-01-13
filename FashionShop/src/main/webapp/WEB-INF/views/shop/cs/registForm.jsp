<%@ page contentType="text/html;charset=utf-8"%>
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
		
		input[type=button:hover {
		  background-color: #45a049;
		}
		
		.container {
		  border-radius: 5px;
		  background-color: #f2f2f2;
		  padding: 20px;
		}
	
	</style>
<script type="text/javascript">
	$(function(){
	   $($("input[type='button']")[0]).click(function(){
	      regist();
	   });
	});
	   function regist() {
	      $("#qnaForm").attr({
	         action : "/shop/cs/qna/regist",
	         method : "post"
	      });
	      $("#qnaForm").submit();
	   }
</script>

</head>

<body>
    	<%@include file="../inc/top.jsp" %>
        <!-- ****** Top Discount Area End ****** -->
	<h3>Contact Form</h3>

	<div class="container">
	  <form id="qnaForm">
	    <input type="text" id="fname" name="title" placeholder="Your title">
	    <input type="text" id="lname" name="writer" placeholder="Your last writer">
	    <textarea id="subject" name="content" placeholder="content.." style="height:200px"></textarea>
	    <input type="button" value="button">
	  </form>
	</div>
        <!-- ****** Footer Area Start ****** -->
        <%@ include file="../inc/footer.jsp" %>
        <!-- ****** Footer Area End ****** -->
    <!-- /.wrapper end -->
</body>

</html>