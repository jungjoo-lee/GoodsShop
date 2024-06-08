<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>wishlist view</title>
</head>
<body>
	<form name="wishlistform" method="post">
		<label> 전체 선택/해제</label>
		<input type="checkbox" id="checkAll">
		<c:forEach items="${wishlist}" var="cvo">
			<div>
				<div>
					<img src='<c:url value="/resources/image/goods/${cvo.thum}.png"/>'>
				</div>
				<div>상품명 : ${cvo.goodsname}</div>
				<div>판매가 : ${cvo.sprice} 원</div>
				<div>
					<input type="checkbox" id="checkboxes" name="gseq" value="${cvo.gseq}" />
				</div>
				<hr>
			</div>
		</c:forEach>

		<div>
			<input id="go_main" type="button" value="쇼핑 계속하기"> 
			<input id="wish_delete" type="button" value="선택상품 삭제">
			<input id="wish_to_cart" type="button" value="선택상품을 장바구니에 추가">
		</div>
		
	</form>

<script type="text/javascript" src='<c:url value="/resources/js/goods/wishlist.js"/>'></script>
</body>
</html>