
//go_main
function gomain(){
	let main = document.querySelector("#go_main");
	
	if(main != null){
		main.addEventListener("click", ()=>{
			location.href="gshop.do?command=index";
		})
	}
}

gomain();

//add_cart
function addCart() {

	let addcart = document.querySelector("#add_cart");

	if (addcart != null) {

		addcart.addEventListener("click", function() {
			if (document.goodsform.input_quantity.value == "") {
				alert("수량을 입력하세요");
			} else {
				document.goodsform.action = "gshop.do?command=addCart";
				document.goodsform.submit();
			}
		})
	}
}
addCart();


//add_wishlist
function addWishlist(){
	let addwish = document.querySelector("#add_wishlist");
	
	if (addwish != null){
		addwish.addEventListener("click", function(){
			document.goodsform.action = "gshop.do?command=addWish";
			document.goodsform.submit();			
		})			
	}	
}

addWishlist();



function goorder() {
	let ordernow = document.querySelector("#purchase_now");

	if (ordernow != null) {
		ordernow.addEventListener("click", () => {
			document.goodsform.action = "gshop.do?command=orderNow";
			document.goodsform.submit();

		})
	}
}

goorder();



