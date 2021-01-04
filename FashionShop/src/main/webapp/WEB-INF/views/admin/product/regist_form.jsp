<%@page import="com.koreait.fashionshop.model.domain.TopCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	List<TopCategory> topList = (List)request.getAttribute("topList");
%>
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

/*드래드 관련*/
#dragArea{
	width:100%;
	height:300px;
	overflow:scroll;
	border:1px solid #ccc;
	float:left;
}
.dragBorder{
	background:#ffffff;
}

#dragArea > img{
	width:100px;
	
}

.box{
	width:100px;
	float:left;
	padding:5px;
}
.box > img{
	width:100%;
}

.close{
	color:red;
	cursor:pointer;
}

</style>
<script type="text/javascript">
	var uploadFiles=[]; 	//미리보기 이미지 목록
	var psizeArray=[]; //유저가 선택한 사이즈를 담년 배열

$(function(){
	CKEDITOR.replace("detail");	
	
	//상위카테고리 선택하면..
	$($("select")[0]).change(function(){
		//비동기 방식으로 서버에 요청하되, 순수 ajax보다는 jquery ajax 를 이용하자!!
		getSubList(this);
	});
	
	/*드래그 관련 이벤트*/
	$("#dragArea").on("dragenter",function(e){	//드래그로 영역에 진입했을 때,,
		$(this).append("dragenter<br>");
		$(this).addClass("dragBorder");
	});
	$("#dragArea").on("dragover",function(e){	//드래그영역 위에 있을 때..
		//$(this).append("dragover<br>");
		e.preventDefault();
	});
	$("#dragArea").on("drop",function(e){	//드래그영역 위에서 이미지를 떨구면..
		e.preventDefault();	//여타 다른 이벤트를 비활성화 시킨다.
		$(this).append("drop<br>");
		
		//자바스크립트로 drag된 이미지 정보를 구해와서, div영역에 미리 보기 효과...
		var fileList = e.originalEvent.dataTransfer.files;	//드래그한 파일들에 대한 배열 얻기
		console.log(fileList);
		
		//배열안에 들어있는 이미지들에 대한 미리보기 처리..
		for(var i=0;i<fileList.length;i++){
		//파일리스트를 일반 배열에 옮겨심기 (왜 심었나? 배열이 지원하는 여러 메서드들을 활용하기 위해) 파일배열은 기본배열이랑 달라서 기본배열에 심어넣어야돼
		uploadFiles.push(fileList[i]);
		preview(uploadFiles[i],i);	//파일 요소 하나를 넘기기 
		
		}
	});
	$("#dragArea").on("dragleave",function(e){	//드래그 영역에서 빠져나가면,,
		$(this).append("dragleave<br>");
		$(this).removeClass("dragBorder");
	});
	
	//이미지 삭제 이벤트 처리
	$("#dragArea").on("click",".close",function(e){
		console.log(e);
		
		//대상 요소 배열 삭제
		//삭제 전에 uploadFiles라는 배열에 들어있는 file의 index를 구하자!
		var f = uploadFiles[e.target.id];
		var index = uploadFiles.indexOf(f); //파일 객체가 몇 번째 들어있는지 추출		(재배치 되는거 다시 알려줘! 라는 뜻,실시간으로 삭제되어서 바뀐 index)
		
		//alert(e.target.id+"클릭했어?");
		uploadFiles.splice(index,1);
		
		//대상요소 삭제	(시각적으로 삭제)
		$(e.target).parent().remove();
		
		
	});
	
	//체크박스 이벤트 구현
	$("input[type='checkbox']").on("click",function(e){
		var ch = e.target; //이벤트 일으킨 주체 컴포넌트 즉 체크박스
		alert($(ch).val());
	});
	
	
});

//업로드 이미지 미리보기
function preview(file,index){
	//js로 이미지 미리보기를 구현하려면, 파일리더를 이용하면 된다. 	FileReader
	var reader = new FileReader();	//아직은 읽을 대상 파일이 결정되지 않음.
	
	//파일을 읽어들이면, 이벤트를 발생시킴
	reader.onload=function(e){
		console.log(e.currentTarget.result);
		
		var tag="<div class=\"box\">";
		tag+="<div class=\"close\" id=\""+index+"\">X</div>";
		tag+="<img src=\""+e.currentTarget.result+"\">";
		tag+="</div>";
		
		$("#dragArea").append(tag);
	};
	
	reader.readAsDataURL(file);		//지정한 파일을 읽는다.(매개변수로는 파일이 와야함)
	
	
	
}

