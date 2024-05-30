function login() {
	let s = document.querySelector("#login");

	if (s != null) {

		s.addEventListener("click", function() {
			location.href = "gshop.do?command=login";
		})
	}
}
login();


function gocart() {
	let g = document.querySelector("#go_cart");

	if (g != null) {

		g.addEventListener("click", function() {
			location.href = "gshop.do?command=viewCartlist";
		});
	}
}
gocart();
