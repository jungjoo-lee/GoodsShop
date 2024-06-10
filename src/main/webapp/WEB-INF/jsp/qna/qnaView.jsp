<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 상세보기</title>
</head>
<body>
	번호 : <div id="qseq">${vo.qseq}<br/></div>
	작성자 : ${vo.userid}<br/>
	제목 : ${vo.subject}<br/>
	내용 : ${vo.content}<br/>
	작성일자 : ${vo.indate}<br/>
	답변 : ${vo.reply}<br/>
	답변일자 : ${vo.replyDate}<br/>
	<br/>
	<c:if test="${vo.userid eq loginUser.userid}">
		<a href="gshop.do?command=qnaWriteForm&qseq=${vo.qseq}">수정</a>
		<button name="deleteBtn" id="deleteBtn">삭제</button>
	</c:if>
	<button>목록</button>
<script type="text/javascript" src="<c:url value='/resources/js/qna/qnaView.js'/>"></script>
</body>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</html>