/*===========로그인================*/
/* 로그인 창 입력 확인*/
function loginCheck(){
	if(document.loginForm.userid.value==""){
		alert("아이디를 입력하세요");
		document.loginForm.userid.focus();
		return false;
	}else if(document.loginForm.pwd.value==""){
		alert("비밀번호를 입력하세요");
		document.loginForm.userid.focus();
		return false;
	}else{
		return true;
	}
}
/* 아이디 찾기 창으로 이동*/
function findIdForm(){
	location.href="gshop.do?command=findIdForm"
}

/* 아이디찾기 */
function findId(){ 
	var name = document.findIdForm.name.value;
    var email = document.findIdForm.email.value;
	if(document.findIdForm.name.value==""){
		alert("이름을 입력하세요");
		document.findIdForm.name.focus();
		return false;
	}else if(document.findIdForm.email.value==""){
		alert("이메일을 입력하세요");
		document.findIdForm.email.focus();
		return false;
	}else{
        document.findIdForm.submit();
    }
}
/* 비밀번호 찾기 창으로 이동*/
function findPwdForm(){
	location.href="gshop.do?command=findPwdForm"
}

function findPwd(){
	var userid = document.findIdForm.userid.value;
    var email = document.findIdForm.email.value;
	if(document.findPwdForm.userid.value==""){
		alert("아이디을 입력하세요");
		document.findPwdForm.userid.focus();
		return false;
	}else if(document.findPwdForm.email.value==""){
		alert("이메일을 입력하세요");
		document.findPwdForm.email.focus();
		return false;
	}else{
        document.findPwdForm.submit();
    }
}

function PwdCodeOK(){
	var userid = document.formm.userid.value;
    var email = document.formm.email.value;
	var securityCodeInput = document.formm.securityCodeInput.value;
	if(document.formm.securityCodeInput.value==""){
		alert("인증코드를 입력하세요");
		document.formm.securityCodeInput.focus();
		return false;
	}else {
		document.formm.submit();
	}

}

function codeOK(){
	var userName = document.formm.name.value;
    var userEmail = document.formm.email.value;
	var securityCodeInput = document.formm.securityCodeInput.value;
	if(document.formm.securityCodeInput.value==""){
		alert("인증코드를 입력하세요");
		document.formm.securityCodeInput.focus();
		return false;
	}else {
		document.formm.submit();
	}

}


/* 회원 정보 수정 */
function go_updateMember(){
	 if(document.updateMemberForm.pwd.value == "") {
   alert("비밀번호를 입력해 주세요.");
   document.updateMemberForm.pwd.focus();
   } else if(document.updateMemberForm.pwd.value != document.updateMemberForm.pwdCheck.value) {
   alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
   document.updateMemberForm.pwd.focus();
   } else if(document.updateMemberForm.name.value == "") {
   alert("이름을 입력해 주세요.");
   document.updateMemberForm.name.focus();
   } else if(document.updateMemberForm.phone.value == "") {
   alert("전화번호를 입력해 주세요.");
   document.updateMemberForm.phone.focus();
   }else if(document.updateMemberForm.email.value == "") {
   alert("이메일을 입력해 주세요.");
   document.updateMemberForm.email.focus();
   }  else{
   document.updateMemberForm.submit();
   }
  }
/* 주소 찾기 창*/
function post_zip(){
	 var url = "gshop.do?command=findZipnum";
  	 var opt = "menubar=no,scrollbars=no,width=550,height=300,top=300,left=300";
   window.open(url, "findZipNum",opt);
}

/* 주소 찾기*/
function addressOK(zip_code , sido, gugun, dong){
   opener.updateMemberForm.zip_code.value =zip_code;
   opener.updateMemberForm.address.value = sido+" " + gugun + " " + dong ;
   self.close();
}
}
	location.href="gshop.do?command=joinPage";
function Join(){