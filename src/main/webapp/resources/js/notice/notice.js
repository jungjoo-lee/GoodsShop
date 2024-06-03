let paging;
let selectAmount = document.querySelector("#selectAmount");
let pages = document.querySelectorAll('.page-link');

document.addEventListener('DOMContentLoaded', () => {   
    getPageInfo();
});

function getPageInfo() {	
	fetch('/GoodsShop/gshop.do?command=asyn', {
		method : 'POST',
		headers: {
			'Content-Type': 'application/json;charset=utf-8'
		},
			body: JSON.stringify({"command" : "pageInfo", "table" : "notice"})
		})
		.then(response => response.json())
		.then(jsonResult => {
			if (jsonResult.status == true) {
				paging = jsonResult.paging;
				
				for (let k = 0; k < selectAmount.length; k++){  
			    	if(selectAmount.options[k].value == jsonResult.paging.amount){
			    		selectAmount.options[k].selected = true;
			    	}
			  	}
			}
	});
}

// 이 밑으로 다 고쳐야함
selectAmount.addEventListener("change", () => {
	paging.amount = parseInt(selectAmount.options[selectAmount.selectedIndex].value);
	
	let realEnd = Math.ceil(paging.total / paging.amount);
	
	if (realEnd < paging.currentPage) {
		if (realEnd <= 0) {
			paging.currentPage = 1;
		} else {
			paging.currentPage = realEnd;
		}
	}
	
	if (paging.amount == 0) {
		return;
	} else {
		asynGetContent();
	}
})

function addPagingEvent() {
	pages = document.querySelectorAll('.page-link');
	
	pages.forEach(page => {
		page.addEventListener("click", (e) => {
			if (e.target.getAttribute('data-value') == "prev") {
				paging.currentPage = parseInt(paging.startPage - 10);
			} else if (e.target.getAttribute('data-value') == "next") {
				paging.currentPage = parseInt(paging.startPage + 10);
			} else {
				paging.currentPage = parseInt(e.target.getAttribute('data-value'));
			}
			asynGetContent();
		});
	});
}
addPagingEvent();

function formatDate(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

function asynGetContent() {
	let param = {
		"command" : "getContent",
		"table" : "notice",
		"amount" : paging.amount,
		"page" : paging.currentPage,
/*		"search" : search,
		"keyword" : keyword,*/
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
				let contentList = jsonResult.content;
				let content = '';
				let i = 0;
				
				contentList.forEach(() => {
					content += '<li class="notice-item">';
					content += '<div class="d-flex justify-content-center align-items-center">';
					content += '<div>' + contentList[i].nseq + '</div>';
					content += '<div>' + contentList[i].adminId + '</div>';
					content += '<div>' + contentList[i].subject + '</div>';
					content += '<div>' + contentList[i].content + '</div>';
					content += '<div>' + formatDate(contentList[i++].indate) + '</div>';
					content += '</div>';
					content += '</li>';
				});
				document.querySelector("#notice-list").innerHTML = content;
				
				paging = jsonResult.paging;
				let pagination = '';
				
				if (paging.prev) {
					pagination += '<li class="page-item">';
					pagination += '<a class="page-link" data-value="prev">Prev</a>';
					pagination += '</li>';
				} else {
					pagination += '<li class="page-item disabled">';
					pagination += '<a class="page-link">Prev</a>';
					pagination += '</li>';
				}
				
				for(let j = paging.startPage; j <= paging.endPage; j++) {
					if(paging.currentPage == j) {
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
				
				if (paging.next) {
					pagination += '<li class="page-item">';
					pagination += '<a class="page-link" data-value="next">Next</a>';
					pagination += '</li>';
				} else {
					pagination += '<li class="page-item disabled">';
					pagination += '<a class="page-link">Next</a>';
					pagination += '</li>';
				}
				document.querySelector("#pagination").innerHTML = pagination;				
				document.querySelector("#pagdInfo").innerHTML = paging.currentPage + ' / ' + paging.realEnd;
				
				addPagingEvent();
			} else {
				alert(jsonResult.message);
			}
	});
}