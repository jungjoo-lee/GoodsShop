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
<link rel="stylesheet" href="<c:url value='/resources/css/review/review.css'/>">
</head>
<body>
<div class="row d-flex justify-content-center">
	<div class="col-lg-6 w-75">
		<div class="card">
        	<div class="card-body">
            	<h5 class="card-title">리뷰</h5>
				<ul class="nav nav-tabs">
					<li class="nav-item">
						<button class="nav-link active" id="all-tab">전체보기</button>
                	</li>
	                <li class="nav-item">
	                	<button class="nav-link" id="my-tab">내 리뷰</button>
	                </li>
				</ul>
				<div class="tab-content pt-2">
					<!-- -------------------------------------------------------------------------------------------------------------------------------------------------- -->
              		<div class="tab-pane fade show active" id="allReview">
              			<div class="row w-100">
              				<div class="col d-flex">
	              				<select class="form-select w-25 me-3" name="selectAmount" id="selectAmount">
							  		<option value=0 selected>목록</option>
							  		<option value=5>5</option>
							  		<option value=10>10</option>
							  		<option value=20>20</option>
								</select>
							</div>
							<div class="col d-flex justify-content-end">
					    		<select class="form-select w-25 me-1" name="search" id="search">
							  		<option value="category" selected>카테고리</option>
							  		<option value="gname">굿즈명</option>
								</select>
					      		<div class="d-flex">
						      		<input class="form-control me-2" name="keyword" id="keyword" type="text" placeholder="Search">
						    	</div>
					    	</div>
		            	</div>
                  		<ul>
                  			<li class="review-header">
                  				<div class="d-flex">
                  					<div class="small-col">번호</div>
                  					<div>사진</div>
                  					<div class="small-col">카테고리</div>
                  					<div>굿즈명</div>
                  					<div>리뷰제목</div>
                  					<div>구매자</div>
                  					<div>리뷰작성일자</div>
                  				</div>
                  			</li>
                  			<div id="review-list">
							<c:forEach var="rev" items="${reviewList}">
							<li class="review-item">
								<div class="d-flex justify-content-center align-items-center">
									<div class="small-col">${rev.rseq}</div>
	               					<div><img src="<c:url value='/gshop.do?command=imageWrite&folder=${rev.gseq}${rev.gname}&realName=${rev.realName}'/>"></div>
	               					<div class="small-col">[${rev.category}]</div>
	               					<div>${rev.gname}</div>
	               					<div>${rev.subject}</div>
	               					<div><img id="badge" src="<c:url value='/resources/image/badge/${rev.grade}.png'/>"> ${rev.userid}</div>
	               					<div><fmt:formatDate value="${rev.indate}"  type="both" pattern="yyyy-MM-dd" /></div>
               					</div>
							</li>
							</c:forEach>
							</div>
						</ul>
						<span id="pagdInfo">${paging.currentPage} / ${paging.realEnd}</span>
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
						  		<c:if test="${num != myPaging.currentPage}">
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
                  	<!-- -------------------------------------------------------------------------------------------------------------------------------------------------- -->
					<div class="tab-pane fade" id="myReview">
						<div class="row w-100">
              				<div class="col d-flex">
	              				<select class="form-select w-25 me-3" name="mySelectAmount" id="mySelectAmount">
							  		<option value=0 selected>목록</option>
							  		<option value=5>5</option>
							  		<option value=10>10</option>
							  		<option value=20>20</option>
								</select>
							</div>
							<div class="col d-flex justify-content-end">
					    		<select class="form-select w-25 me-1" name="mySearch" id="mySearch">
							  		<option value="category" selected>카테고리</option>
							  		<option value="gname">굿즈명</option>
								</select>
					      		<div class="d-flex">
						      		<input class="form-control me-2" name="myKeyword" id="myKeyword" type="text" placeholder="Search">
						    	</div>
					    	</div>
		            	</div>
                		<ul>
                  			<li class="review-header">
                  				<div class="d-flex">
                  					<div class="small-col">번호</div>
                  					<div>사진</div>
                  					<div class="small-col">카테고리</div>
                  					<div>굿즈명</div>
                  					<div>리뷰제목</div>
                  					<div>구매자</div>
                  					<div>리뷰작성일자</div>
                  				</div>
                  			</li>
						<c:forEach var="rev" items="${reviewMyList}">
							<li class="review-item">
								<div class="d-flex justify-content-center align-items-center">
									<div class="small-col">${rev.rseq}</div>
	               					<div><img src="<c:url value='/gshop.do?command=imageWrite&folder=${rev.gseq}${rev.gname}&realName=${rev.realName}'/>"></div>
	               					<div class="small-col">[${rev.category}]</div>
	               					<div>${rev.gname}</div>
	               					<div>${rev.subject}</div>
	               					<div><img id="badge" src="<c:url value='/resources/image/badge/${rev.grade}.png'/>"> ${rev.userid}
	               					</div>
	               					<div><fmt:formatDate value="${rev.indate}"  type="both" pattern="yyyy-MM-dd" /></div>
               					</div>
							</li>
						</c:forEach>
						</ul>
						<span id="pagdInfo">${myPaging.currentPage} / ${myPaging.realEnd}</span>
						<nav>
							<ul class="pagination justify-content-center" id="pagination">
						  	<!-- 이전 버튼 -->
						  	<c:choose>
						  		<c:when test="${myPaging.prev}">
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
						  	<c:forEach var="num" begin="${myPaging.startPage}" end="${myPaging.endPage}">
						  		<c:if test="${num == myPaging.currentPage}">
						  			<li class="page-item active"><a class="page-link" data-value="${num}">${num}</a></li>
						  		</c:if>
						  		<c:if test="${num != myPaging.currentPage}">
						  			<li class="page-item"><a class="page-link" data-value="${num}">${num}</a></li>
						  		</c:if>
						  	</c:forEach>
						    <!-- 다음 버튼 -->
						    <c:choose>
						  		<c:when test="${myPaging.next}">
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
</div>
<script type="text/javascript" src="<c:url value='/resources/js/review/review.js'/>"></script>
</body>
</html>