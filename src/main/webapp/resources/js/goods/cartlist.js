
function gomain(){
	let main = document.querySelector("#go_main");
	
	if(main != null){
		main.addEventListener("click", ()=>{
			location.href="gshop.do?command=index";
		})
	}
}

gomain();


function deletecart(){

	let wish = document.querySelector("#cart_delete");
	
	if(wish!=null){
		wish.addEventListener("click", ()=>{
			
			let count = 0;
			let gseq = document.cartlistform.gseq;
				
			if(gseq.length == undefined){
				if(gseq.checked == true){
					count++;
				}		
			} else {
				for(let i=0; i<gseq.length; i++){
					if(gseq[i].checked == true){
						count++;
					}
				}
			}
			
			if(count == 0){
				alert("삭제할 상품을 선택해주세요");			
			} else {
				document.cartlistform.action = "gshop.do?command=deleteCart";
				document.cartlistform.submit();
			}			
		})
	}
}

deletecart();


function goorder(){
	let cartorder = document.querySelector("#cart_order");
	
	if(cartorder != null){
		cartorder.addEventListener("click", ()=>{
						let count = 0;
			let gseq = document.cartlistform.gseq;
				
			if(gseq.length == undefined){
				if(gseq.checked == true){
					count++;
				}		
			} else {
				for(let i=0; i<gseq.length; i++){
					if(gseq[i].checked == true){
						count++;
					}
				}
			}
			
			if(count == 0){
				alert("주문할 상품을 선택하세요");			
			} else {
				document.cartlistform.action = "gshop.do?command=orderFromCart";
				document.cartlistform.submit();
			}				
		})		
	}
}

goorder();
