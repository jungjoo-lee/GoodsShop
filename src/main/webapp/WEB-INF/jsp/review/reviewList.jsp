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
              		<div class="tab-pane fade show active" id="allReview">
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
						<c:forEach var="rev" items="${reviewList}">
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
                  	</div>
					<div class="tab-pane fade" id="myReview">
                		내 리뷰
                	</div>
              	</div>
            </div>
    	</div>
	</div>
</div>
<script type="text/javascript" src="<c:url value='/resources/js/review/review.js'/>"></script>
</body>
</html>