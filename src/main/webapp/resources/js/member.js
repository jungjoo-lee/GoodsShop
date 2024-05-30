function idcheck(){
	if(document.JoinPage.userid.value==""){
		alert("아이디를 입력하고 중복체크를 진행해주세요!");
		document.JoinPage.userid.focus();
		return;
	}
	var url = "gshop.do?command=IDCheck&userid=" + document.JoinPage.userid.value;
	var opt = "toolbar=no, menubar=no, resizable=no, width=500, height=250, scrollbars=no";
	window.open(url, "IDCheck", opt);
}


function find_zip(){
	var url = "gshop.do?command=findZipnum";
	var opt = "menubar=no, scrollbars=no, width=550, height=300, top=300, left=300";
	window.open(url, "findZipnum", opt);
}

function go_save(){
	if( document.JoinPage.userid.value == ""){
		alert("아이디가 입력되지 않았습니다!");
		document.JoinPage.userid.focus();
	}else if( document.JoinPage.reid.value != document.JoinPage.userid.value){
		alert("아이디 중복확인을 해주세요.");
		document.JoinPage.userid.focus();
	}else if( document.JoinPage.pwd.value == ""){
		alert("비밀번호가 입력되지 않았습니다!");
		document.JoinPage.pwd.focus();
	}else if( document.JoinPage.pwd.value != document.JoinPage.pwdCheck.value){
		alert("비밀번호 확인이 일치하지 않습니다!");
		document.JoinPage.pwd.focus();
	}else if( document.JoinPage.name.value == ""){
		alert("이름을 입력해주세요.");
		document.JoinPage.name.focus();
	}else if( document.JoinPage.phone.value == ""){
		alert("전화번호가 입력되지 않았습니다!");
		document.JoinPage.phone.focus();
	}else if( document.JoinPage.email.value == ""){
		alert("이메일을 입력해주세요.");
		document.JoinPage.email.focus();
	}else if( document.JoinPage.yno[1].checked == true){
		alert("약관 동의를 해주셔야 가입이 가능합니다!");
	}else{
		document.JoinPage.submit();
	}
}
function idok(userid){
	opener.JoinPage.userid.value = userid;
	opener.JoinPage.reid.value = userid;
	self.close();
}

function addressOK( zip_num, sido, gugun, dong ){
	opener.document.JoinPage.zip_code.value=zip_num;
	opener.document.JoinPage.address.value=sido+" "+gugun+" "+dong;
	self.close();
}
function verify(){
	alert("입력하신 이메일로 인증번호가 전송되었습니다.")
	var url = "gshop.do?command=getEmail&email="+email.value;
	var opt = "toolbar=no, menubar=no, resizable=no, width=500, height=250, scrollbars=no";
	window.open(url, "getEmail", opt);
	
}
function send(){
	opener.document.JoinPage.v_email.value=code;
	self.close();
}

function confirm(){
	// if 이메일로 받은 코드값 != 입력된 코드값 > 인증번호가 일치하지 않습니다
	// focus
	// else message : 인증완료
}



