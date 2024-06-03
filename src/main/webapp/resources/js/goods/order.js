function goOrder (){
	let go_order = document.querySelector("#go_order")
	
	if(go_order!=null){
		go_order.addEventListener("click", function(){
			
			let num = document.orderpageform.numberOfGoods.value
			let totalp = document.orderpageform.orderTotalPrice.value
			
			
			let ans = confirm("총 " + num + " 개의 상품 (총 주문금액 : " + totalp + ") 를 주문합니다. 확인을 누르시면 결제페이지로 이동합니다.");
			if (ans){
				document.orderpageform.action = "gshop.do?command=getPayment";
				document.orderpageform.submit();
			} else {
				alert("주문을 취소합니다.");
			}					
		})	
	}
}

goOrder();