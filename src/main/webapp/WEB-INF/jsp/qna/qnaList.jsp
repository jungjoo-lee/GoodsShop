<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
</head>
<body>
<c:forEach var="qna" items="${qnaList}">
	${qna.qseq}&nbsp;${qna.subject}&nbsp;${qna.content}&nbsp;${qna.userid}&nbsp;${qna.indate}&nbsp;${qna.replyDate}<br/>
</c:forEach>
</body>
</html>