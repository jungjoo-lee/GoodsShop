<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="resources/js/member/member.js"></script>
<%@ include file="/header.jsp"%>

<section>
		<article>
			<form method="post" action="gshop.do?command=login" name="loginForm">
				<h2>로그인</h2>
				<div class="field">
					<label>아이디</label><input name="userid" type="text"/>
				</div>
				<div class="field">
					<label>비밀번호</label><input name="pwd" type="password"/>
				</div>
				<div class="btn">
					<input type="submit" value="LOGIN" onClick="return loginCheck();">
					<input type="button" value="JOIN" onClick="return Join();">
					<input type="button" value="FIND ID" onClick="return findIdForm();">
				</div>
			</form>
			<div style="font-size:80%; font-weight:bold">${message}</div>
		</article>
</section>
<%@ include file="/footer.jsp"%>
