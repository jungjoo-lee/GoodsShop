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

function findIdForm(){
	location.href="gshop.do?command=findIdForm"
}

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