<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/member.css">


</head>
<body>


<div id="wrap">

	<header>
		<nav id="top_menu">
			<div id="logo"><img src="resources/image/0507.jpg" width="270" height="200"></div>
			<div class="gnb">
					<c:choose>
							<c:when test="${empty loginUser}">
									<a href="gshop.do?command=loginForm">Log-In</a>
									<a href="gshop.do?command=joinPage">Join</a>
							</c:when>
						<c:otherwise>
									<a href="#">${loginUser.name}(${loginUser.userid})</a>
									<a href="gshop.do?command=logout">Logout</a>
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
		<nav id="category_menu">
			
			<div>Temporary Space Check</div>
			<hr>
		</nav>
	</header>
	
	
	
	
	
	
	
	
	