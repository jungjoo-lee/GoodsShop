<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 작성하기</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
</head>
<body>
<c:if test="${not empty vo}">
	<div id="qseq">${vo.qseq}</div>
</c:if>
<input type="text" name="subject" id="subject" value="${vo.subject}"/>
<textarea name="content" id="content" rows="" cols="">${vo.content}</textarea>

<c:choose>
	<c:when test="${not empty vo}">
		<button name="updateBtn" id="updateBtn">수정</button>
	</c:when>
	<c:otherwise>
		<button name="writeBtn" id="writeBtn">작성하기</button>
	</c:otherwise>
</c:choose>
<button>목록</button>
<script type="text/javascript" src="<c:url value='/resources/js/qna/qnaWrite.js'/>"></script>
</body>
</html>