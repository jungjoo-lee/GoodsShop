let selectAmount = document.querySelector("#selectAmount");
let amount = 10;
let currentPage = 1;
let pages = document.querySelectorAll('.page-link');
let pagination1 = document.querySelector("#pagination");
let startPage = 1;
let realEnd;
let paging;

document.addEventListener("DOMContentLoaded", function() {
    getPageInfo();
});

function getPageInfo() {
	let param = {
		"command" : "pageInfo",
	};
	
	fetch('/GoodsShop/gshop.do?command=asyn', {
		method : 'POST',
		headers: {
			'Content-Type': 'application/json;charset=utf-8'
		},
			body: JSON.stringify(param)
		})
		.then(response => response.json())
		.then(jsonResult => {
			if (jsonResult.status == true) {
				paging = jsonResult.paging;
				console.log(paging);
			}
	});
}

selectAmount.addEventListener("change", () => {
	amount = selectAmount.options[selectAmount.selectedIndex].value;
	currentPage = 1;
	
	if (amount == 0) {
		return;
	} else {
		updateContent();
	}
})

function addPagingEvent() {
	pages = document.querySelectorAll('.page-link');
	pagination1 = document.querySelector("#pagination");
	
	pages.forEach(page => {
		page.addEventListener("click", (e) => {
			if (e.target.getAttribute('data-value') == "prev") {
				currentPage = startPage - 10;
			} else if (e.target.getAttribute('data-value') == "next") {
				currentPage = startPage + 10;
			} else {
				currentPage = e.target.getAttribute('data-value');
			}
			updateContent();
		});
	});
}
addPagingEvent();

function updateContent() {
	let param = {
		"amount" : amount,
		"page" : currentPage,
	};
		
	fetch('/WEB12_ShoesShop/admin/updateContent.do', {
		method : 'POST',
		headers: {
			'Content-Type': 'application/json;charset=utf-8'
		},
			body: JSON.stringify(param)
		})
		.then(response => response.json())
		.then(jsonResult => {
			if (jsonResult.status == true) {
				let memberList = jsonResult.memberList;
				let content = '';
				let i = 0;
				
				memberList.forEach(() => {
					content += '<tr>';
					content += '<td>' + memberList[i].userid + '</td>';
					content += '<td>' + memberList[i].name + '</td>';
					content += '<td>' + memberList[i].phone + '</td>';
					content += '<td>' + memberList[i].email + '</td>';
					content += '<td>' + memberList[i].zip_num + '</td>';
					content += '<td>' + memberList[i].address1 + '</td>';
					content += '<td>' + memberList[i].address2 + '</td>';
					content += '<td>' + memberList[i].indate + '</td>';
					content += '<td class="text-center">';
					if (memberList[i].useyn === 1) {
						content += 'Y';
					} else {
						content += 'N';
					}
					content += '</td>';
					content += '<td class="text-center"><input class="form-check-input" type="checkbox" value="' + memberList[i++].userid + '"></td>';
					content += '</tr>';
				});
				document.querySelector("#memberList").innerHTML = content;
				
				let page = jsonResult.page;
				startPage = page.startPage;
				currentPage = page.currentPage;
				let pagination = '';
				realEnd = page.realEnd;
				
				if (page.prev) {
					pagination += '<li class="page-item">';
					pagination += '<a class="page-link" data-value="prev">Prev</a>';
					pagination += '</li>';
				} else {
					pagination += '<li class="page-item disabled">';
					pagination += '<a class="page-link">Prev</a>';
					pagination += '</li>';
				}
				
				for(let j = startPage; j <= page.endPage; j++) {
					if(page.currentPage == j) {
						pagination += '<li class="page-item active">';
						pagination += '<a class="page-link" data-value="' + j + '">';
						pagination += j;
						pagination += '</a>';
						pagination += '</li>';
					} else {
						pagination += '<li class="page-item">';
						pagination += '<a class="page-link" data-value="' + j + '">';
						pagination += j;
						pagination += '</a>';
						pagination += '</li>';
					}
				}
				
				if (page.next) {
					pagination += '<li class="page-item">';
					pagination += '<a class="page-link" data-value="next">Next</a>';
					pagination += '</li>';
				} else {
					pagination += '<li class="page-item disabled">';
					pagination += '<a class="page-link">Next</a>';
					pagination += '</li>';
				}
				pagination += '<li class="list-group-item d-flex align-items-center">';
				pagination += '<span class="form-text" style="margin-top: 0; margin-left: 20px">';
				pagination += page.currentPage + '/' + page.realEnd;
				pagination += '</span>';
				pagination += '</li>';
				document.querySelector("#pagination").innerHTML = pagination;
				
				addPagingEvent();
			} else {
				alert(jsonResult.message);
			}
	});
}

let quickMove = document.querySelector("#quickMove");
quickMove.addEventListener("keydown", (e) => {
    if (e.key === "Enter" || e.keyCode === 13) {
        if (quickMove.value !== "" && quickMove.value !== null) {
			if (quickMove.value > realEnd) {
				alert("마지막페이지 보다 넘게 이동 할 수 없습니다.");
            } else {
				currentPage = quickMove.value;
            	updateContent();
            	quickMove.value = '';
			}
        }
    }
});