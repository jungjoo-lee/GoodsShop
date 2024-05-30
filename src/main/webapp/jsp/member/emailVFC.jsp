<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="popup">
	<form method="post"  action="gshop.do?command=getEmail&email">
		전송된 코드 입력 : <input type="text" name="code">
		<input type="button" value="인증하기" class="submit" onClick="send()">
	</form>
</div>
<script src="<c:url value='/resources/js/member.js'/>"></script>
</body>
</html>