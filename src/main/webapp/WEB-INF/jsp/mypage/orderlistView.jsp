<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<%@ include file="/WEB-INF/jsp/header.jsp"%>

	<c:choose>
		<c:when test="${empty orderList}">
			<h3>텅~</h3>
		</c:when>
		<c:otherwise>
			<c:forEach items="${orderList}" var="ovo">
				<div>주문번호 : ${ovo.oseq}</div>
				<div>주문상품 : ${ovo.gname} 포함 ${ovo.quantity} 개</div>
				<div>주문일시 : ${ovo.indate}</div>
				<div>주문금액 : ${ovo.totalprice}</div>
				<div>주문상태 : ${ovo.status}</div>
				<hr/>
			</c:forEach>
		</c:otherwise>

	</c:choose>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
