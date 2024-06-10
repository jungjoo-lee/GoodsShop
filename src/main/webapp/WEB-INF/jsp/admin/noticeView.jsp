<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/admin.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/noticeView.css'/>">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
	<body class="sb-nav-fixed">
        <!-- header -->
        <jsp:include page="../fix/admin/header.jsp"/>
        
        <div id="layoutSidenav">
        	<!-- side -->
        	<jsp:include page="../fix/admin/sidemenu.jsp"/>
        	
        	<div id="layoutSidenav_content">
		        <div class="row justify-content-center">
		            <div class="col-lg-8 col-md-10">
		                <div class="detail-container">
		                    <h4 class="mb-3">Notice Details</h4>
		                    <div class="detail-item">
		                        <strong>번호:</strong><span>${vo.nseq}</span>
		                    </div>
		                    <div class="detail-item">
		                        <strong>작성자:</strong><span>${vo.adminId}</span>
		                    </div>
		                    <div class="detail-item">
		                        <strong>제목:</strong><span>${vo.subject}</span>
		                    </div>
		                    <div class="detail-item">
		                        <strong>내용:</strong><pre><span>${vo.content}</span></pre>
		                    </div>
		                    <div class="detail-item">
		                        <strong>작성일자:</strong><span><fmt:formatDate value="${vo.indate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span>
		                    </div>
		                    <div class="btn-group">
		                        <a class="btn btn-primary btn-custom" href="<c:url value='/gshop.do?command=noticeUpdateForm&nseq=${vo.nseq}'/>">수정</a>
		                        <a class="btn btn-danger btn-custom" href="<c:url value='/gshop.do?command=noticeDelete&nseq=${vo.nseq}'/>">삭제</a>
		                        <a class="btn btn-secondary" href="<c:url value='/gshop.do?command=adminNoticeList'/>">목록</a>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
        </div>
    <script type="text/javascript" src="<c:url value='/resources/js/admin/qnaView.js'/>"></script>
	</body>
</html>
