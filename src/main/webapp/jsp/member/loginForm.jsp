<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<link rel="stylesheet" href="resources/css/loginForm.css">
<section id="loginForm">
		<article id="login">
			<form method="post" action="gshop.do?command=login" name="loginForm">
				<h2>LOGIN</h2>
				<div class="field">
					<input name="userid" type="text" value="아이디" onfocus="clearPlaceholder(this)" onblur="setPlaceholder(this, '아이디')">
				</div>
				<div class="field">
					<input name="pwd" type="text" value="비밀번호" onfocus="clearPlaceholders(this)" onblur="setPlaceholder(this, '비밀번호')">
				</div>
				<div class="btn">
					<input type="submit" value="로그인" onClick="return loginCheck();"><br/>
					<input type="button" value="아이디 찾기" onClick="return findIdForm();">
					<input type="button" value="비밀번호 찾기" onClick="return findPwdForm();"><br/>
					<input type="button" value="회원가입" onClick="">
				</div>
			</form>
			<div style="font-size:80%; font-weight:bold">${message}</div>
		</article>
</section>
<script src="<c:url value='/resources/js/member/userLogin.js'/>"></script>
<script src="<c:url value='/resources/js/member/loginForm.js'/>"></script>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>