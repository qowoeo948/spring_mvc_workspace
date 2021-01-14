<%@page import="com.koreait.fashionshop.model.domain.Member"%>
<%@page import="com.koreait.fashionshop.model.domain.Paymethod"%>
<%@page import="com.koreait.fashionshop.model.common.Formatter"%>
<%@page import="com.koreait.fashionshop.model.domain.Cart"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<Cart> cartList = (List)request.getAttribute("cartList");
	List<Paymethod> paymethodList = (List)request.getAttribute("paymethodList");
	Member member=(Member)session.getAttribute("member");
	
	//장바구니로부터, 상품 가액 계산
	int totalPrice=0;
	for(Cart cart : cartList){
		totalPrice += (cart.getQuantity() * cart.getPrice());
	}
	
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
	<script>
	function setData(ch){
		var form=document.querySelector("#checkout-form");
		
		if(ch.checked){//체크를 했다면, 주문자 정보를 받는자 정보에 대입
			form.receiver_name.value=form.member_name.value;
			form.receiver_phone.value=form.member_phone.value;
			form.receiver_addr.value=form.member_addr.value;
		}else{
			//받는 사람 정보를 다시 원상 복구(초기화)
			form.receiver_name.value="";
			form.receiver_phone.value="";
			form.receiver_addr.value="";
		}
	}
	
	function order(){
		$("#checkout-form").attr({
			action:"/shop/payment/regist",
			method:"post"
		});
		$("#checkout-form").submit();
	}
	</script>
</head>

<body>
    	<%@include file="../inc/top.jsp" %>
        <!-- ****** Top Discount Area End ****** -->
		
        <!-- ****** Checkout Area Start ****** -->
        <div class="checkout_area section_padding_100">
            <div class="container">
                <div class="row">

                    <div class="col-12 col-md-6">
                        <div class="checkout_details_area mt-50 clearfix">

                            <div class="cart-page-heading">
                                <h5>Billing Address</h5>
                                <p>Enter your cupone code</p>
                            </div>

                            <form id="checkout-form">
                            	<input type="hidden" name="total_price" value="<%=totalPrice%>">
                            	<input type="hidden" name="total_pay" value="<%=totalPrice%>">
                            	
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="member_name">주문고객 명<span>*</span></label>
                                        <input type="text" class="form-control" id="member_name"  value="<%=member.getName() %>" required>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="member_phone">연락처 <span>*</span></label>
                                        <input type="text" class="form-control" id="member_phone" value="010-8888-9999" required>
                                    </div>
                                    <div class="col-12 mb-3">
                                        <label for="member_addr">주소</label>
                                        <input type="text" class="form-control" id="member_addr" value="<%=member.getAddr()%>">
                                    </div>
                                    
									<div class="col-12 mb-3">
	                                    <div class="custom-control custom-checkbox d-block mb-2">
	                                            <input type="checkbox" class="custom-control-input" id="customCheck1" onClick="setData(this)">
	                                            <label class="custom-control-label" for="customCheck1">주문자와 동일</label>
	                                    </div>
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label for="first_name">받으실분 이름<span>*</span></label>
                                        <input type="text" class="form-control" id="first_name" name="receiver_name" value="" required>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="last_name">연락처 <span>*</span></label>
                                        <input type="text" class="form-control" id="last_name" name="receiver_phone" value="" required>
                                    </div>
                                    <div class="col-12 mb-3">
                                        <label for="company">받으실 주소</label>
                                        <input type="text" class="form-control" id="company" name="receiver_addr" value="">
                                    </div>
                                    
                                    <div class="col-12 mb-3">
                                        <label for="country">결제방법 선택<span>*</span></label>
                                        <select class="custom-select d-block w-100" id="country" name="paymethod_id">
                                        <%for(Paymethod paymethod : paymethodList){ %>
                                        <option value="<%=paymethod.getPaymethod_id()%>"><%=paymethod.getMethod() %></option>
										<%} %>
                                    </select>
                                    </div>

                                    <div class="col-12">
                                        <div class="custom-control custom-checkbox d-block mb-2">
                                            <input type="checkbox" class="custom-control-input" id="customCheck1">
                                            <label class="custom-control-label" for="customCheck1">Terms and conitions</label>
                                        </div>
                                        <div class="custom-control custom-checkbox d-block mb-2">
                                            <input type="checkbox" class="custom-control-input" id="customCheck2">
                                            <label class="custom-control-label" for="customCheck2">Create an accout</label>
                                        </div>
                                        <div class="custom-control custom-checkbox d-block">
                                            <input type="checkbox" class="custom-control-input" id="customCheck3">
                                            <label class="custom-control-label" for="customCheck3">Subscribe to our newsletter</label>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="col-12 col-md-6 col-lg-5 ml-lg-auto">
                        <div class="order-details-confirmation">

                            <div class="cart-page-heading">
                                <h5>Your Order</h5>
                                <p>The Details</p>
                            </div>

                            <ul class="order-details-form mb-4">
                                <li><span>Product</span> <span>Total</span></li>
                                <li><span>청바지외 2건</span> <span>$59.90</span></li>
                                <li><span>할인쿠폰</span> <span>-$10.90</span></li>
                                <li><span>최종 결제하실 금액</span> <span>$49.90</span></li>
                            </ul>


                            

                            <a href="javascript:order()" class="btn karl-checkout-btn">Place Order</a>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- ****** Checkout Area End ****** -->		
        	
        <!-- ****** Footer Area Start ****** -->
        <%@ include file="../inc/footer.jsp" %>
        <!-- ****** Footer Area End ****** -->
    </div>
    <!-- /.wrapper end -->
</body>

</html>