<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Goods</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/admin.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/goods.css'/>">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body class="sb-nav-fixed">
	<!-- header -->
	<jsp:include page="../fix/admin/header.jsp" />

	<div id="layoutSidenav">
		<!-- side -->
		<jsp:include page="../fix/admin/sidemenu.jsp" />

		<div id="layoutSidenav_content">
			<div class="container">
				<h1 class="mt-4">상품목록</h1><!-- title -->
				
				
				<div class="row w-100">
					<div class="col d-flex"> <!-- 목록 선택 -->
				      		<select class="form-select w-25 me-3" name="selectAmount" id="selectAmount">
						  		<option value=0 selected>카테고리별 보기</option>
						  		<option value=1>10</option>
						  		<option value=2>50</option>
						  		<option value=3>100</option>
						  		<option value=4>100</option>
						  		<option value=5>100</option>
						  		<option value=6>100</option>
						  		<option value=7>100</option>
							</select>
							<div class="btn-group">
				                <button type="button" class="btn btn-outline-secondary active" id="all"><i class="bi bi-list"></i></button>
				                <button type="button" class="btn btn-outline-secondary" id="notnull"><i class="bi bi-check-circle"></i></button>
				            	<button type="button" class="btn btn-outline-secondary" id="null"><i class="bi bi-x-circle"></i></button>
				            </div>
				    </div>
					<div class="col d-flex justify-content-end">
						<!-- 검색 폼 -->
						<select class="form-select w-25 me-1" name="search" id="search">
							<option value="gname" selected>상품명</option>
<!-- 							<option value="userid">작성자</option> -->
						</select>
						<div class="d-flex">
							<input class="form-control me-2" name="keyword" id="keyword"
								type="text" placeholder="Search">
						</div>
					</div>
				</div>


				<div class="row w-100">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>상품번호</th>
							<th>상품명</th>
							<th>이미지</th>
							<th>원가</th>
							<th>판매가</th>
							<th>마진</th>
							<th>등록일</th>
							<th>카테고리</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty adminGoodsList}">
								<tr>
									<td>등록된 상품이 없습니다</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${adminGoodsList}" var="agvo">
										<tr onclick="viewGoodsDetail(${agvo.gseq})">					
											<td>${agvo.gseq}</td>
											<td>${agvo.gname}</td>
											<td class="thumbnail">
												<img alt="${agvo.thum}.png" src='<c:url value="/resources/image/goods/${agvo.thum}.png"/>'>
											</td>
											<td>${agvo.oprice}</td>
											<td>${agvo.sprice}</td>
											<td>${agvo.mprice}</td>
											<td>${agvo.indate}</td>
											<td>${agvo.category}</td>
										</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>



			<!-- Paging -->
			<div class="row w-100">
				<div class="col d-flex align-items-center">
					<!-- page 정보 출력 -->
					<input class="form-control w-25 me-3" type="text" name="quickMove"
						id="quickMove" placeholder="Page Num"> <span id="pagdInfo">${paging.currentPage}
						/ ${paging.realEnd}</span>
				</div>
				<div class="col d-flex justify-content-end">
					<!-- paging -->
					<!-- paging -->
					<nav>
						<ul class="pagination justify-content-center" id="pagination">
							<!-- 이전 버튼 -->
							<c:choose>
								<c:when test="${paging.prev}">
									<li class="page-item"><a class="page-link"
										data-value="prev">Prev</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item disabled"><a class="page-link">Prev</a>
									</li>
								</c:otherwise>
							</c:choose>
							<!-- 페이지 번호 -->
							<c:forEach var="num" begin="${paging.startPage}"
								end="${paging.endPage}">
								<c:if test="${num == paging.currentPage}">
									<li class="page-item active"><a class="page-link"
										data-value="${num}">${num}</a></li>
								</c:if>
								<c:if test="${num != paging.currentPage}">
									<li class="page-item"><a class="page-link"
										data-value="${num}">${num}</a></li>
								</c:if>
							</c:forEach>
							<!-- 다음 버튼 -->
							<c:choose>
								<c:when test="${paging.next}">
									<li class="page-item"><a class="page-link"
										data-value="next">Next</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item disabled"><a class="page-link">Next</a>
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</nav>
				</div>
			</div>
			<script type="text/javascript" src="<c:url value='/resources/js/admin/admin.js'/>"></script>
			<script type="text/javascript" src="<c:url value='/resources/js/admin/goodsView.js'/>"></script>	
		</div>
	</div>
</body>
</html>