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
	if(document.findIdForm.name.value==""){
		alert("이름을 입력하세요");
		document.findIdForm.name.focus();
		return false;
	}else if(document.findIdForm.email.value==""){
		alert("이메일을 입력하세요");
		document.findIdForm.email.focus();
		return false;
	}else{
		document.findIdForm.action = 'gshop.do?command=findId';
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
  
function post_zip(){
	 var url = "gshop.do?command=findZipnum";
  	 var opt = "menubar=no,scrollbars=no,width=550,height=300,top=300,left=300";
   window.open(url, "findZipNum",opt);
}

function addressOK(zip_num , sido, gugun, dong){
   opener.updateMemberForm.zip_num.value = zip_num;
   opener.updateMemberForm.address.value = sido+" " + gugun + " " + dong ;
   self.close();
}

function Join(){
	location.href="gshop.do?command=joinPage";
}







