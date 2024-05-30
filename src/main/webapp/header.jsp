<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>


<div id="wrap">

	<header>
		<nav id="top_menu">
			<div id="logo"><img src="images/logo.png" width="180" height="100"></div>
			<div class="gnb">
					<c:choose>
							<c:when test="${empty loginUser}">
									<a href="shop.do?command=loginForm">Log-In</a>
									<a href="shop.do?command=JoinForm">Join</a>
							</c:when>
						<c:otherwise>
									<a href="#">${loginUser.name}(${loginUser.userid})</a>
									<a href="shop.do?command=logout">Logout</a>
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
			
			<div>1</div>
			<div>2</div>
			<div>3</div>
			<div>4</div>
			<div>5</div>
		</nav>
	</header>
	
	
	
	
	
	
	
	
	