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
	
	<c:forEach items="${cartlist}" var="cvo">
		<div>
			<div> <img src='<c:url value="/resources/image/goods/${cvo.thum}.png"/>'> </div>
			<div> 상품명 : ${cvo.goodsname} </div>
			<div> 수량 : ${cvo.quantity} 개 </div>
			<div> 금액 : ${cvo.totalprice} </div>
			<input type="text">
			<hr>
		</div>
	</c:forEach>

</body>
</html>