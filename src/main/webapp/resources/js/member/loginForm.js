function clearPlaceholder(input) {
            if (input.value === input.defaultValue) {
                input.value = '';
                input.classList.remove('placeholder');
                input.type = "text";
                
            }
        }

function clearPlaceholders(input) {
    if (input.value === input.defaultValue) {
        input.value = '';
        input.classList.remove('placeholder-visible');
        input.type = "password";
    }
}

function setPlaceholder(input, placeholder) {
    if (input.value === '') {
        input.value = placeholder;
        input.classList.add('placeholder-visible');
        input.type = "text";
    }
}

// 페이지 로드 시 실행하여 초기 상태를 설정합니다.
window.onload = function() {
    var passwordField = document.getElementById("password");
    // 초기에 입력된 값이 "비밀번호"인 경우에만 클래스를 추가하여 플레이스홀더 텍스트를 보이게 합니다.
    if (passwordField.value === "비밀번호") {
        passwordField.classList.add('placeholder-visible');
    }
};     