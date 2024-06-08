<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Goods Shop</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/admin.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/admin/index.css'/>">
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body>
	<body class="sb-nav-fixed">
        <!-- header -->
        <jsp:include page="../fix/admin/header.jsp"/>
        
        <div id="layoutSidenav">
        	<!-- side -->
        	<jsp:include page="../fix/admin/sidemenu.jsp"/>

			<div id="layoutSidenav_content">
				<div class="container-fluid">
			    	<!-- 목록 select -->
		    		<div class="row">
					    <div class="col">
					    	<select class="form-select w-25" name="selectAmount" id="selectAmount">
							  <option value="0" selected>목록 갯수</option>
							  <option value="10">10</option>
							  <option value="50">50</option>
							  <option value="100">100</option>
							</select>
					    </div>
					    
					    <div class="col d-flex justify-content-end">
					    	<select class="form-select w-25 me-1" name="search" id="search">
						  		<option value="userid" selected>아이디</option>
						  		<option value="name">이름</option>
						  		<option value="email">이메일</option>
							</select>
				      		<div class="d-flex">
					      		<input class="form-control me-2" name="keyword" id="keyword" type="text" placeholder="Search">
					    	</div>
					    </div>
					</div>
					<!-- memberList -->
					<div>
						<div>
							<ul>
		               			<li class="member-header">
		               				<div class="d-flex">
		               					<div>userID</div>
		               					<div>Grade</div>
		               					<div>Name</div>
		               					<div>Phone</div>
		               					<div>Email</div>
		               					<div class="small-col">Zip_Code</div>
		               					<div>Address</div>
		               					<div>Detail Address</div>
		               					<div>Indate</div>
		               					<div class="small-col">Y/N</div>
		               					<div class="small-col"><input class="form-check-input" type="checkbox" id="checkAll"></div>
		               				</div>
		               			</li>
		               		</ul>
		               	</div>
		               	<div>
		               		<ul id="member-list">
								<c:forEach var="vo" items="${memberList}">
									<li class="member-item">
										<div class="d-flex justify-content-center align-items-center">
											<div>${vo.userid}</div>
			               					<div>${vo.grade}</div>
			               					<div>${vo.name}</div>
			               					<div>${vo.phone}</div>
			               					<div>${vo.email}</div>
			               					<div class="small-col">${vo.zip_code}</div>
											<div>${vo.address}</div>
											<div>${vo.d_address}</div>
											<div><fmt:formatDate value="${vo.indate}" type="both" pattern="yyyy-MM-dd" /></div>
			               					<div class="small-col">
			               						<c:choose>
													<c:when test="${vo.is_login eq 1}">Y</c:when>
													<c:otherwise>N</c:otherwise>
												</c:choose>
			               					</div>
			               					<div class="small-col"><input class="form-check-input" type="checkbox" name="YN" value="${vo.userid}"></div>
		               					</div>
									</li>
								</c:forEach>
							</ul>
		               	</div>
               		</div>
		    		<!-- paging -->
			    	<nav style="display: flex; justify-content: space-between;">
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
					  	<li class="list-group-item d-flex align-items-center">
						  	<span class="form-text">
						  		${paging.currentPage} / ${paging.realEnd}
					  		</span>
					  	</li>
					  </ul>
					  	<div>
							<input type="button" name="switchBtn" id="switchBtn" value="회원 상태 변경"/>
							<input type="button" name="discardBtn" id="discardBtn" value="탈퇴 처리하기"/>
						</div>
					</nav>
				</div>
			</div>
        </div>
        <script type="text/javascript" src="<c:url value='/resources/js/admin/member.js'/>"></script>
    </body>
</html>