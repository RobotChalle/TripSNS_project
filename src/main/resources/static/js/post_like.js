function getSessionId() {
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            const response = JSON.parse(xhr.responseText);
            if (response.result == "success") {
                mySessionId = response.id;
            }
        }
    }
    xhr.open("get", "../member/getMyId");
    xhr.send();
}

function refreshTotalLikeCount() {
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            const response = JSON.parse(xhr.responseText);
            const totalLikeCountBox = document.getElementById("totalLikeCount");
            totalLikeCountBox.innerText = response.count;
        }
    }
    xhr.open("get", "");
    xhr.send();

    window.addEventListener("DOMContentLoaded", function () {
        getSessionId();
    });
}

