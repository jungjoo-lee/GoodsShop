<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/goods/detail.css'/>">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>

	<h1>${goodsDetail.gname}</h1>

	<form method="post" name="goodsform">
		<input type="hidden" name="gseq" id="gseq" value="${goodsDetail.gseq}">
		<div>
			<div>
				<img alt="${goodsDetail.thum}.png"
					src='<c:url value = "/resources/image/goods/${goodsDetail.thum}.png"/>'>
			</div>

			<div>
				<div>가격 : ${goodsDetail.sprice} 원</div>
				<div>
					수량 : <input id="input_quantity" name="input_quantity" type="number"
						min="1" value="1">
				</div>
			</div>

			<div>
				<div>
					<input type="button" id="add_cart" value="장바구니에 추가"> <input
						type="button" id="add_wishlist" value="찜하기"> <input
						type="button" id="purchase_now" value="바로 주문하기"> <input
						type="button" id="go_main" value="메인으로">
				</div>
			</div>

			<div>
				<div>${goodsDetail.content}</div>
				<div>
					<c:forEach items="${goodsDetail.imageList}" var="img">
						<div>
							<img
								src='<c:url value = "/resources/image/goods/${img.realname}.png"/>'>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</form>
	<div>
		
	</div>
	<div class="row d-flex justify-content-center">
		<div class="col-lg-6 w-75">
			<div class="card">
	        	<div class="card-body">
	            	<h5 class="card-title">리뷰</h5>
					<div class="row">
						<c:if test="${not empty loginUser}">
							<button type="button" class="btn btn-primary" id="reviewWriteBtn">리뷰 쓰기</button>
						</c:if>
						<input type="text" name="subject" id="subject" placeholder="제목">
						<textarea rows="10" cols="100" name="content" id="content" placeholder="내용"></textarea>
					</div>
					<div>
						<ul>
							<li class="review-header">
								<div class="d-flex">
									<div class="small-col">번호</div>
									<div class="small-col">작성자</div>
									<div>리뷰제목</div>
									<div>내용</div>
									<div>리뷰작성일자</div>
									<div class="small-col"></div>
								</div>
							</li>
						</ul>
					</div>
					<div>
						<ul id="reviewList">
							<c:forEach var="vo" items="${reviewList}">
							<li class="review-item">
								<div class="d-flex">
									<div class="small-col">${vo.rseq}</div>
									<div class="small-col">${vo.grade}, ${vo.userid}</div>
									<div class="left">${vo.subject}</div>
									<div class="left">${vo.content}</div>
									<div><fmt:formatDate value="${vo.indate}" type="both" pattern="yyyy-MM-dd HH:mm:SS" /></div>
									<div class="small-col">
										<c:if test="${loginUser.userid eq vo.userid}">
											<button type="button" class="btn btn-primary btn-sm" id="reviewUpdateBtn"><i class="bi bi-pen-fill"></i></button><button type="button" class="btn btn-danger btn-sm" id="reviewDeleteBtn"><i class="bi bi-x-square-fill"></i></button>
										</c:if>
									</div>
								</div>
							</li>
							</c:forEach>
						</ul>
					</div>
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
	<script type="text/javascript" src='<c:url value = "/resources/js/goods/detail.js"/>'></script>
</body>
</html>