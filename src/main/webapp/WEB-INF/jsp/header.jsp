<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/header.css">
<script src="<c:url value='/resources/js/member/userLogin.js'/>"></script>
</head>
<body>

<div id="wrap">

	<header>
		<nav id="top_menu">
			<div class="gnb">
					<c:choose>
							<c:when test="${empty loginUser}">
									<a href="gshop.do?command=loginForm">로그인</a>
									<a href="gshop.do?command=joinPage">회원가입</a>
							</c:when>
						<c:otherwise>
									<a href="#">${loginUser.name}(${loginUser.userid})</a>
									<a href="gshop.do?command=logout">Logout</a>
									<a href="gshop.do?command=updateMemberForm">정보 수정</a>
						</c:otherwise>
					</c:choose>
					<a href="">장바구니</a>
					<a href="">마이페이지</a>
					<a href="">문의하기</a>			
			</div>
			<div class="hmenu">
					<div></div>
					<div></div>
					<div></div>
			</div>
		</nav>
		<div id="logo"><img src="images/main.jpg" width="180" height="100"></div>
		<nav id="category_menu">
			
			<div>1</div>
			<div>2</div>
			<div>3</div>
			<div>4</div>
			<div>5</div>
		</nav>
	</header>
	
	
	
	
	
	
	
	
	