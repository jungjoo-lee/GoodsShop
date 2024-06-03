<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="orderlistForm">
	<div>
		<input type="button" id="go_main">
	</div>
	<c:choose>
		<c:when test="${empty orderList}">
			<h3>텅~</h3>
		</c:when>
		<c:otherwise>
			<c:forEach items="${orderList}" var="ovo">
				<div id="orderlist">
					<div>주문번호 : ${ovo.oseq}</div>
					<div>주문상품 : ${ovo.gname} 포함 ${ovo.quantity} 개</div>
					<div>주문일시 : ${ovo.indate}</div>
					<div>주문금액 : ${ovo.totalprice}</div>
					<div>주문상태 : ${ovo.status}</div>
					
					<input type="hidden" name="oseq" value="${ovo.oseq}">
					<hr>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</form>	
<script type="text/javascript" src='<c:url value = "/resources/js/goods/orderlistview.js"/>'></script>
</body>
</html>