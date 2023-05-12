function validateLoginForm() {
    const loginId = document.getElementById("loginId");
    const loginPassword = document.getElementById("loginPassword");

    if (loginId.value === "") {
        alert("아이디를 입력하세요.");
        loginId.focus();
        return false;
    }

    if (loginPassword.value === "") {
        alert("비밀번호를 입력하세요.");
        loginPassword.focus();
        return false;
    }

    return true;
}