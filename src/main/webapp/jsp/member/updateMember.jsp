<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section>

		<article>
			<form action="gshop.do?command=updateMember" method="post" name="updateMemberForm">
			<h3>회원정보 수정</h3>
			<div class="field">
				<label>아이디</label>
				<div>
					<input type="text" name="userid" value="${loginUser.userid}" readonly>
				</div>
			</div>
			<div class="field">
				<label>비밀번호</label><input type="password" name="pwd">
			</div>
			<div class="field">
				<label>비밀번호 확인</label><input type="password" name="pwdCheck">
			</div>
			<div class="field">
				<label>이름</label><input type="text" name="name" value="${loginUser.name}">
			</div>
			<div class="field">
				<label>핸드폰</label><input type="text" name="phone" value="${loginUser.phone}">
			</div>
			<div class="field">
				<label>이메일</label><input type="text" name="email" value="${loginUser.email}">
			</div>
			
			<h3>정보 수정</h3>
			<div class="field">
				<label>우편번호 검색</label>
				<div>
					<input type="text" name="zip_code" readonly " >
					<input type="button" onClick="post_zip();" value="우편번호 찾기">
				</div>
			</div>
			<div class="field">
				<label>주소</label><input type="text" name="address" value="${loginUser.address},${loginUser.d_address}" readonly>
			</div>
			<div class="field">
				<label>상세 주소</label><input type="text" name="d_address" >
			</div>
			
			<div class="btn">
				<input type="button" value="회원정보 수정" onClick="return go_updateMember()"/>
			</div>
		</form>
		</article>
</section>
</article>
<script src="<c:url value='/resources/js/member/userLogin.js'/>"></script>
</body>
</html>