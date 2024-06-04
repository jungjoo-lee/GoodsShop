function go_category (){
	
	for(let i=0; i<=9; i++){
		let goCate = document.querySelector("#categories_"+i)
		if(goCate != null){
			goCate.addEventListener("click", function(){
				location.href="gshop.do?command=viewCategory&cgseq="+i;	
			})
		}
	}	
}

go_category();


