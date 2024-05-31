document.addEventListener("DOMContentLoaded", () => {
    let writeBtn = document.getElementById("writeBtn");
    let updateBtn = document.querySelector("#updateBtn");
    let deleteBtn = document.querySelector("#deleteBtn");
    let container = document.getElementById("replyContainer");
    let textareaCreated = false;
    let reply = document.querySelector("#reply");
	let replydate = document.querySelector("#replydate");

	if (writeBtn != null) {
	    writeBtn.addEventListener("click", () => {
	        if (!textareaCreated) {
	            let textarea = document.createElement("textarea");
	            textarea.name = "reply";
	            textarea.id = "reply";
	            container.appendChild(textarea);
	
	            writeBtn.value = "전송";
	            textareaCreated = true;
	        } else {
				if (document.getElementById("reply").value !== '')
					document.getElementById("writeForm").submit();
				else {
					alert("답변 적어");
				}
	        }
	    });
    }
    
    let textareaUpdated = false;
    updateBtn.addEventListener("click", () => {
		if (!textareaUpdated) {
	        reply.style.display = 'none';
	        replydate.style.display = 'none';
	
	        let textarea = document.createElement("textarea");
	        textarea.name = "replytext";
	        textarea.id = "replytext";
	        textarea.classList.add("form-control");
	        textarea.rows = 5;
	        
	        replyContent = reply.innerHTML.replace("답변 : ", "");
	        textarea.textContent = replyContent;
	        container.appendChild(textarea);
	        updateBtn.value = "전송";
	        textareaUpdated = true;
        } else {
			let textareaValue = document.getElementById("replytext").value;
	        if (textareaValue === '' || textareaValue === replyContent)
				alert("답변 수정해");
			else {
				if (confirm("수정 하시겠습니까?")) {
					document.getElementById("updateForm").submit();
				}
			}
        }
    });
        
    deleteBtn.addEventListener("click", () => {
        if (confirm("정말 삭제하시겠습니까?")) {
            let deleteForm = document.getElementById("deleteForm");
            deleteForm.submit();
        }
    });
});