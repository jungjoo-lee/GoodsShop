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
	<form name="goodsViewForm">
		<div id="layoutSidenav_content">
			<div class="container">
				<h1 class="mt-4">상품목록</h1><!-- title -->
				
				
				<div class="row w-100">
					<div class="col d-flex"> <!-- 목록 선택 -->
				      		<select class="form-select w-50 me-3" name="selectCategory" id="selectCategory">
						  		<option value=0 selected>카테고리별</option>
						  		<option value=1>목걸이</option>
						  		<option value=2>반지</option>
						  		<option value=3>팔찌</option>
						  		<option value=4>귀걸이</option>
						  		<option value=5>키링</option>
						  		<option value=6>인형</option>
						  		<option value=7>기타</option>
							</select>
				    </div>
					<div class="col d-flex justify-content-end">
						<!-- 검색 폼 -->
						<div class="d-flex">
							<input class="form-control me-2" name="searchKey" id="searchKey"
								type="text" placeholder="상품명을 입력하세요">
						</div>
						<div class="d-flex" id="goSearch">
							<input type="button" value="검색">
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
							<th>베스트YN</th>
							<th>판매중YN</th>
							<th> <input type="checkbox" id=checkAll> </th>
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
										<tr>					
											<td onclick="viewGoodsDetail(${agvo.gseq})">${agvo.gseq}</td>
											<td onclick="viewGoodsDetail(${agvo.gseq})">${agvo.gname}</td>
											<td onclick="viewGoodsDetail(${agvo.gseq})" class="thumbnail">
												<img src="<c:url value='/gshop.do?command=imageWrite&folder=${agvo.gseq}${agvo.gname}&realName=${agvo.realname}'/>">
											</td>
											<td onclick="viewGoodsDetail(${agvo.gseq})">${agvo.oprice}</td>
											<td onclick="viewGoodsDetail(${agvo.gseq})">${agvo.sprice}</td>
											<td onclick="viewGoodsDetail(${agvo.gseq})">${agvo.mprice}</td>
											<td onclick="viewGoodsDetail(${agvo.gseq})">${agvo.indate}</td>
											<td onclick="viewGoodsDetail(${agvo.gseq})">${agvo.category}</td>
											<c:choose>
												<c:when test="${agvo.bestyn == 1}">
													<td>Y</td>
												</c:when>
												<c:otherwise>
													<td>N</td>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${agvo.useyn == 1}">
													<td>Y</td>
												</c:when>
												<c:otherwise>
													<td>N</td>
												</c:otherwise>
											</c:choose>
											<td> 
												<input type="checkbox" id="checkboxes" name="gseq" value="${agvo.gseq}" />
											</td>
										</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			
			<div>
				<input type="button" id="bestToggle" value="베스트 상품 변경">
				<input type="button" id="useynToggle" value="상품 판매여부 변경">
				<input type="button" id="deleteGoods" value="상품 삭제">
			</div>



			<script type="text/javascript" src="<c:url value='/resources/js/admin/admin.js'/>"></script>
			<script type="text/javascript" src="<c:url value='/resources/js/admin/goodsView.js'/>"></script>	
		</div>
		</div>
	</form>
	</div>

</body>
</html>