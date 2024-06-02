
function wishtocart(){

	let wish = document.querySelector("#wish_to_cart");
	
	if(wish!=null){
		wish.addEventListener("click", ()=>{
			
			let count = 0;
			let gseq = document.wishlistform.gseq;
				
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
				alert("장바구니에 추가할 상품을 선택하세요");			
			} else {
				document.wishlistform.action = "gshop.do?command=wishToCart";
				document.wishlistform.submit();
			}			
		})
	}
}

wishtocart();

function deletewish(){
	let deletewish = document.querySelector("#wish_delete");
	if (deletewish != null){
		deletewish.addEventListener("click", ()=>{
			
			let count = 0;
			let gseq = document.wishlistform.gseq;
			
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
				alert("찜목록에서 삭제할 상품을 선택하세요");			
			} else {
				document.wishlistform.action = "gshop.do?command=deleteWish";
				document.wishlistform.submit();
			}				
			
		})			
	}
}

deletewish();
