<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/index/index.css'/>">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
    <div id="container">
		<div class="section">
			<div class="section-title"> New </div>
			<div class="product-row">
			<c:forEach items="${newlist}" var="gvo" varStatus="status">
				<div class="product-box">
					<div class="product-info">
						<a href="gshop.do?command=goodsDetailView&gseq=${gvo.gseq}">
							<div class="product-image">
								<c:forEach var="image" items="${gvo.imageList}">
					                <c:set var="key" value="${gvo.gseq}${gvo.gname}${image.realname}"/>
					                <img alt="${image.realname}" src="data:image/jpeg;base64,${imageMap[key]}"/>
					            </c:forEach>
								<%-- <img alt="${gvo.imageList[0].realname}" src="<c:url value='/gshop.do?command=imageWrite&folder=${gvo.gseq}${gvo.gname}&realName=${gvo.imageList[0].realname}'/>"> --%>
							</div>
							${gvo.gname} - <fmt:formatNumber type="currency" value="${gvo.sprice}"></fmt:formatNumber>
						</a>
					</div>
				</div>
			</c:forEach>
			</div>
		</div>
		
		<div class="section">
			<div class="section-title"> Best 20 </div>
			<div class="product-row">
				<c:forEach items="${bestlist}" var="gvo" varStatus="status">
					<c:if test="${status.index < 8}">
						<div class="product-box">
							<div class="product-info">
								<a href="gshop.do?command=goodsDetailView&gseq=${gvo.gseq}">
									<div class="product-image">
										<c:forEach var="image" items="${gvo.imageList}">
							                <c:set var="key" value="${gvo.gseq}${gvo.gname}${image.realname}"/>
							                <img alt="${image.realname}" src="data:image/jpeg;base64,${imageMap[key]}"/>
							            </c:forEach>
										<%-- <img alt="${gvo.imageList[0].realname}" src="<c:url value='/gshop.do?command=imageWrite&folder=${gvo.gseq}${gvo.gname}&realName=${gvo.imageList[0].realname}'/>"> --%>
									</div>
									${gvo.gname} - <fmt:formatNumber type="currency" value="${gvo.sprice}"></fmt:formatNumber>
								</a>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>
			<br><br>
			<div class="view-all">
				<a href="gshop.do?command=viewCategory&cgseq=0">전체보기</a>
			</div>
			<br><br><br><br>
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
	</div>
<script type="text/javascript" src='<c:url value = "/resources/js/goods/main.js"/>'></script>
</body>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</html>