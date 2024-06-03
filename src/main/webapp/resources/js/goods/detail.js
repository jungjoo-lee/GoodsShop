
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

// review
function currentTime() {
	let now = new Date();
	let formattedTime = now.getFullYear() + '-' +
                    String(now.getMonth() + 1).padStart(2, '0') + '-' +
                    String(now.getDate()).padStart(2, '0') + ' ' +
                    String(now.getHours()).padStart(2, '0') + ':' +
                    String(now.getMinutes()).padStart(2, '0') + ':' +
                    String(now.getSeconds()).padStart(2, '0');
	return formattedTime;
}

let reviewList = document.querySelector('#reviewList');
let reviewWriteBtn = document.querySelector('#reviewWriteBtn');
reviewWriteBtn.addEventListener("click", () => {
	if(confirm("리뷰 작성하시겠습니까?")) {
		fetch('/GoodsShop/gshop.do?command=asyn', {
			method : 'POST',
			headers: {
				'Content-Type': 'application/json;charset=utf-8'
			},
				body: JSON.stringify({
					"command" : "reviewWrite",
					"gseq" : gseq.value,
					"subject" : subject.value,
					"content" : content.value,
				})
			})
			.then(response => response.json())
			.then(jsonResult => {				
				if (jsonResult.status == true) {
					let vo = jsonResult.vo;
					let li = document.createElement("li");
					
					li.textContent = vo.rseq + ', ' + vo.userid + ', ' + vo.grade + ', ' + vo.subject + ', ' + vo.content + ', ' + currentTime();
					
					let editButton = document.createElement("button");
					editButton.textContent = "수정";
					editButton.classList.add("btn", "btn-primary", "btn-sm");
					editButton.addEventListener("click", () => {
					    // 작성
					});
					
					let deleteButton = document.createElement("button");
					deleteButton.textContent = "삭제";
					deleteButton.classList.add("btn", "btn-danger", "btn-sm");
					deleteButton.addEventListener("click", () => {
					    // 작성
					    reviewList.removeChild(li);
					});
					
					li.appendChild(editButton);
					li.appendChild(deleteButton);
					
					reviewList.prepend(li);
					document.querySelector('#subject').value = '';
					document.querySelector('#content').value = '';
					
					if (reviewList.children.length > 10) {
					    reviewList.removeChild(reviewList.lastElementChild);
					}
					
					alert(jsonResult.message);
				} else {
					alert(jsonResult.message);
				}
		});
	} else {
		return;
	}
});

let reviewDeleteBtn = document.querySelector('#reviewDeleteBtn');
reviewDeleteBtn.addEventListener("click", () => {
	if(confirm("정말로 삭제하시겠습니까?")) {
		alert("삭제되었습니다.");
	}
});