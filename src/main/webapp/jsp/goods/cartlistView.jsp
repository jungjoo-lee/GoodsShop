<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cartlistView</title>
</head>
<body>
	<form name="cartlistform" method="post">

		<c:choose>
			<c:when test="${empty cartlist}">
				<h1>텅~</h1>
			</c:when>
			<c:otherwise>
				<c:forEach items="${cartlist}" var="cvo">
					<div>
						<div>
							<img
								src='<c:url value="/resources/image/goods/${cvo.thum}.png"/>'>
						</div>
						<div>상품명 : ${cvo.goodsname}</div>
						<div>수량 : ${cvo.quantity} 개</div>
						<div>금액 : ${cvo.totalprice} 원</div>
						<div>
							<input type="checkbox" name="gseq" value="${cvo.gseq}" /> 
							<input type="hidden" name="quantity" value="${cvo.quantity}">
						</div>
						<hr>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>

		<div>
			<input id="go_main" type="button" value="메인으로"> 
			<input id="cart_delete" type="button" value="선택상품 삭제">
			<input id="cart_order" type="button" value="선택상품 주문">
		</div>
	</form>
<script type="text/javascript" src='<c:url value="/resources/js/goods/cartlist.js"/>'></script>
</body>
</html>