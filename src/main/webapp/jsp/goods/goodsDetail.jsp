<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
</head>
<body>

	<h1>${goodsDetail.gname}</h1>

	<form method="post" name="goodsform">
		<input type="hidden" name="gseq" value="${goodsDetail.gseq}">
		<div>
			<div>
				<img alt="${goodsDetail.thum}.png"
					src='<c:url value = "/resources/image/goods/${goodsDetail.thum}.png"/>'>
			</div>

			<div>
				<div>가격 : ${goodsDetail.sprice} 원</div>
				<div>
					수량 : <input id="input_quantity" name="input_quantity" type="number"
						min="1" value="1">
				</div>
			</div>

			<c:choose>
				<c:when test="${empty loginAdmin}">
					<div>
						<div>
							<input type="button" id="add_cart" value="장바구니에 추가"> <input
								type="button" id="add_wishlist" value="찜하기"> <input
								type="button" id="purchase_now" value="바로 주문하기"> <input
								type="button" id="go_main" value="메인으로">
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<div>
							<input type="button" id="update_goods" value="상품 수정"> 
							<input type="button" id="delete_goods" value="상품 삭제"> 
							<input type="button" id="go_goodslist" value="목록으로">
						</div>
					</div>					
				</c:otherwise>
			</c:choose>


			<div>
				<div>${goodsDetail.content}</div>
				<div>
					<c:forEach items="${goodsDetail.imageList}" var="img">
						<div>
							<img
								src='<c:url value = "/resources/image/goods/${img.realname}.png"/>'>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</form>
	<div>
		
	</div>
	<script type="text/javascript" src='<c:url value = "/resources/js/goods/detail.js"/>'></script>
</body>
</html>