<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문목록</title>
</head>
<body>

	<div>
		<input type="button" id="go_main">
	</div>
	<c:choose>
		<c:when test="${empty orderList}">
			<h3>텅~</h3>
		</c:when>
		<c:otherwise>
			<c:forEach items="${orderList}" var="ovo">
				<a href="gshop.do?command=orderDetailView&oseq=${ovo.oseq}">				
					<div id="orderlist">
					<div>주문번호 : ${ovo.oseq}</div>
					<div>주문상품 : ${ovo.gname} 포함 ${ovo.quantity} 개</div>
					<div>주문일시 : ${ovo.indate}</div>
					<div>주문금액 : ${ovo.totalprice}</div>
					<div>주문상태 : ${ovo.status}</div>

					<input type="hidden" name="oseq" value="${ovo.oseq}">
					<hr>
				</div>
				</a>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	
	<ul class="pagination justify-content-center" id="pagination">
		<!-- 이전 버튼 -->
		<c:choose>
			<c:when test="${paging.prev}">
				<li class="page-item"><a class="page-link" data-value="prev">Prev</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="page-item disabled"><a class="page-link">Prev</a></li>
			</c:otherwise>
		</c:choose>
		<!-- 페이지 번호 -->
		<c:forEach var="num" begin="${paging.beginPage}"
			end="${paging.endPage}">

			<c:if test="${num == paging.page}">
				<li class="page-item active"><a class="page-link"
					href="gshop.do?command=viewOrderList&page=${num}"
					data-value="${num}"> ${num} </a></li>
			</c:if>
			<c:if test="${num != paging.page}">
				<li class="page-item"><a class="page-link"
					href="gshop.do?command=viewOrderList&page=${num}"
					data-value="${num}"> ${num} </a></li>
			</c:if>
		</c:forEach>
		<!-- 다음 버튼 -->
		<c:choose>
			<c:when test="${paging.next}">
				<li class="page-item"><a class="page-link" data-value="next">Next</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="page-item disabled"><a class="page-link">Next</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
	<script type="text/javascript"
		src='<c:url value = "/resources/js/goods/orderlistview.js"/>'></script>
</body>
</html>