<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>category</title>
</head>
<body>
	<c:forEach items="${categoryList}" var="categoryList">
			<div>				
				<div>
					<a href="gshop.do?command=goodsDetailView&gseq=${categoryList.gseq}">
						<img src="<c:url value='/gshop.do?command=imageWrite&folder=${categoryList.gseq}${categoryList.gname}&realname=${categoryList.realname}'/>">
					</a>
					<a href="gshop.do?command=goodsDetailView&gseq=${categoryList.gseq}">
						${categoryList.gname} - ${categoryList.sprice}					
					</a>
				</div>
			</div>
	</c:forEach>
	
	<ul class="pagination justify-content-center" id="pagination">
		<!-- 이전 버튼 -->
		<c:if test="${paging.prev}">
			<li class="page-item">
				<a class="page-link" data-value="prev" href="gshop.do?command=viewCategory&cgseq=${categoryList.cgseq}&page=${num}">Prev</a>
			</li>
		</c:if>
		<!-- 페이지 번호 -->
		<c:forEach var="num" begin="${paging.beginPage}"
			end="${paging.endPage}">

			<c:if test="${num == paging.page}">
				<li class="page-item active"><a class="page-link"
					href="gshop.do?command=viewCategory&cgseq=${categoryList.cgseq}&page=${num}"
					data-value="${num}"> ${num} </a></li>
			</c:if>
			<c:if test="${num != paging.page}">
				<li class="page-item"><a class="page-link"
					href="gshop.do?command=viewCategory&cgseq=${categoryList.cgseq}&page=${num}"
					data-value="${num}"> ${num} </a></li>
			</c:if>
		</c:forEach>
		<!-- 다음 버튼 -->
		<c:if test="${paging.next}">
			<li class="page-item">
				<a class="page-link" href="gshop.do?command=viewCategory&cgseq=${categoryList.cgseq}&page=${num}" data-value="prev">Prev</a>
			</li>
		</c:if>
	</ul>	
	
</body>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</html>