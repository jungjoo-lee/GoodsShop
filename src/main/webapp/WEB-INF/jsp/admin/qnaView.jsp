<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 상세보기</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/admin.css'/>">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
	<body class="sb-nav-fixed">
        <!-- header -->
        <jsp:include page="../fix/admin/header.jsp"/>
        
        <div id="layoutSidenav">
        	<!-- side -->
        	<jsp:include page="../fix/admin/sidemenu.jsp"/>
        	
        	<div id="layoutSidenav_content">
        		<div class="container-fluid px-4">
        			번호 : ${vo.qseq}<br/>
					작성자 : ${vo.userid}<br/>
					제목 : ${vo.subject}<br/>
					내용 : ${vo.content}<br/>
					작성일자 : <fmt:formatDate value="${vo.indate}" type="both" pattern="yyyy-MM-dd HH:mm:SS" /><br/>
					<c:if test="${not empty vo.reply}">답변 : ${vo.reply}</c:if>
					<c:if test="${not empty vo.reply}">답변일자 : <fmt:formatDate value="${vo.replyDate}" type="both" pattern="yyyy-MM-dd HH:mm:SS" /></c:if>
					<form id="replyForm" method="post" action="<c:url value='/gshop.do?command=qnaReplyWrite&qseq=${vo.qseq}'/>">
						<div id="replyContainer">
						</div>
					</form>
						<c:choose>
							<c:when test="${empty vo.reply}">
								<input type="button" class="btn btn-secondary" id="writeBtn" value="답변"/>
							</c:when>
							<c:otherwise>
								<input class="btn btn-primary" id="updateBtn" value="수정"/>
								<input class="btn btn-danger" id="deleteBtn" value="삭제">
							</c:otherwise>
						</c:choose>
					<a class="btn btn-light" href="<c:url value='/gshop.do?command=qna'/>">목록</a>
        		</div>
        	</div>
        </div>
    <script type="text/javascript" src="<c:url value='/resources/js/admin/qnaView.js'/>"></script>
	</body>
</html>