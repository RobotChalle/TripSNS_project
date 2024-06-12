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
    xhr.open("get", "../member/getMyId", false);
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
    xhr.open("get", "./getTotalLikeCount?boardId=" + boardId);
    xhr.send();
}

function toggleLike() {
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 & xhr.status == 200) {
            refreshTotalLikeCount();
            refreshMyHeart();
        }
    }
    xhr.open("get", "./toggleLike?board_id" + boardId);
    xhr.send();
}

function refreshMyHeart() {
    if (mySessionId == null) return;
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            const response = JSON.parse(xhr.responseText);
            const heartBox = document.getElementById("heartBox")
            if (response.isLiked) {
                heartBox.classList.remove("bi-heart");
                heartBox.classList.add("bi-heart-fill");
            } else {
                heartBox.classList.remove("bi-heart-fill");
                heartBox.classList.add("bi-heart");
            }
        }
    }
    xhr.open("get", "./isLiked?board_id=" + boardId);
    xhr.send();
}

window.addEventListener("DOMContentLoaded", function () {
    getSessionId();
    refreshTotalLikeCount();
});

