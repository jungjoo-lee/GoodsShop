<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<link rel="stylesheet" href="resources/css/member.css">

<article id="join">
	<h2 >JOIN US</h2>
	<h3>기본 정보</h3>
		<table>
			<div class="field">
				<div class="label">아이디</div>
				<input type="text" name="userid">
				<input type="hidden" name="reid">
				<input	type="button" value="중복 확인" onClick="idcheck()" id="btnn">
			</div>
			<div class="field">
				<div class="label">비밀번호</div>
				<input type="password"	name="pwd">
			</div>
			<div class="field">
				<div class="label">비밀번호 확인</div>
				<input type="password" name="pwdCheck">
			</div>
			<div class="field">
				<div class="label">성명</div>
				<input type="text" name="name"	placeholder="ex) 홍길동">
			</div>
			<div class="field">
				<div class="label">연락처</div>
				<input type="text" name="phone" placeholder="010-1234-5678">
			</div>
			<div class="field">
				<div class="label">이메일</div>
				<input type="text" name="email"	id="email" placeholder="abc@gmail.com">
				<input	type="button" value="인증번호 전송" onClick="verify()" id="btnn">

			</div><hr>


				<h3>부가 정보</h3>
			<div class="field">
				<div class="label">우편 번호</div>
				<input type="text"	name="zip_code" id="zipcode" readonly placeholder="123-123">
				<input type="button" value="우편번호 찾기" onClick="find_zip()" id="btnn">
			</div>
			<div class="field">
				<div class="label">주소</div>
				<input type="text" name="address" size=30 readonly>
			</div>
			<div class="field">
				<div class="label">상세 주소</div>
				<input type="text" name="d_address">
			</div><hr>

			<div id="contract">
				<h3>약관 동의</h3>
				<p class="check">
					■ 수집하는 개인정보 항목<br />
						회사는 회원가입, 상담, 서비스 신청 등등을 위해 아래와 같은 개인정보를 수집하고	있습니다.<br/>
						ο 수집항목 : 이름 , 생년월일 , 성별 , 로그인ID , 비밀번호 , 비밀번호 질문과 답변 , 자택 전화번호 , 자택
						주소 , 휴대전화번호 , 이메일 , 직업 , 회사명 , 부서 , 직책 , 회사전화번호 , 취미 , 결혼여부 , 기념일 ,
						법정대리인정보 , 서비스 이용기록 , 접속 로그 , 접속 IP 정보 , 결제기록<br />
						ο 개인정보 수집방법 : 홈페이지(회원가입) , 서면양식<br /><br/>
							
					■ 개인정보의 수집 및 이용목적<br />
							
						회사는 수집한 개인정보를 다음의 목적을 위해 활용합니다.<br />
							
						ο 서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 요금정산 콘텐츠 제공 , 구매 및 요금 결제 , 물품배송 또는 청구지 등 발송<br />
						ο 회원 관리 : 회원제 서비스 이용에 따른 본인확인 , 개인 식별 , 연령확인 , 만14세 미만 아동 개인정보 수집 시 법정 대리인 동의여부 확인 , 고지사항 전달<br/>
						ο 마케팅 및 광고에 활용 : 접속 빈도 파악 또는 회원의 서비스 이용에 대한 통계<br/><br/>
							
					■ 개인정보의 보유 및 이용기간<br />
						회사는 개인정보 수집 및 이용목적이 달성된 후에는 예외 없이 해당 정보를 지체 없이 파기합니다.
				</p>
					<span>약관에 동의하시겠습니까?</span>
					<div style="display: flex; margin-top: 10px">
							<input type="radio" name="yno"> 동의  &nbsp; &nbsp; &nbsp;
							<input type="radio" name="yno" checked style="margin-left: 20px;"> 미동의
					</div>
				</div>
				<div class="btn" style="display: flex; justify-content: center; margin: 20px">
						<input type="button" id="regi" value="가입하기" onClick="go_save()">
				</div>
			
				
		</table>
	</form>
</article>
<script src="<c:url value='/resources/js/member/newID.js'/>"></script>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>