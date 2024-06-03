function orderDetail (){
	let od = document.querysquerySelector("#orderlist");
	if (od != null){
		od.addEventListener("click", function(){
			document.orderlistForm.action = "gshop.do?command=orderDetailView";		
			document.orderlistForm.submit();			
		})
	}	
}

orderDetail();