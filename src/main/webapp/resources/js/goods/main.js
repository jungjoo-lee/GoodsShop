function login() {
	let login = document.querySelector("#login");

	if (login != null) {

		login.addEventListener("click", function() {
			location.href = "gshop.do?command=login";
		})
	}
}
login();


function gocart() {
	let gocart = document.querySelector("#go_cart");

	if (gocart != null) {

		gocart.addEventListener("click", function() {
			location.href = "gshop.do?command=viewCartlist";
		});
	}
}
gocart();


function gowishlist(){
	let gowish = document.querySelector("#go_wishlist");
	
	if (gowish != null) {
		gowish.addEventListener("click", ()=>{
			location.href = "gshop.do?command=viewWishlist";
		})
	}
}
gowishlist();