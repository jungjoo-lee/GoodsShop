<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<link rel="stylesheet" href="resources/css/loginForm.css">
<div>
<section id="loginForm">
		<article id="login">
			<form method="post" action="gshop.do?command=login" name="loginForm">
			<div class="loginbox">
				<div class="logininput">
					<input name="userid" type="text" value="아이디" onfocus="clearPlaceholder(this)" onblur="setPlaceholder(this, '아이디')">
					<input name="pwd" type="text" value="비밀번호" onfocus="clearPlaceholders(this)" onblur="setPlaceholder(this, '비밀번호')">
				</div>
				<div class="loginbtn">
					<input type="submit" value="로그인" onClick="return loginCheck();"><br/>
				</div>
			
				<div class="btn">
					<input type="button" value="아이디 찾기" onClick="return findIdForm();">
					<input type="button" value="비밀번호 찾기" onClick="return findPwdForm();"><br/>
					<input type="button" value="회원가입" onClick="return Join();">
				</div>
				<div class="join-box">
					<div class="join-message">
					아직 회원이 아니라면? <br>
					오브플라워에 가입하고, 더욱 다양한 혜택을 만나보세요! <br>
					</div>
					<input type="button" value="회원가입하기" onclick="location.href='gshop.do?command=joinPage'">
				</div>
			</div>
			</form>
			<div style="font-size:80%; font-weight:bold">${message}</div>
		</article>
</section>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
<script src="<c:url value='/resources/js/member/userLogin.js'/>"></script>
<script src="<c:url value='/resources/js/member/newID.js'/>"></script>
<script src="<c:url value='/resources/js/member/loginForm.js'/>"></script>