let selectAmount = document.querySelector("#selectAmount");
let pages = document.querySelectorAll('.page-link');
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
				
				for (let k = 0; k < selectAmount.length; k++){  
			    	if(selectAmount.options[k].value == jsonResult.paging.amount){
			    		selectAmount.options[k].selected = true;
			    	}
			  	}
			}
	});
}

selectAmount.addEventListener("change", () => {
	paging.amount = parseInt(selectAmount.options[selectAmount.selectedIndex].value);
	
	let realEnd = Math.ceil(paging.total / paging.amount);
	
	if (realEnd < paging.currentPage)
		paging.currentPage = realEnd;
	
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
				paging.currentPage = paging.startPage - 10;
			} else if (e.target.getAttribute('data-value') == "next") {
				paging.currentPage = paging.startPage + 10;
			} else {
				paging.currentPage = e.target.getAttribute('data-value');
			}
			asynGetContent();
		});
	});
}
addPagingEvent();

let quickMove = document.querySelector("#quickMove");

function formatDate(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}. ${month}. ${day}.`;
}

function asynGetContent() {
	let param = {
		"command" : "getContent",
		"amount" : paging.amount,
		"page" : paging.currentPage,
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
					content += '<tr>';
					content += '<td class="text-center">';
					if (contentList[i].reply == null)
						content += '(미처리)';
					else
						content += '(답변완료)';
					content += '</td>';
					content += '<td><a href="/GoodsShop/gshop.do?command=qnaView&qseq=' + contentList[i].qseq + '">' + contentList[i].subject + '</a></td>';
					content += '<td class="text-center">' + contentList[i].userid + '</td>';
					content += '<td class="text-center">' + formatDate(contentList[i++].indate) + '</td>';
					content += '</tr>';
				});
				document.querySelector("#infoTable").innerHTML = content;
				
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

function numCheck(e) {
	var code = e.keyCode || e.which;

    if ((code >= 48 && code <= 57) || (code >= 96 && code <= 105)) {
        return;
    }

    if (code === 8 || code === 9 || code === 13 || code === 46) {
        return;
    }
    
    if (code >= 37 && code <= 40) {
        return;
    }

	e.preventDefault();
}

function numInputCheck(e) {
    const value = e.target.value;
    
    if (!/^\d*$/.test(value)) {
        e.target.value = value.replace(/\D/g, '');
    }
}

quickMove.addEventListener("keydown", (e) => {
	numCheck(e);
	
    if (e.keyCode === 13) {
        if (quickMove.value !== "" && quickMove.value !== null) {
			if (quickMove.value > paging.realEnd) {
				alert("마지막페이지 보다 넘게 이동 할 수 없습니다.");
            } else {
				paging.currentPage = quickMove.value;
            	asynGetContent();
            	quickMove.value = '';
			}
        }
    }
});
quickMove.addEventListener("input", numInputCheck);

document.addEventListener('DOMContentLoaded', () => {
    let buttons = document.querySelectorAll('.btn-group .btn');

    buttons.forEach(button => {
        button.addEventListener('click', () => {
            buttons.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');
        });
    });
});

