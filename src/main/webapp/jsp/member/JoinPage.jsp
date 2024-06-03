<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>


<article>
			<h2>회원가입</h2>
			<h3>기본 정보</h3>
	<form method="post" action="gshop.do?command=join"  name="JoinPage">
		<table>
				<div class="field" >
						<label>아이디 &nbsp;&nbsp;&nbsp;</label>
						<input type="text" name="userid" size="15">
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
						<input type="text" name="name" placeholder="ex) 홍길동">
				</div>
				<div class="field">
						<label>연락처&nbsp;&nbsp;&nbsp;</label>
						<input type="text" name="phone" placeholder="010-1234-5678">
				</div>
				<div class="field">
						<label>이메일&nbsp;&nbsp;&nbsp;</label>
							<input type="text" name="email"  id="email" placeholder="abc@gmail.com">
							<input type="button" value="인증번호 전송" onClick="verify()">
						
				</div>
				<!-- <div class="field">
						<label>본인 인증 코드&nbsp;&nbsp;&nbsp;</label>
						<input type="text" name="v_email" size="14"  placeholder="6자리 코드 입력">
						<input type="button" value="인증하기" onClick="m_confirm(${verificationCode})" readonly="readonly">
				</div> --><hr/>

			<h3>부가 정보</h3>
				<div class="field">
						<label>우편 번호&nbsp;&nbsp;&nbsp;</label>
						<input type="text" name="zip_code" size="10"  readonly placeholder="123-123">
						<input type="button" value="우편번호 찾기" onClick="find_zip()">
				</div>
				<div class="field" >
						<label>주소&nbsp;&nbsp;&nbsp;</label>
						<input type="text" name="address" size=30 readonly >
				</div>
				<div class="field">
						<label>상세 주소&nbsp;&nbsp;&nbsp;</label>
						<input type="text" name="d_address">
				</div><hr/>
				<h3>약관 동의</h3>
				<p class="check">
					<span>약관에 동의하시겠습니까?</span>
						<input type="radio" name="yno"> 동희  &nbsp; &nbsp; &nbsp;
						<input type="radio" name="yno" checked> 시러요
				</p>
				<div class="btn">
						<input type="button" value="가입하기" onClick="go_save()">
						<input type="button" value="처음으로" onClick="gshop.do?command=loginForm">
				</div>
				
		</table>
	</form>
</article>
<script src="<c:url value='/resources/js/member.js'/>"></script>
<%@ include file="/footer.jsp"%>