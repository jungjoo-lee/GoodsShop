<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="popup">
		<form method="post" name="formm">
						<label>본인 인증 코드&nbsp;&nbsp;&nbsp;</label>
						<input type="text" name="securityCodeInput" id="securityCodeInput">
						<button id="btn">인증</button>
		</form>	
</div>
<script type="text/javascript">
document.getElementById('btn').addEventListener('click', () => {
	document.formm.action = 'gshop.do?command=findIdOK&securityCodeInput=' + securityCodeInput.value;
	document.formm.submit();
});

</script>

