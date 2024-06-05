document.addEventListener("DOMContentLoaded", () => {
	let qseqText;
	let qseq;
		
	function writeupdate(command) {
		if (command == "qnaUpdate") {
			qseqText = document.querySelector('#qseq');
			qseq = parseInt(qseqText.innerHTML);
		}
		fetch('/GoodsShop/gshop.do?command=asyn', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json;charset=utf-8'
			},
				body: JSON.stringify({"command": command, "qseq": qseq, "subject": subject.value, "content": content.value})
			})
			.then(response => response.json())
			.then(jsonResult => {
				if (jsonResult.status == true) {
					alert(jsonResult.message);
					location.href = jsonResult.url;
				} else {
					alert(jsonResult.message);
				}
		});
	}

	let writeBtn = document.querySelector('#writeBtn');

	if (writeBtn != null) {
		writeBtn.addEventListener('click', () => {
			writeupdate("qnaWrite");
		});
	}

	let updateBtn = document.querySelector('#updateBtn');
	
	if (updateBtn != null) {
		updateBtn.addEventListener('click', () => {
			writeupdate("qnaUpdate");
		});
	}
});