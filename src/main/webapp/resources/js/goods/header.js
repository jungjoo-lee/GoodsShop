function go_category (){
	
	for(let i=0; i<=9; i++){
		let goCate = document.querySelector("#categories_"+i)
		if(goCate != null){
			goCate.addEventListener("click", function(){
				location.href="gshop.do?command=viewCategory&cgseq="+i+"&page=1";	
			})
		}
	}	
}

go_category();


function productSearch(){
	let sch = document.querySelector("#search_goods");
	if(sch != null){
		sch.addEventListener("click", ()=>{
			let key = document.querySelector("#search_key").value;
			
			if(key == ''){
				
			} else {
				location.href="gshop.do?command=searchGoods&key="+key;
			}
			
		})
	}
}

productSearch();
