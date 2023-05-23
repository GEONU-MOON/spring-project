function checkEngNumber(str) {
    const pattern = /^[A-Za-z0-9]+$/;
    return pattern.test(str);
}

function checkKorEng(str) {
    const pattern = /^[가-힣A-Za-z]+$/;
    return pattern.test(str);
}

function checkPassword(str) {
    const pattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{4,12}$/;
    return pattern.test(str);
}

function validateForm() {
    const uid = document.getElementById("registerId");
    const uname = document.getElementById("registerName");
    const mail = document.getElementById("registerEmail");
    const pw = document.getElementById("registerPassword");
    const cpw = document.getElementById("passwordConfirm");

    // 아이디 확인
    if (uid.value.length < 4) {
        alert("아이디를 4글자 이상 입력하세요.");
        uid.focus();
        return false;
    } else if (!checkEngNumber(uid.value)) {
        alert("영문 대소문자, 숫자만 입력하세요.");
        uid.focus();
        return false;
    }

    // 이름 확인
    if (uname.value === "") {
        alert("이름을 입력하세요.");
        uname.focus();
        return false;
    } else if (!checkKorEng(uname.value)) {
        alert("한글, 영어만 입력하세요.");
        uname.focus();
        return false;
    }

    // 이메일 확인
    const emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
    if (!mail.value.match(emailPattern)) {
        alert("올바른 이메일 주소를 입력하세요.");
        mail.focus();
        return false;
    }

    // 비밀번호 확인
    if (!checkPassword(pw.value)) {
        alert("비밀번호는 영어, 숫자, 특수문자를 섞어 4~12자리로 만드세요.");
        pw.focus();
        return false;
    }

    // 비밀번호 확인 일치 여부
    if (cpw.value !== pw.value) {
        alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        cpw.focus();
        return false;
    }

}