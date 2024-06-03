<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order</title>
</head>
<body>
	<div> 주문자 정보 </div>
	<div>
		<div> 주문번호 : ${ovo.oseq}</div>
		<div>주문자성명: ${ovo.name}</div>
		<div>주문주소 : ${ovo.address} ${ovo.d_address}</div>
		<div>주문일시 : ${ovo.indate}</div>
		<div>주문상태 : ${ovo.status}</div>
	</div>
	
	<div> 주문 상품 정보 </div>
	<c:choose>
			<c:when test="${empty orderDetailList}">
				<h1>텅~</h1>
			</c:when>
			<c:otherwise>
				<c:forEach items="${orderDetailList}" var="ovo">
					<div>
						<div>
							<img
								src='<c:url value="/resources/image/goods/${ovo.thum}.png"/>'>
						</div>
						<div>상품별 주문번호 : ${ovo.odseq}</div>
						<div>상품명 : ${ovo.goodsname}</div>
						<div>수량 : ${ovo.quantity} 개</div>
						<div>금액 : ${ovo.totalprice} 원</div>
						<hr>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		

</body>
</html>