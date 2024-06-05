<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<%@ include file="/WEB-INF/jsp/header.jsp"%>

	<form method="post" name="paymentform">
		<div>
			<h3> Payment Page </h3>
		</div>
		

		<div>${orderProductList[0].gname} 포함 총 ${param.numberOfGoods} 개의 상품</div>


		<div> 상품 총액: ${param.orderTotalPrice}</div>
		
		<input id="get_payment" type="button" value="결제하기">
	</form>
<script type="text/javascript" src='<c:url value = "/resources/js/goods/payment.js"/>'></script>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
