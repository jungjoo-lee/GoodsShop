
//go_main
function goMain(){
	let main = document.querySelector("#go_main");
	
	if(main != null){
		main.addEventListener("click", ()=>{
			location.href="gshop.do?command=index";
		})
	}
}

goMain();

//add_cart
function addCart() {

	let addcart = document.querySelector("#add_cart");

	if (addcart != null) {

		addcart.addEventListener("click", function() {
			if (document.goodsform.input_quantity.value == "") {
				alert("수량을 입력하세요");
			} else {
				document.goodsform.action = "gshop.do?command=addCart";
				document.goodsform.method = "post";
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
			document.goodsform.method = "post";
			document.goodsform.submit();			
		})			
	}	
}

addWishlist();


//go_order
function goOrder() {
	let ordernow = document.querySelector("#purchase_now");

	if (ordernow != null) {
		ordernow.addEventListener("click", () => {
			document.goodsform.action = "gshop.do?command=orderNow";
			document.goodsform.method = "post";
			document.goodsform.submit();

		})
	}
}

goOrder();


//admin - 목록으로
function viewGoodsList (){
	let goodslist = document.querySelector("#go_goodslist");
	
	if (goodslist != null){
		goodslist.addEventListener("click", ()=>{
			document.goodsform.action = "gshop.do?command=adminGoodsView";
			document.goodsform.method = "post";
			document.goodsform.submit();
			
		})
	}
}

viewGoodsList();

//admin - 상품 수정
function update_Goods(){
	let updateGoods = document.querySelector("#update_goods");
	
	if(updateGoods != null){
		updateGoods.addEventListener("click", ()=>{
			document.goodsform.action = "gshop.do?command=adminGoodsUpdateForm";
			document.goodsform.method = "post";
			document.goodsform.submit();
		})
	}	
}

//admin - 상품 추가
function update_Goods(){
	let updateGoods = document.querySelector("#update_goods");
	
	if(updateGoods != null){
		updateGoods.addEventListener("click", ()=>{
			document.goodsform.action = "gshop.do?command=adminGoodsUpdateForm";
			document.goodsform.method = "post";
			document.goodsform.submit();
		})
	}	
}

update_Goods();

//admin - 상품 삭제
function delete_Goods(){
	let deleteGoods = document.querySelector("#delete_goods");
	
	if(deleteGoods != null){
		deleteGoods.addEventListener("click", ()=>{
			document.goodsform.action = "gshop.do?command=adminGoodsDelete";
			document.goodsform.method = "post";
			document.goodsform.submit();
		})		
	}
}

delete_Goods();
