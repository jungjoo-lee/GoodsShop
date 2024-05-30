
//add_cart
function addCart(){
	document.querySelector("#add_cart").addEventListener("click", function(){
		if (document.goodsform.input_quantity.value == "") {
			alert("수량을 입력하세요");
		} else {
			document.goodsform.action = "gshop.do?command=addCart";
			document.goodsform.submit();
		}
	})
}
addCart();








