<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>orderPage</title>
</head>
<body>

	<h1>주문페이지~~~</h1>

	<form method="post" name="orderpageform">
		<div>목록</div>

		<c:set var="numberOfGoods" value="0"></c:set>
		<c:set var="orderTotalPrice" value="0"></c:set>
		<c:forEach items="${orderProductList}" var="ovo" varStatus="ovostat">

			<div>
				<div>
					<img src='<c:url value="/resources/image/goods/${ovo.thum}.png"/>'>
				</div>
				<div>상품명 : ${ovo.gname}</div>
				<div> <input type="number" value="${ovo.quantity}" readonly="readonly"> </div>
				<div>금액 : ${ovo.totalprice} 원</div>
				<hr>
			</div>
			<c:set var="numberOfGoods" value="${numberOfGoods + ovo.quantity}"></c:set>
			<c:set var="orderTotalPrice"
				value="${orderTotalPrice + ovo.totalprice}"></c:set>
			<input type="hidden" name="gseq" value="${ovo.gseq}">
		</c:forEach>

		<div>총 ${numberOfGoods} 개의 상품, 총액 : ${orderTotalPrice}</div>
		
		<input type="button" id="go_order" value="주문하기">
		<input type="hidden" name="numberOfGoods" value="${numberOfGoods}">
		<input type="hidden" name="orderTotalPrice" value="${orderTotalPrice}">

	</form>
<script type="text/javascript" src='<c:url value = "/resources/js/goods/order.js"/>'></script>
</body>
</html>