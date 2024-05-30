document.addEventListener("DOMContentLoaded", () => {
    const writeBtn = document.getElementById("writeBtn");
    const container = document.getElementById("replyContainer");
    let textareaCreated = false;

	if (writeBtn != null) {
	    writeBtn.addEventListener("click", () => {
	        if (!textareaCreated) {
	            const textarea = document.createElement("textarea");
	            textarea.name = "reply";
	            textarea.id = "reply";
	            container.appendChild(textarea);
	
	            writeBtn.value = "전송";
	            textareaCreated = true;
	        } else {
				if (document.getElementById("reply").value !== '')
					document.getElementById("replyForm").submit();
				else {
					alert("답변 적어");
				}
	        }
	    });
    }
});

document.querySelector("#deleteBtn").addEventListener("click", () => {
	let replyForm = document.getElementById("replyForm");
	replyForm.action = '/GoodsShop/gshop.do?command=qnaReplyWrite&qseq=';
	document.getElementById("replyForm").submit();
});