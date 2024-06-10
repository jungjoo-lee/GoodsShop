<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/admin.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/admin/index.css'/>">
</head>
<body class="sb-nav-fixed">
 		<div id="layoutSidenav_content" style="width:100%; align-items:center; justify-content:center;">
 			<div class="container-fluid px-4"  style="width:70%; align-items:center; justify-content:center;">
         		<h1 class="mt-4">공지사항</h1><br/> <!-- 제목 -->
            	<div class="row w-100">
					<div class="col d-flex">
						<select class="form-select w-25 me-3" name="selectAmount" id="selectAmount">
							<option value=0 selected>목록</option>
							<option value=10>10</option>
							<option value=30>30</option>
							<option value=50>50</option>
						</select>
					</div>
					<div class="col d-flex justify-content-end">
						<select class="form-select w-25 me-1" name="search" id="search">
							<option value="sc" selected>제목 + 내용</option>
							<option value="subject">제목</option>
							<option value="content">내용</option>
						</select>
                     	<div class="d-flex">
                        	<input class="form-control me-2" name="keyword" id="keyword" type="text" placeholder="Search">
						</div>
					</div>
				</div>
				<br/>
				<div>
					<div>
						<ul>
		                    <li class="li-header">
		                       <div class="d-flex">
		                          <div class="small-col">번호</div>
		                          <div class="small-col">작성자</div>
		                          <div>제목</div>
		                          <div>내용</div>
		                          <div class="small-col">작성일자</div>
		                          <div class="small-col"><input class="form-check-input" type="checkbox" id="checkAll"></div>
		                       </div>
		                    </li>
						</ul>
					</div>								
				</div>
               	<div>
               		<ul id="notice-list">
						<c:forEach var="notice" items="${noticeList}">
							<li class="li-item">
								<div class="d-flex justify-content-center align-items-center">
									<div class="small-col"><a href="<c:url value='/gshop.do?command=noticeView&nseq=${notice.nseq}'/>">${notice.nseq}</a></div>
			                        <div class="small-col"><a href="<c:url value='/gshop.do?command=noticeView&nseq=${notice.nseq}'/>">${notice.adminId}</a></div>
			                        <div><a href="<c:url value='/gshop.do?command=noticeView&nseq=${notice.nseq}'/>">${notice.subject}</a></div>
			                        <div><a href="<c:url value='/gshop.do?command=noticeView&nseq=${notice.nseq}'/>">${notice.content}</a></div>
									<div class="small-col"><fmt:formatDate value="${notice.indate}" type="both" pattern="yyyy-MM-dd" /></div>
									<div class="small-col"><input class="form-check-input" type="checkbox" name="check" value="${notice.nseq}"></div>
		                      </div>
			               </li>
						</c:forEach>
               		</ul>
               	</div>
               	<div class="d-flex col align-items-center"  style="width:100%; align-items:center; justify-content:center;">
               		<jsp:include page="paging.jsp">
			    		<jsp:param value="${paging}" name=""/>
			    	</jsp:include><br/>
			    	<span id="pageInfo">${paging.currentPage} / ${paging.realEnd}</span>
               	</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="<c:url value='/resources/js/admin/notice.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/admin/fix.js'/>"></script>
</body>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</html>