<%@ include file="/WEB-INF/jsp/header.jsp"%>
<section>
		<article>
			<form method="post" action="gshop.do?command=login" name="loginForm">
				<h2>로그인</h2>
				<div class="field">
					<label>아이디</label><input name="userid" type="text" value="hong10"/>
				</div>
				<div class="field">
					<label>비밀번호</label><input name="pwd" type="password" value="10"/>
				</div>
				<div class="btn">
					<input type="submit" value="로그인" onClick="return loginCheck();">
					<input type="button" value="회원가입" onClick="">
					<input type="button" value="아이디 찾기" onClick="return findIdForm();">
					<input type="button" value="비밀번호 찾기" onClick="return findPwdForm();">
				</div>
			</form>
			<div style="font-size:80%; font-weight:bold">${message}</div>
		</article>
</section>
<script src="<c:url value='/resources/js/member/userLogin.js'/>"></script>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>