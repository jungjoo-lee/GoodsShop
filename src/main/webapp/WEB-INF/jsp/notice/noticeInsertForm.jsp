<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/admin.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/admin/noticeInsert.css'/>">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
	<body class="sb-nav-fixed">
        <!-- header -->
        <jsp:include page="../fix/admin/header.jsp"/>
        
        <div id="layoutSidenav">
        	<!-- side -->
        	<jsp:include page="../fix/admin/sidemenu.jsp"/>
        	
        	<div id="layoutSidenav_content">
	        	<div class="col-lg-8 col-md-10">
	                <div class="card card-custom">
	                    <h4 class="mb-3">Notice Insert</h4>
	                    <form id="layoutSidenav_content" method="post" action="<c:url value='/gshop.do?command=noticeInsert&nseq=${vo.nseq}'/>">
	                        <div class="field">
	                            <label for="subject">제목</label>
	                            <input type="text" id="subject" name="subject" value="${vo.subject}" />
	                        </div>
	                        <div class="field">
	                            <label for="content">내용</label>
	                            <textarea id="content" name="content">${vo.content}</textarea>
	                        </div>
	                        <div class="field">
	                            <label>작성일자</label>
	                            <div><fmt:formatDate value="${vo.indate}" type="both" pattern="yyyy-MM-dd HH:mm:SS" /></div>
	                        </div>
	                        <div class="btn-group btn-group-custom">
	                            <button type="submit" class="btn btn-primary btn-custom">등록</button>
	                            <a class="btn btn-secondary btn-custom" href="<c:url value='/gshop.do?command=adminNoticeList'/>">목록</a>
	                        </div>
	                    </form>
	                </div>
	            </div>
        	</div>
        </div>
	</body>
</html>
