<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Of Flower</title>
<link rel="stylesheet" href="resources/css/header_footer2.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
</head>

<body>
	<header class="header">
		<div id="wrap">
			<div id="top_menu">
				<div class="gnb">
					<div class="w-5"></div>
					<div class="left-links">
						<c:choose>
							<c:when test="${empty loginUser}">
								<a href="gshop.do?command=loginForm">로그인</a>
								<a href="gshop.do?command=joinPage">회원가입</a>
							</c:when>
							<c:otherwise>
								<a href="#">${loginUser.name}(${loginUser.userid})</a>
								<a href="gshop.do?command=logout">로그아웃</a>
								<a href="gshop.do?command=updateMemberForm">정보수정</a>
								<a href="gshop.do?command=viewCartlist">장바구니</a>
								<a href="gshop.do?command=viewWishlist">찜목록</a>
								<a href="gshop.do?command=viewOrderList">주문목록</a>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="right-links">
						<a href="gshop.do?command=noticeList">NOTICE</a> <a
							href="gshop.do?command=qnaList">Q&A</a> <a
							href="gshop.do?command=reviewList">REVIEW</a>
					</div>
					<div class="w-5"></div>
				</div>
			</div>
			<div id="logo">
				<a href="gshop.do?command=index"> <img
					src="<c:url value='/resources/image/logo_.png'/>" alt="">
				</a>
			</div>
			<div class="menu_bar">
				<div id="categories_0">BEST50</div>
				<div id="categories_1">헤어악세서리</div>
				<div id="categories_2">반지</div>
				<div id="categories_3">목걸이</div>
				<div id="categories_4">귀걸이</div>
				<div id="categories_5">팔찌</div>
				<div id="categories_6">브로치</div>
				<div id="categories_7">모자</div>
			</div>
			<div class="header_searchbar">
				<input type="text" id="search_key" class="search-input" placeholder="상품 이름을 입력해주세요">
				<input id="search_goods" type="button" class="search-button"
					value="검색">
			</div>
		</div>
	</header>
<script src="<c:url value='/resources/js/goods/header.js'/>"></script>
</body>