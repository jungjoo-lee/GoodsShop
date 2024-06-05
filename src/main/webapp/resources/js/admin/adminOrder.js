function change_status(){
    let changeSt = document.querySelector("#changeStat");
    let osseqSelect = document.querySelector("#osseq");

    osseqSelect.addEventListener("change", function() {
        if (osseqSelect.value != '0') {
            changeSt.addEventListener("click", ()=>{
                document.adminOrderForm.action = "gshop.do?command=adminUpdateOrderStatus";
                document.adminOrderForm.method = "post";
                document.adminOrderForm.submit();
            });
        } else {
            changeSt.removeEventListener("click", ()=>{});
        }
    });
}

change_status();


function search_by(){
	let goSearch = document.querySelector("#goSearch");
	
	goSearch.addEventListener("click",()=>{
		document.adminOrderForm.action = "gshop.do?command=adminSearchOrder";
		document.adminOrderForm.method = "post";
		document.adminOrderForm.submit();
	})
}
search_by();

function enter_Search(){
	let input = document.querySelector("#searchKey");
	
	input.addEventListener("keypress", (e)=>{
		if(e.key === "Enter"){
			document.adminOrderForm.action = "gshop.do?command=adminSearchOrder";
			document.adminOrderForm.method = "post";
			document.adminOrderForm.submit();
		}
	})	
}

enter_Search();

function preventSubmit(){
	let form = document.querySelector("#adminOrderForm");
	form.addEventListener("keypress", (e)=>{
		if(e.key === "Enter"){
			e.preventDefault();
		}	
	})	
}

preventSubmit();