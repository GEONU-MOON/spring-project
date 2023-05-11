function validateRegistrationForm() {
    let registerId = document.getElementById("registerId");
    let registerName = document.getElementById("registerName");
    let registerEmail = document.getElementById("registerEmail");
    let registerPassword = document.getElementById("registerPassword");
    let passwordConfirm = document.getElementById("passwordConfirm");

    if (registerId.value.trim() === "") {
        alert("아이디를 입력해주세요.");
        registerId.focus();
        return false;
    }

    if (registerName.value.trim() === "") {
        alert("이름을 입력해주세요.");
        registerName.focus();
        return false;
    }

    if (registerEmail.value.trim() === "") {
        alert("이메일을 입력해주세요.");
        registerEmail.focus();
        return false;
    }

    if (registerPassword.value.trim() === "") {
        alert("비밀번호를 입력해주세요.");
        registerPassword.focus();
        return false;
    }

    if (passwordConfirm.value.trim() === "") {
        alert("비밀번호 확인을 입력해주세요.");
        passwordConfirm.focus();
        return false;
    }

    if (registerPassword.value !== passwordConfirm.value) {
        alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
        registerPassword.focus();
        return false;
    }

    return true;
}