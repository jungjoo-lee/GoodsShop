<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>      
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order</title>
</head>
<body>
	<hr>
	<div> 주문자 정보 </div>
	<div>
		<div> 주문번호 : ${orderDetailList[0].oseq}</div>
 		<div>주문자성명: ${orderDetailList[0].name}</div>
		<div>주문주소 : ${orderDetailList[0].address} ${orderDetailList[0].daddress}</div>
		<div>주문일시 : ${orderDetailList[0].indate}</div>
		<div>주문상태 : ${orderDetailList[0].status}</div> 
	</div>

	<hr>	
	<div> 주문 상품 정보 </div>
	<c:choose>
			<c:when test="${empty orderDetailList}">
				<h1>텅~</h1>
			</c:when>
			<c:otherwise>
				<c:forEach items="${orderDetailList}" var="ovo">
					<div>
						<div>
							<img class="thumbnail" src="<c:url value='/gshop.do?command=imageWrite&folder=${ovo.gseq}${ovo.gname}&realName=${ovo.realname}'/>">
						</div>
						<div>상품별 주문번호 : ${ovo.odseq}</div>
						<div>상품명 : ${ovo.gname}</div>
						<div>수량 : ${ovo.quantity} 개</div>
						<div>금액 : ${ovo.totalprice} 원</div>
						<hr>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		

</body>
</html>