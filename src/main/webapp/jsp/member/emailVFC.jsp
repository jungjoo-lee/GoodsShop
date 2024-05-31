<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="popup">
		3분 이내에 이메일로 전송된 인증번호를 입력해주세요!!
		
		<input type="text" id="vemail" name="vemail" size="14"  placeholder="6자리 코드 입력">
		<input type="button" value="인증하기" name="submit" onClick="m_confirm(${verificationCode})">
</div>
<script src="<c:url value='/resources/js/member.js'/>"></script>
</body>
</html>