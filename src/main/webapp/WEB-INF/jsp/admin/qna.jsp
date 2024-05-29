<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/admin.css'/>">
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
	<body class="sb-nav-fixed">
        <!-- header -->
        <jsp:include page="../fix/admin/header.jsp"/>
        
        <div id="layoutSidenav">
        	<!-- side -->
        	<jsp:include page="../fix/admin/sidemenu.jsp"/>
        	
        	<div id="layoutSidenav_content">
        		<div class="container-fluid px-4">
                	<h1 class="mt-4">Q&A</h1><br/> <!-- 제목 -->
					<div class="row w-100">
				    	<div class="col"> <!-- 목록 선택 -->
				      		<select class="form-select w-25" name="selectAmount" id="selectAmount">
						  		<option value="0" selected>목록</option>
						  		<option value=10>10</option>
						  		<option value=50>50</option>
						  		<option value=100>100</option>
							</select>
				    	</div>
				    	<div class="col d-flex justify-content-end"> <!-- 검색 폼 -->
				      		<form class="d-flex" role="search">
					      		<input class="form-control me-2" name="keyword" id="keyword" type="search" placeholder="Search">
					      		<button class="btn btn-outline-success" type="submit">Search</button>
					    	</form>
				    	</div>
					</div>
					<br/>
					<div class="row w-100"> <!--  목록 테이블 -->
				  		<table class="table table-hover">
				  			<thead class="text-center table-light">
				  				<tr>
				  					<th>처리상태</th>
				  					<th>제목</th>
				  					<th>작성자</th>
				  					<th>작성일</th>
				  				</tr>
				  			</thead>
				  			<tbody>
				  				<c:forEach var="qna" items="${qnaList}">
				  					<tr>
				  						<td class="text-center">
				  						<c:choose>
				  							<c:when test='${empty qnaVO.reply}'>(미처리)</c:when>
				  							<c:otherwise>(답변완료)</c:otherwise>
				  						</c:choose>
				  						</td>
				  						<td><a href="<c:url value='/gshop.do?command=qnaView&qseq=${qna.qseq}'/>">${qna.subject}</a></td>
				  						<td class="text-center">${qna.userid}</td>
				  						<td class="text-center"><fmt:formatDate value="${qna.indate}" /></td>
				  					</tr>
				  				</c:forEach>
				  			</tbody>
				  		</table>
					</div>
					<div class="row w-100">
						<div class="col d-flex align-items-center"> <!-- page 정보 출력 -->
							<input class="form-control w-25 me-3" type="text" name="quickMove" id="quickMove" placeholder="Page Num">
							${paging.currentPage} / ${paging.realEnd}
						</div>
						<div class="col d-flex justify-content-end"> <!-- paging -->
							<!-- paging -->
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
						</div>
					</div>
            	</div>
        	</div>            
        </div>
	<script type="text/javascript" src="<c:url value='/resources/js/admin/admin.js'/>"></script>
    </body>
</html>