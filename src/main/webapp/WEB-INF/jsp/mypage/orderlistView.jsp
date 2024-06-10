<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='/resources/css/listView.css'/>">
</head>
<body>
	<div class="view-container">
		<div class="container-title"> 주문내역 </div>


		<c:choose>
			<c:when test="${empty orderList}">
				<div>
					상품이 존재하지 않습니다.
				</div>
			</c:when>
			<c:otherwise>
				<div class="titlerow">
					<div class="titlefield">주문번호</div>
					<div class="titlefield">주문상품</div>
					<div class="titlefield">주문일시</div>
					<div class="titlefield">주문금액</div>
					<div class="titlefield">주문상태</div>
				</div>

				<ul class="listbox">			
					<c:forEach items="${orderList}" var="ovo">
						<a href="gshop.do?command=orderDetailView&oseq=${ovo.oseq}">
							<li class="list-row">
									<div class="listfield">${ovo.oseq}</div>
									<div class="listfield">${ovo.gname} 포함 ${ovo.quantity} 개</div>
									<div class="listfield">${ovo.indate}</div>
									<div class="listfield">${ovo.totalprice}</div>
									<div class="listfield">${ovo.status}</div>

									<input type="hidden" name="oseq" value="${ovo.oseq}">
							</li>
						</a>
					</c:forEach>
				</ul>
			</c:otherwise>
		</c:choose>
	</div>
	
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
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
