
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
					    reviewUpdate();
					});
					
					let deleteButton = document.createElement("button");
					deleteButton.textContent = "삭제";
					deleteButton.classList.add("btn", "btn-danger", "btn-sm");
					deleteButton.addEventListener("click", (e) => {
					    // 작성
					    reviewDelete(e)
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
				} else {
					alert(jsonResult.message);
				}
		});
	} else {
		return;
	}
});

function getRseq(e) {
    let target = e.target;

    if (target.tagName.toLowerCase() === 'i') {
        target = target.closest('button');
    }

    let listItem = target.closest('.list-item');
    
    return listItem.querySelector('.item-num').textContent.replace('no.', '').trim();
}

let reviewUpdateBtn = document.querySelectorAll('.reviewUpdateBtn');
if (reviewUpdateBtn != null) {
	reviewUpdateBtn.forEach(btn => {
		btn.addEventListener("click", (e) => {
			reviewUpdate(e);
		});
	});
}

function reviewUpdate(e) {
	let listItem = e.target.closest('.list-item');
    let subjectElement = listItem.querySelector('.item-subject');
    let contentElement = listItem.querySelector('.item-content-text');
    let titleText = subjectElement.textContent;
    let contentText = contentElement.textContent;
	
	if (listItem.querySelector('.save-btn')) {
	    return;
	}

    subjectElement.innerHTML = `<input type="text" class="form-control" value="${titleText}">`;
    contentElement.innerHTML = `<textarea class="form-control">${contentText}</textarea>`;

    let saveBtn = document.createElement('button');
    saveBtn.textContent = '저장';
    saveBtn.classList.add('btn', 'btn-success', 'btn-sm', 'save-btn');
    saveBtn.addEventListener('click', () => {
        let newTitle = subjectElement.querySelector('input').value;
        let newContent = contentElement.querySelector('textarea').value;

        if(confirm("리뷰 내용을 수정하시겠습니까?")) {
			alert("수정되었습니다.");
		} else {
			return;
		}

        subjectElement.textContent = newTitle;
        contentElement.textContent = newContent;
        saveBtn.remove();
    });

    listItem.querySelector('.buttons').appendChild(saveBtn);
}

let reviewDeleteBtn = document.querySelectorAll('.reviewDeleteBtn');
if (reviewDeleteBtn != null) {
	reviewDeleteBtn.forEach(btn => {
		btn.addEventListener("click", (e) => {
			reviewDelete(e);
		});
	});
}

function reviewDelete(e) {
	if(confirm("정말로 삭제하시겠습니까?")) {
		let rseq = getRseq(e);

		fetch('/GoodsShop/gshop.do?command=asyn', {
			method : 'POST',
			headers: {
				'Content-Type': 'application/json;charset=utf-8'
			},
				body: JSON.stringify({
					"command" : "reviewDelete",
					"rseq" : rseq,
				})
			})
			.then(response => response.json())
			.then(jsonResult => {				
				if (jsonResult.status == true) {
					alert(jsonResult.message);
				} else {
					alert(jsonResult.message);
				}
		});
	} else {
		return;
	}
}