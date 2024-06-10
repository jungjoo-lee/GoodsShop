<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/admin.css'/>">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
	<body class="sb-nav-fixed">
        	<form id="layoutSidenav_content"  method="post" >
        		<div class="container-fluid px-4">
        		<h1>공지사항</h1>
        			번호 : ${vo.nseq}<br/>
					작성자 : ${vo.adminId}<br/>
					제목 : ${vo.subject}<br/>
					내용 : ${vo.content}<br/>
					작성일자 : <fmt:formatDate value="${vo.indate}" type="both" pattern="yyyy-MM-dd HH:mm:SS" /><br/>
        		</div>
        		<a class="btn btn-light" href="<c:url value='/gshop.do?command=noticeList'/>">목록</a>
        	</form>
        </div>

    <script type="text/javascript" src="<c:url value='/resources/js/admin/qnaView.js'/>"></script>
	</body>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</html>
