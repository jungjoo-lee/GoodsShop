<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>


<article>
			<h2>회원가입</h2>
			<h3>기본 정보</h3>
	<form method="post" action="gshop.do?command=join"  name="JoinPage">
		<table border="1">
				<div class="field" >
						<label>아이디 &nbsp;&nbsp;&nbsp;</label>
						<input type="text" name="userid" size="20">
						<input type="hidden" name="reid">
						<input type="button" value="중복 확인" onClick="idcheck()">
				</div>
				<div class="field">
						<label>비밀번호&nbsp;&nbsp;&nbsp;</label>
						<input type="password" name="pwd">
				</div>
				<div class="field">
						<label>비밀번호 확인</label>
						<input type="password" name="pwdCheck">
				</div>
				<div class="field">
						<label>성명&nbsp;&nbsp;&nbsp;</label>
						<input type="text" name="name">
				</div>
				<div class="field">
						<label>연락처&nbsp;&nbsp;&nbsp;</label>
						<input type="text" name="phone">
				</div>
				<div class="field">
						<label>이메일&nbsp;&nbsp;&nbsp;</label>
						<input type="text" name="email">
				</div>
				<div class="field">
						<label>본인 인증 코드&nbsp;&nbsp;&nbsp;</label>
						<input type="text" name="v_email">
				</div>				

			<h3>부가 정보</h3>
				<div class="field">
						<label>우편 번호&nbsp;&nbsp;&nbsp;</label>
						<input type="text" name="zip_code" readonly>
						<input type="button" value="우편번호 찾기" onClick="find_zip()">
				</div>
				<div class="field">
						<label>주소&nbsp;&nbsp;&nbsp;</label>
						<input type="text" name="address" readonly>
				</div>
				<div class="field">
						<label>상세 주소&nbsp;&nbsp;&nbsp;</label>
						<input type="text" name="d_address">
				</div>
				<div class="btn">
						<input type="button" value="가입하기" onClick="go_save()">
						<input type="button" value="메인으로" 
									onClick="#">
				</div>
		</table>
	</form>
</article>
<script src="<c:url value='/resources/js/member.js'/>"></script>
<%@ include file="/footer.jsp"%>