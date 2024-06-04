function marginCal(){
	let cal_Margin = document.querySelectorAll("#prices");
	
	if(cal_Margin != null){		
		cal_Margin.forEach(function(e){
			e.addEventListener("keyup", ()=>{
			let oprice = document.goodsWriteForm.oprice.value;
			let sprice = document.goodsWriteForm.sprice.value;
			document.goodsWriteForm.mprice.value = sprice-oprice;		
			})			
		})	
	}
}

marginCal();




function go_update(){
	let goUpdate = document.querySelector("#admingoods_update");
	if(goUpdate != null){
		goUpdate.addEventListener("click", ()=>{
			document.goodsWriteForm.action = "gshop.do?command=adminUpdateGoods";
			document.goodsWriteForm.method = "post";
			document.goodsWriteForm.submit();
		})			
	}
}

go_update();





function go_insert(){
	let goInsert = document.querySelector("#admingoods_insert");
	if(goInsert != null){
		goInsert.addEventListener("click", ()=>{
			document.goodsWriteForm.action = "gshop.do?command=adminInsertGoods";
			document.goodsWriteForm.method = "post";
			document.goodsWriteForm.submit();
		})			
	}
}

go_insert();


