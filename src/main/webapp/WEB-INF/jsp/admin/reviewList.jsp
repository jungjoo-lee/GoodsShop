<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
</head>
<body>

<c:forEach var="rev" items="${reviewList}">
	<div>${rev.rseq}, ${rev.grade}, ${rev.userid}, ${rev.subject}, ${rev.content}, ${rev.indate}</div>
</c:forEach>
<nav>
	<ul class="pagination justify-content-center" id="pagination">
  	<!-- 이전 버튼 -->
  	<c:choose>
  		<c:when test="${paging.prev}">
  			<li class="page-item">
  				<a class="page-link" data-value="prev">Prev</a>
  			</li>
  		</c:when>
  		<c:otherwise>
  			<li class="page-item disabled">
  				<a class="page-link">Prev</a>
  			</li>
  		</c:otherwise>
  	</c:choose>
  	<!-- 페이지 번호 -->
  	<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
  		<c:if test="${num == paging.currentPage}">
  			<li class="page-item active"><a class="page-link" data-value="${num}">${num}</a></li>
  		</c:if>
  		<c:if test="${num != paging.currentPage}">
  			<li class="page-item"><a class="page-link" data-value="${num}">${num}</a></li>
  		</c:if>
  	</c:forEach>
    <!-- 다음 버튼 -->
    <c:choose>
  		<c:when test="${paging.next}">
  			<li class="page-item">
  				<a class="page-link" data-value="next">Next</a>
  			</li>
  		</c:when>
  		<c:otherwise>
  			<li class="page-item disabled">
  				<a class="page-link">Next</a>
  			</li>
  		</c:otherwise>
  	</c:choose>
  	</ul>
</nav>
<script type="text/javascript" src="<c:url value='/resources/js/admin/review.js'/>"></script>
</body>
</html>