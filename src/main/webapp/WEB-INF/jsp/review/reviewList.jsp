<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰</title>
</head>
<body>
<ul>
<c:forEach var="rev" items="${reviewList}">
	<li>
		${rev.rseq}&nbsp;${rev.giseq}&nbsp;[${rev.category}]${rev.gname}&nbsp;${rev.subject}&nbsp;${rev.content}&nbsp;${rev.grade}&nbsp;${rev.userid}&nbsp;${rev.indate}<br/>
	</li>
</c:forEach>
</ul>
</body>
</html>