function payment(){
	let pay = document.querySelector("#get_payment")
	
	if(pay != null){
		pay.addEventListener("click", function(){
			document.paymentform.action = "gshop.do?command=goOrder";
			document.paymentform.submit();
		})
	}	
}
payment();