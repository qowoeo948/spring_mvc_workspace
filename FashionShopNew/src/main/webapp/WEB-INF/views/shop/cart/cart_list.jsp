<%@page import="com.koreait.fashionshop.model.common.Formatter"%>
<%@page import="com.koreait.fashionshop.model.domain.Cart"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<Cart> cartList = (List)request.getAttribute("cartList");
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
	function delCart(){
		if(confirm("장바구니를 모두 비우시겠습니까?")){
			location.href="/shop/cart/del";
		}	
	}
	
	function editCart(){
		if(confirm("주문 수량을 변경하시겠어요?")){
			$("#cart-form").attr({
				action:"/shop/cart/edit",
				method:"post"
			});
			$("#cart-form").submit();
		}	
		
	}
	
	function checkoutForm(){
		location.href="/shop/payment/form";	
	}
	</script>
</head>

<body>
    	<%@include file="../inc/top.jsp" %>
        <!-- ****** Top Discount Area End ****** -->
		
        <!-- ****** Cart Area Start ****** -->
        <div class="cart_area section_padding_100 clearfix">
            <div class="container">
            	<form id="cart-form">
                <div class="row">
                    <div class="col-12">
                        <div class="cart-table clearfix">
                            <table class="table table-responsive">
                                <thead>
                                    <tr>
                                        <th>Product</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%int sum=0; //합계 %>
                                    <%for(Cart cart : cartList){ %>
                                    <tr>
                                        <td class="cart_product_img d-flex align-items-center">
                                            <a href="#"><img src="/resources/data/basic/<%=cart.getProduct_id() %>.<%=cart.getFilename()%>" alt="Product"></a>
                                            <h6><%=cart.getSubCategory().getName() %> > <%=cart.getProduct_name() %> </h6>
                                        </td>
                                        <td class="price"><span><%=Formatter.getCurrency(cart.getPrice()) %></span></td>
                                        <td class="qty">
                                            <div class="quantity">
                                                <span class="qty-minus" onclick="var effect = document.getElementById('qty'); var qty = effect.value; if( !isNaN( qty ) &amp;&amp; qty &gt; 1 ) effect.value--;return false;"><i class="fa fa-minus" aria-hidden="true"></i></span>
                                                <input type="hidden" name="cart_id" value="<%=cart.getCart_id()%>">
                                                <input type="number" name="quantity" class="qty-text" id="qty" step="1" min="1" max="99"  value="<%=cart.getQuantity()%>">
                                                <span class="qty-plus" onclick="var effect = document.getElementById('qty'); var qty = effect.value; if( !isNaN( qty )) effect.value++;return false;"><i class="fa fa-plus" aria-hidden="true"></i></span>
                                            </div>
                                        </td>
                                        <%
                                        	sum = sum + (cart.getPrice()*cart.getQuantity());
                                        %>
                                        <td class="total_price"><span><%=Formatter.getCurrency(cart.getPrice()*cart.getQuantity()) %></span></td>
                                    </tr>
                                    <%} %>
                                </tbody>
                            </table>
                        </div>

                        <div class="cart-footer d-flex mt-30">
                            <div class="back-to-shop w-50">
                                <a href="shop-grid-left-sidebar.html">Continue shooping</a>
                            </div>
                            <div class="update-checkout w-50 text-right">
                                <a href="javascript:delCart()">clear cart</a>
                                <a href="javascript:editCart()">Update cart</a>
                            </div>
                        </div>

                    </div>
                </div>
				</form>
				
                <div class="row">
                    <div class="col-12 col-md-6 col-lg-4">
                        <div class="coupon-code-area mt-70">
                            <div class="cart-page-heading">
                                <h5>Cupon code</h5>
                                <p>Enter your cupone code</p>
                            </div>
                            <form action="#">
                                <input type="search" name="search" placeholder="#569ab15">
                                <button type="submit">Apply</button>
                            </form>
                        </div>
                    </div>
                    <div class="col-12 col-md-6 col-lg-4">
                        <div class="shipping-method-area mt-70">
                            <div class="cart-page-heading">
                                <h5>Shipping method</h5>
                                <p>Select the one you want</p>
                            </div>

                            <div class="custom-control custom-radio mb-30">
                                <input type="radio" id="customRadio1" name="customRadio" class="custom-control-input">
                                <label class="custom-control-label d-flex align-items-center justify-content-between" for="customRadio1"><span>Next day delivery</span><span>$4.99</span></label>
                            </div>

                            <div class="custom-control custom-radio mb-30">
                                <input type="radio" id="customRadio2" name="customRadio" class="custom-control-input">
                                <label class="custom-control-label d-flex align-items-center justify-content-between" for="customRadio2"><span>Standard delivery</span><span>$1.99</span></label>
                            </div>

                            <div class="custom-control custom-radio">
                                <input type="radio" id="customRadio3" name="customRadio" class="custom-control-input">
                                <label class="custom-control-label d-flex align-items-center justify-content-between" for="customRadio3"><span>Personal Pickup</span><span>Free</span></label>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-lg-4">
                        <div class="cart-total-area mt-70">
                            <div class="cart-page-heading">
                                <h5>Cart total</h5>
                                <p>Final info</p>
                            </div>

                            <ul class="cart-total-chart">
                                <li><span>Subtotal</span> <span><%=Formatter.getCurrency(sum) %></span></li>
                                <li><span>Shipping</span> <span>Free</span></li>
                                <li><span><strong>Total</strong></span> <span><strong><%=Formatter.getCurrency(sum) %></strong></span></li>
                            </ul>
                            <a href="javascript:checkoutForm()" class="btn karl-checkout-btn">Proceed to checkout</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- ****** Cart Area End ****** -->		
        <!-- ****** Footer Area Start ****** -->
        <%@ include file="../inc/footer.jsp" %>
        <!-- ****** Footer Area End ****** -->
    </div>
    <!-- /.wrapper end -->
</body>

</html>