<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/goods.css'/>">
</head>
<body>

<div class="product-detail">

	<form method="post" name="goodsform">
		<input type="hidden" name="gseq" value="${goodsDetail.gseq}">
		<div class="detail-container">
			
			<div class="detail-imagebox">
				<div class="imgbox">
				<img alt="${goodsDetail.imageList[0].realname}"
					src="<c:url value='/gshop.do?command=imageWrite&folder=${goodsDetail.gseq}${goodsDetail.gname}&realName=${goodsDetail.imageList[0].realname}'/>">
				</div>
			</div>

			<div class="detail-infobox">
				<div class="detail-infotext">
					<div class="detail-title">${goodsDetail.gname}</div>
					<div class="detail-field">가격 : ${goodsDetail.sprice} 원</div>
					<div class="detail-field">
						수량 : <input id="input_quantity" name="input_quantity" type="number"
							min="1" value="1">
					</div>
					<div class="detail-field">
						${goodsDetail.content}
					</div>
				</div>

				<c:choose>
					<c:when test="${empty loginAdmin}">
						<div class="detail-btns">
							<input type="button" id="purchase_now" value="바로구매">
							<input type="button" id="add_cart" value="장바구니"> 
							<input type="button" id="add_wishlist" value="찜하기"> 
						</div>
					</c:when>
					<c:otherwise>
						<div class="detail-btns">
							<input type="button" id="update_goods" value="상품 수정"> 
							<input type="button" id="delete_goods" value="상품 삭제"> 
							<input type="button" id="go_goodslist" value="목록으로">
						</div>					
					</c:otherwise>
				</c:choose>
			</div>
		</div>


		<div class="detail-contentbox">
				<div></div>
				<div>
					<c:forEach items="${goodsDetail.imageList}" var="img">
						<div>
							<img src="<c:url value='/gshop.do?command=imageWrite&folder=${goodsDetail.gseq}${goodsDetail.gname}&realName=${img.realname}'/>">
						</div>
					</c:forEach>
				</div>
		</div>
	</form>
</div>
	<script type="text/javascript" src='<c:url value = "/resources/js/goods/detail.js"/>'></script>
</body>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</html>