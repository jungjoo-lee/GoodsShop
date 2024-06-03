<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/index/index.css'/>">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
	<c:choose>
		<c:when test="${empty loginUser}">
			<div>
				<h3>로그인하삼</h3>
				<button id="login"> 로그인ㄱㄱ </button>
			</div>
		</c:when>
		<c:otherwise>
			<div> ${loginUser.userid} ㅎㅇ </div>
			<div> 
				<button id="go_cart"> 장바구니 보기 </button>
				<button id="go_wishlist"> 찜목록 보기 </button>
			</div>
			<h3>하이</h3>			
		</c:otherwise>
	</c:choose>

	<div>
		<div> <h3>베스트 20</h3> </div>
		<c:forEach items="${bestlist}" var="gvo" varStatus="status">

			<div>				
				<div>
					<a href="gshop.do?command=goodsDetailView&gseq=${gvo.gseq}">
						<img alt="${gvo.thum}.png" src='<c:url value="/resources/image/goods/${gvo.thum}.png"/>'>	
					</a>
					<a href="gshop.do?command=goodsDetailView&gseq=${gvo.gseq}">
						${gvo.gname} - ${gvo.sprice}					
					</a>
				</div>
			</div>
		</c:forEach>	
	</div>
	<div>
		<div> <h3>신상품</h3> </div>
		<c:forEach items="${newlist}" var="gvo" varStatus="status">

			<div>				
				<div>
					<a href="gshop.do?command=goodsDetailView&gseq=${gvo.gseq}">
						<img alt="${gvo.thum}.png" src='<c:url value="/resources/image/goods/${gvo.thum}.png"/>'>	
					</a>		
					<a href="gshop.do?command=goodsDetailView&gseq=${gvo.gseq}">
						${gvo.gname} - ${gvo.sprice}					
					</a>		
				</div>
			</div>
		</c:forEach>	
	</div>

	<script type="text/javascript" src='<c:url value = "/resources/js/goods/main.js"/>'></script>
<!-- 공지사항 -->
<div class="card w-100 ms-3 me-2">
  <div class="card-header d-flex justify-content-between">
  	<div>공지사항</div>
    <div>
    	<a href="<c:url value='/gshop.do?command=noticeList'/>"><i class="bi bi-plus-circle"></i> 전체보기</a>
    </div>
  </div>
  <ul class="list-group list-group-flush">
  	<c:forEach var="notice" items="${noticeList}">
  	<li class="list-group-item list-group-item-action d-flex">
  		<div>${notice.nseq}</div>
  		<div>${notice.adminId}</div>
  		<div>
	  		<a href="">
	  		<c:choose>
		        <c:when test="${fn:length(notice.subject) gt 26}">
		        	<c:out value="${fn:substring(notice.subject, 0, 25)}">...</c:out>
		        </c:when>
		        <c:otherwise>
		        	<c:out value="${notice.subject}"/>
		        </c:otherwise>
			</c:choose>
			</a>
		</div>
		<div>
			<c:choose>
		        <c:when test="${fn:length(notice.content) gt 26}">
		        	<c:out value="${fn:substring(notice.content, 0, 25)}">...</c:out>
		        </c:when>
		        <c:otherwise>
		        	<c:out value="${notice.content}"/>
		        </c:otherwise>
			</c:choose>
		</div>
		<div><fmt:formatDate value="${notice.indate}" type="both" pattern="yyyy-MM-dd" /></div>
	</li>
  	</c:forEach>
  </ul>
</div>

<div class="d-flex col">
	<!-- Q&A -->
	<div class="card w-50 ms-3 me-2">
	  <div class="card-header d-flex justify-content-between">
	  	<div>Q&A</div>
	    <div>
	    	<a href="<c:url value='/gshop.do?command=qnaList'/>"><i class="bi bi-plus-circle"></i> 전체보기</a>
	    </div>
	  </div>
	  <ul class="list-group list-group-flush">
	  	<c:forEach var="qna" items="${qnaList}">
	  	<li class="list-group-item list-group-item-action d-flex">
	  		<div>${qna.qseq}</div>
	  		<div>
		  		<a href="">
		  		<c:choose>
			        <c:when test="${fn:length(qna.subject) gt 26}">
			        	<c:out value="${fn:substring(qna.subject, 0, 25)}">...</c:out>
			        </c:when>
			        <c:otherwise>
			        	<c:out value="${qna.subject}"/>
			        </c:otherwise>
				</c:choose>
				<c:if test="${not empty qna.replyDate}"><i class="bi bi-clipboard-check"></i></c:if>
				</a>
			</div>
			<div>${qna.userid}</div>
			<div><fmt:formatDate value="${qna.indate}" type="both" pattern="yyyy-MM-dd" /></div>
		</li>
	  	</c:forEach>
	  </ul>
	</div>
	<!-- review -->
	<div class="card w-50 ms-2 me-3">
	  <div class="card-header d-flex justify-content-between">
	  	<div>Review</div>
	    <div>
	    	<a href="<c:url value='/gshop.do?command=reviewList'/>"><i class="bi bi-plus-circle"></i> 전체보기</a>
	    </div>
	  </div>
	  <ul class="list-group list-group-flush">
	  	<c:forEach var="rev" items="${reviewList}">
	  	<li class="list-group-item list-group-item-action d-flex">
	  		<div>${rev.gname}</div>
	  		<div>
		  		<a href="#">
		  		<c:choose>
			        <c:when test="${fn:length(rev.subject) gt 14}">
			        	<c:out value="${fn:substring(rev.subject, 0, 13)}"/>...
			        </c:when>
			        <c:otherwise>
			        	<c:out value="${rev.subject}"/>
			        </c:otherwise>
				</c:choose>
				</a>
			</div>
			<div>${rev.userid}</div>
			<div><fmt:formatDate value="${rev.indate}" type="both" pattern="yyyy-MM-dd" /></div>
		</li>
	  	</c:forEach>
	  </ul>
	</div>
</div>
</body>
</html>