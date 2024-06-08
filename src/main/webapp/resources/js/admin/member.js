let param = {
    table: "member_view",
};

let paging;
let selectAmount = document.querySelector("#selectAmount");
let pages = document.querySelectorAll('.page-link');
let search = document.querySelector('#search');
let keyword = '';
let checkAll = document.querySelector("#checkAll");
let checkBoxes = document.querySelectorAll("div.small-col input[type='checkbox'][name='YN']");
let switchBtn = document.querySelector("#switchBtn");
let discardBtn = document.querySelector("#discardBtn");

document.querySelector("#keyword").addEventListener("keydown", (e) => {
	keyword = document.querySelector("#keyword").value;
	
	if (e.keyCode === 13) {
		asynGetContent();
	}
});

document.querySelector("#keyword").addEventListener("input", () => {
	keyword = document.querySelector("#keyword").value;
});

switchBtn.addEventListener("click", () => {
	if (confirm("선택한 회원(들)의 상태를 변경할까요?")) {
		fetch('/GoodsShop/gshop.do?command=asyn', {
			method : 'POST',
			headers: {
				'Content-Type': 'application/json;charset=utf-8'
			},
				body: JSON.stringify({
					"command" : "switchYN",
					"checkList" : checkBoxChecked(),
				})
			})
			.then(response => response.json())
			.then(jsonResult => {
				if (jsonResult.status == true) {
					asynGetContent();
					alert(jsonResult.message);
					checkAll.checked = false;
				} else {
					alert(jsonResult.message);
					checkAll.checked = false;
				}
		});
	} else {
		return;
	}
});

discardBtn.addEventListener("click", () => {
	if (confirm("선택한 회원(들)을 탈퇴처리합니다. 이 동작은 되돌릴 수 없습니다. 진행할까요?")) {
		fetch('/GoodsShop/gshop.do?command=asyn', {
			method : 'POST',
			headers: {
				'Content-Type': 'application/json;charset=utf-8'
			},
				body: JSON.stringify({
					"command" : "discardMember",
					"checkList" : checkBoxChecked(),
				})
			})
			.then(response => response.json())
			.then(jsonResult => {
				if (jsonResult.status == true) {
					asynGetContent();
					alert(jsonResult.message);
					checkAll.checked = false;
				} else {
					alert(jsonResult.message);
					checkAll.checked = false;
				}
		});
	} else {
		return;
	}
});

checkAll.addEventListener("change", function() {
	let isChecked = checkAll.checked;
	checkBoxes = document.querySelectorAll("div.small-col input[type='checkbox'][name='YN']");
	
	if(isChecked){
		for(let checkBox of checkBoxes) {
			checkBox.checked = true;
		}
	} else {
		for(let checkBox of checkBoxes) {
			checkBox.checked = false;
		}
	}
});

function checkBoxChecked() {
	let checkList = [];
	checkBoxes = document.querySelectorAll("div.small-col input[type='checkbox'][name='YN']");
	
	for (let i = 0; i < checkBoxes.length; i++) {
		if (checkBoxes[i].checked == true) {
			checkList.push(checkBoxes[i].value);
		}
	}

	return checkList;
}

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

document.addEventListener('DOMContentLoaded', () => {   
    getPageInfo();
});

function getPageInfo() {	
	fetch('/GoodsShop/gshop.do?command=asyn', {
		method : 'POST',
		headers: {
			'Content-Type': 'application/json;charset=utf-8'
		},
			body: JSON.stringify({"command" : "pageInfo", "table" : "member_view"})
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

document.getElementById('search').addEventListener('change', () => {
    let selectedValue = search.options[search.selectedIndex].value;

	Object.keys(param).forEach(key => {
        if (key !== 'table') {
            delete param[key];
        }
    });
    
    if (selectedValue) {
        param[selectedValue] = '';
    }
});

function asynGetContent() {
	let selectedValue = document.getElementById('search').value;
	
	param.command = "getContent";
	param.amount = paging.amount;
	param.page = paging.currentPage;
	
	if (keyword.trim() !== '') {
        param[selectedValue] = keyword;
    }

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
					content += '<li class="member-item">';
					content += '<div class="d-flex justify-content-center align-items-center">';
					content += '<div>' + contentList[i].userid + '</div>';
					content += '<div>' + contentList[i].grade + '</div>';
					content += '<div>' + contentList[i].name + '</div>';
					content += '<div>' + contentList[i].phone + '</div>';
					content += '<div>' + contentList[i].email + '</div>';
					content += '<div class="small-col">' + contentList[i].zip_code + '</div>';
					content += '<div>' + contentList[i].address + '</div>';
					content += '<div>' + contentList[i].d_address + '</div>';
					content += '<div>' + formatDate(contentList[i].indate) + '</div>';
					content += '<div class="small-col">';
					if (contentList[i].is_login == 1) {
						content += 'Y';
					} else {
						content += 'N';
					}
					content += '</div>';
					content += '<div class="small-col"><input class="form-check-input" type="checkbox" name="YN" value="' + contentList[i++].userid + '"><div>';
					content += '</div>';
					content += '</li>';
				});
				document.querySelector("#member-list").innerHTML = content;
				
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
				//document.querySelector("#pageInfo").innerHTML = paging.currentPage + ' / ' + paging.realEnd;
				
				addPagingEvent();
			} else {
				alert(jsonResult.message);
			}
	});
	checkAll.checked = false;
}