<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ include file="/WEB-INF/jsp/header.jsp"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='/resources/css/listView.css'/>">
</head>
<body>
	<div class="view-container">
		<form method="post" name="paymentform">
			<div class="container-title"> 주문하기 </div>
			
	
			<div>${orderProductList[0].gname} 포함 총 ${param.numberOfGoods} 개의 상품</div>
	
	
			<div> 상품 총액: <fmt:formatNumber type="currency" value="${param.orderTotalPrice}"></fmt:formatNumber></div>
			
			<div class="pay-button">
			<input id="get_payment" type="button" value="결제하기">
			</div>
		</form>
	</div>
<script type="text/javascript" src='<c:url value = "/resources/js/goods/payment.js"/>'></script>
</body>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</html>