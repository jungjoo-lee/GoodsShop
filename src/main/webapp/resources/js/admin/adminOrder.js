/*function change_status (){
	let changeStat = document.querySelector("#changeStat");
	
	if(changeStat != null){
		if(document.adminOrderForm.osseq.value != '0'){
			changeStat.addEventListener("click", () => {
				
				document.adminOrderForm.action = "gshop.do?command=adminUpdateOrderStatus";
				document.adminOrderForm.method = "post";
				document.adminOrderForm.submit();

			})
		}			
	}	
}
change_status();*/

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