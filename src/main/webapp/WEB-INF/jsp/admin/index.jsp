<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Goods Shop</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/admin.css'/>">
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
				<div class="container">
			    	<!-- 목록 select -->
		    		<div class="row">
					    <div class="col-10"></div>
					    <div class="col-2">
					    	<select class="form-select" name="selectAmount" id="selectAmount">
							  <option value="0" selected>목록 갯수</option>
							  <option value="10">10</option>
							  <option value="50">50</option>
							  <option value="100">100</option>
							</select>
					    </div>
					</div>
					
		    		<!-- memberList -->
		    		<table class="table table-hover">
		    			<thead>
						    <tr>
						      	<th scope="col">userID</th>
						      	<th scope="col">Grade</th>
						      	<th scope="col">Name</th>
						    	<th scope="col">Phone</th>
						    	<th scope="col">Email</th>
						    	<th scope="col">Zip_Code</th>
						    	<th scope="col">Address</th>
						    	<th scope="col">Detail Address</th>
						    	<th scope="col">Indate</th>
						    	<th scope="col">use Y/N</th>
						    	<th scope="col"><input class="form-check-input" type="checkbox" value="checkAll" id="checkAll"
						    								name="YN"	onclick="checkAll(this)"></th>
							</tr>
						</thead>
						<tbody id="memberList">
						<form name="adminList" method="post">
						<c:forEach var="vo" items="${memberList}">
							<tr>
								<td>${vo.userid}</td>
								<td>${vo.grade}</td>
								<td>${vo.name}</td>
								<td>${vo.phone}</td>
								<td>${vo.email}</td>
								<td>${vo.zip_code}</td>
								<td>${vo.address}</td>
								<td>${vo.d_address}</td>
								<td>${vo.indate}</td>
								<td class="text-center">
									<c:choose>
										<c:when test="${vo.is_login eq 1}">Y</c:when>
										<c:otherwise>N</c:otherwise>
									</c:choose>
								</td>
								<td class="text-center">
								<input class="form-check-input" type="checkbox" 	name="YN"	value="${vo.userid}"></td>
							</tr>
						</c:forEach>
						</form>
						</tbody>
					</table>

					
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
					  	<span class="form-text" style="margin-top: 0; margin-left: 20px">
					  		${paging.currentPage} / ${paging.realEnd}
					  	</span></li>
					  </ul>
					  	<div>
							<input type="button" id="switch" value="회원 상태 변경" name="switch" onclick="switchYN()">
							<input type="button" id="discard" value="탈퇴 처리하기" name="discard" onclick="discard()">
						</div>
					</nav>
				</div>
			</div>
        </div>
        <script type="text/javascript" src="<c:url value='/resources/js/admin/member.js'/>"></script>
    </body>
</html>