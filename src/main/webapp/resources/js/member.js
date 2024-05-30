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

function go_next(){
	
	if( document.JoinPage.okon[1].checked == true){
		alert("약관에 동의하지 않으면 가입하실 수 없습니다!!");
	}else{
		document.JoinPage.submit();
	}
}

function find_zip(){
	if(document.JoinPage.zip_code.value==""){
		alert("주소가 입력되지 않았습니다!!");
		document.JoinPage.zip_code.focus();
		return;
	}
	var url = "shop.do?command=findZipno";
	var opt = "menubar=no, scrollbars=no, width=550, height=300, top=300, left=300";
	window.open(url, "findZipno", opt);
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
	}else{
		document.JoinPage.submit();
	}
}
function idok(userid){
	opener.JoinPage.userid.value = userid;
	opener.JoinPage.reid.value = userid;
	self.close();
}
