function checkPassword(action) {
    const password = prompt("기존 비밀번호를 입력하세요.");

    if (password) {
        $.ajax({
            url: "/check-password",
            method: "POST",
            data: {
                password: password,
            },
            success: function (response) {
                if (response === "true") {
                    document.getElementById(action + "-form").submit();
                } else {
                    alert("비밀번호가 일치하지 않습니다.");
                }
            },
        });
    }
}

function validateInput() {
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const newPassword = document.getElementById("password").value;
    const newPasswordCk = document.getElementById("password2").value;

    const namePattern = /^[가-힣a-zA-Z]+$/;
    const newPasswordPattern = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{4,12}$/;

    if (!namePattern.test(name)) {
        alert("이름은 한글, 영어만 입력할 수 있습니다.");
        return false;
    }

    if (!validateEmail(email)) {
        alert("유효한 이메일 주소를 입력해주세요.");
        return false;
    }

    if (newPassword || newPasswordCk) {
        if (!newPasswordPattern.test(newPassword)) {
            alert("새 비밀번호는 영어, 숫자, 특수문자를 섞어 4~12자리로 만들어야 합니다.");
            return false;
        }

        if (newPassword !== newPasswordCk) {
            alert("새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다.");
            return false;
        }
    }



    return true;
}

function validateEmail(email) {
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(email);
}

document.getElementById("update-btn").addEventListener("click", function (event) {
    event.preventDefault();
    if (validateInput()) {
        checkPassword("update");
    }
});

document.getElementById("delete-btn").addEventListener("click", function (event) {
    event.preventDefault();
    checkPassword("delete");
});

