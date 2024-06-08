let param = {
    table: "notice",
};

let deleteBtn = document.querySelector("#deleteBtn");
deleteBtn.addEventListener("click", () => {
	if (confirm("삭제하시겠습니까?")) {
		param.command = "checkDelete";
		param.checkList = checkBoxChecked();
		
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
					delete param.command;
					delete param.checkList;
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

function contentList(noticeList) {
	let content = '';
	let i = 0;
	
	noticeList.forEach(() => {
		content += '<li class="li-item">';
		content += '<div class="d-flex justify-content-center align-items-center">';
		content += '<div class="small-col">' + noticeList[i].nseq + '</div>';
		content += '<div class="small-col">' + noticeList[i].adminId + '</div>';
		content += '<div><a href="/GoodsShop/gshop.do?command=adminNoticeView&nseq=' + noticeList[i].nseq + '">' + noticeList[i].subject + '</a></div>';
		content += '<div>' + noticeList[i].content + '</div>';
		content += '<div class="small-col">' + formatDate(noticeList[i].indate) + '</div>';
		content += '<div class="small-col"><input class="form-check-input" type="checkbox" name="check" value="' + noticeList[i++].nseq + '"></div>';
		content += '</div>';
		content += '</li>';
	});
	document.querySelector("#notice-list").innerHTML = content;	
}

function insertNotice(){
	location.href="gshop.do?command=insertNoticeForm";
}