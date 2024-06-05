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
<link rel="stylesheet" href="resources/css/loginForm.css">
<link rel="stylesheet" href="resources/css/member.css">
</head>
<body>

<div id="wrap">
	<header id="header">
		<nav id="top_menu">
			<div class="gnb">
				<div class="left-links">
					<c:choose>
							<c:when test="${empty loginUser}">
									<a href="gshop.do?command=loginForm">로그인</a>
									<a href="gshop.do?command=joinPage">회원가입</a>
							</c:when>
						<c:otherwise>
									<a id="user" href="gshop.do?command=updateMemberForm">${loginUser.name}님 환영합니다</a>
									<a href="gshop.do?command=logout">Logout</a>
									<a href="gshop.do?command=viewCartlist">CART</a>
									<a href="gshop.do?command=updateMemberForm">정보 수정</a>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="right-links">
					<a href="gshop.do?command=noticeList">NOTICE</a>
					<a href="gshop.do?command=qnaList">Q&A</a>
					<a href="gshop.do?command=reviewList">REVIEW</a>
				</div>
			</div>
		</nav>
		<div id="logo"><a href="gshop.do?command=index">FLOWER ON YOU</a></div>
		<nav id="category_menu">
			<div>BEST50</div>
			<div>헤어악세서리</div>
			<div>반지</div>
			<div>목걸이</div>
			<div>귀걸이</div>
			<div>팔찌</div>
			<div>브로치</div>
			<div>모자</div>
			<div>장갑</div>
		</nav>
			<script type="text/javascript" src="<c:url value="/resources/js/goods/header.js" />"></script>
	</header>
	
	
	
	
	
	
	
	
	