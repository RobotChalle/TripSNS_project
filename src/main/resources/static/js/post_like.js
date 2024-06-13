function like() {
    let p_no = $('#p_no').val();
    let p_id = $('#p_id').val();
    let thumbsup = $('#thumbsUp')
    let thumbsFill = $('#thumbsFill')
    console.log(p_no)
    $.ajax({
        url: "/postLike",
        type: "get",
        data: {"p_id": p_id, "p_no": p_no},
        success: function (chk) {
            if (chk == 0) {
                postLikeCount();
                thumbsup.css("display", "block");
                thumbsFill.css("display", "none");
            } else {
                postLikeCount();
                thumbsup.css("display", "none");
                thumbsFill.css("display", "block");
            }
        }
    })
}

function postLikeSelect() {
    let p_no = $('#p_no').val();
    let p_id = $('#p_id').val();
    let thumbsup = $('#thumbsUp')
    let thumbsFill = $('#thumbsFill')
    $.ajax({
        url: "/postLikeSelect",
        type: "get",
        data: {"p_id": p_id, "p_no": p_no},
        success: function (chk) {
            if (chk == 0) {
                thumbsup.css("display", "block");
                thumbsFill.css("display", "none");
            } else {
                thumbsup.css("display", "none");
                thumbsFill.css("display", "block");
            }
        }
    })
}

function postLikeCount() {
    let p_no = $('#p_no').val();
    let p_id = $('#p_id').val();
    var likeCount = document.getElementById('likeCount');
    console.log(p_no);
    $.ajax({
        url: "/postLikeCount",
        type: "get",
        data: {"p_id": p_id, "p_no": p_no},
        success: function (postLikeCount) {
            likeCount.textContent = postLikeCount;
            console.log(postLikeCount);
        }
    })
}

window.addEventListener("DOMContentLoaded", function () {
    postLikeCount();
    postLikeSelect();
})