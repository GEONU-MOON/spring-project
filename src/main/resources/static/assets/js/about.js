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

document.getElementById("update-btn").addEventListener("click", function () {
    checkPassword("update");
});

document.getElementById("delete-btn").addEventListener("click", function () {
    checkPassword("delete");
});
