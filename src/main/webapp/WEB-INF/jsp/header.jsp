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
<link rel="stylesheet" href="resources/css/member.css">
</head>
<body>

<div id="wrap">
	<header>
		<nav id="top_menu">
			<div class="gnb">
				<div class="left-links">
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
				</div>
				<div class="right-links">
					<a href="">NOTICE</a>
					<a href="">Q&A</a>
					<a href="">REVIEW</a>
				</div>
			</div>
		</nav>
		<div id="logo"><a href="gshop.do?command=loginForm">FLOWER ON YOU</a></div>
			<nav>
				<div id="categories_1">BEST50</div>
				<div id="categories_2">헤어악세서리</div>
				<div id="categories_3">반지</div>
				<div id="categories_4">목걸이</div>
				<div id="categories_5">귀걸이</div>
				<div id="categories_6">팔찌</div>
				<div id="categories_7">브로치</div>
				<div id="categories_8">모자</div>
				<div id="categories_9">장갑</div>
			</nav>			
			<script type="text/javascript" src="<c:url value="/resources/js/goods/header.js" />"></script>
	</header>
	
	
	
	
	
	
	
	
	