//비동기 방식으로 하위 카테고리 요청하기!!
function getSubList(obj){
	//alert($(obj).val());
	
	$.ajax({
		url:"/admin/product/sublist", 
		type:"get",
		data:{
			topcategory_id:$(obj).val()			
		},
		success:function(result){
			//json형식으로 날라오게 돼 이젠
			//alert("서버로 부터 받은 결과는 "+result);
			
			//서버가 이미  json 으로 전송해주었으므로 별도의  parsing이 필요없다!!
			//기존 option태그를 먼저 지우자!!
			$($("select")[1]).empty();
			$($("select")[1]).append("<option>하위카테고리 선택</option>");
			
			//for(var i=0;i<result.subList.length;i++){	이건 기초 방식
			for(var i=0;i<result.length;i++){	//이건 spring을 이용한 방식	subList에서 가져올 필요가 없다
				//var subCategory = result.subList[i]; //이것도 기초방식 
				var subCategory = result[i]; //subcategory 1건에 대한 json 객체얻기!!
				$($("select")[1]).append("<option value=\""+subCategory.subcategory_id+"\">"+subCategory.name+"</option>");
			}
		}
	});
}
	
	//사이즈 선택시 배열 재구성 하기
	function setPsizeArray(){
		
	
	}
	
	
	function regist(){
		
		//$("textarea").val(CKEDITOR.instances.detail.getData());
	
		//비동기 전송시, 기존의 form을 이용할 수 있을까? yes!!!
		var formData = new FormData($("form")[0]);	//<form>태그와는 틀리다.. 전송할 때 파라미터들을 담을 수 있지만
															//이 자체가 폼태그는 아니다!!
		
		//미리보기 했던 이미지들은 파일컴포넌트화 되어있지 않기 때문에, 전송데이터에서 빠져있다.
		//따라서 기존 formData전송 전에, 동적으로 파일컴포넌트화 시켜 formData에 추가하자!!
		//java에서의 improved for문과 동일한 역할 (주로 컬렉션에서 객체를 꺼낼 때 편하게 사용..)
		$.each(uploadFiles,function(i,file){
			//<input type ="file" name="addImg"> 한 효과랑 똑같아			
			formData.append("addImg",file,file.name);	//아이디, 업로드할 파일객체, 파일명 순으로 온다.
			console.log(file.name);
		});
		
		//폼데이터에 에디터의 값 추가하기!!
		formData.append("detail",CKEDITOR.instances["detail"].getData());
			
		/*비동기 업로드*/
		$.ajax({
			url:"/admin/product/regist",
			data:formData,
			contentType:false,		//false일 경우 multipart/form-data로 간주하기로 했음
			processData:false,		//false일 경우 query-string으로 전송하지 않음 	contentType,processData = false -->2개가 세트임
			type:"post",
			success:function(result){
				alert(result);
			}
		});
	
		
		/* 동기방식 업로드
		$("form").attr({
			action:"/admin/product/regist",
			method:"post",
			enctype:"multipart/form-data"
		});		
		
		$("form").submit();
		
	*/
	}
</script>
</head>
<body>
<%@ include file="../inc/main_navi.jsp" %>

<h3>Contact Form</h3>
<div class="container">
  <form>
  	
  	<select>
  		<option>상위카테고리 선택</option>
  		<%for(TopCategory topCategory : topList){%>
  		<option value="<%=topCategory.getTopcategory_id()%>"><%=topCategory.getName() %></option>
  		<%} %>
  	</select>
  	
  	<select name="subcategory_id">
  		<option>하위카테고리 선택</option>
  	</select>
    <input type="text" name="product_name" placeholder="상품명" >
    <input type="text" name="price" placeholder="가격">
    <input type="text" name="brand" placeholder="브랜드">
	<!-- 파일 최대 4개까지 지원 -->
	<p>대표이미지: <input type="file" name="repImg"></p>
	
	<div id="dragArea"></div>

	<!-- 지원 사이즈 선택  -->
	<p>
		XS<input type="checkbox" name="psize[0].fit" value="XS">
		S<input type="checkbox" name="psize[1].fit" value="S">
		M<input type="checkbox" name="psize[2].fit" value="M">
		L<input type="checkbox" name="psize[3].fit" value="L">
		XL<input type="checkbox" name="psize[4].fit" value="XL">
		XXL<input type="checkbox" name="psize[5].fit" value="XXL">
	</p>
	
	<!-- 색상 -->
	<p>
		<input type="color" name="color[0].picker" value="#ccfefe">
		<input type="color" name="color[1].picker" value="#ffffff">
		<input type="color" name="color[2].picker" value="#000000">
		<input type="color" name="color[3].picker" value="#fdfdfd">
		<input type="color" name="color[4].picker" value="#0000ff">
		<input type="color" name="color[5].picker" value="#ff0000">
	</p>	
    
    <textarea id="detail" name="detail" placeholder="상세정보.." style="height:200px"></textarea>
    <input type="button" value="글등록" onClick="regist()">
    <input type="button" value="목록보기" onClick="location.href='/client/notice/list'">
  </form>
</div>

</body>
</html>