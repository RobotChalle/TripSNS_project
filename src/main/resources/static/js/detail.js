document.addEventListener('DOMContentLoaded', function () {
    let p_no = document.getElementById('p_no').value;
    $.ajax({
        url: "/postViewUpdate",
        type: "post",
        data: {"p_no": p_no},
        success: function () {
            postViewCount();
            console.log(p_no);
        }
    })
})

function postViewCount() {
    let p_no = document.getElementById('p_no').value;
    let viewSpan = document.getElementById('postView');
    $.ajax({
        url: "/postViewCount",
        type: "get",
        data: {"p_no": p_no},
        success: function (viewCount) {
            viewSpan.textContent = viewCount;
        }
    })
}