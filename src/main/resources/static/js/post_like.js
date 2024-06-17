function like(post_no, obj) {
    let p_id = $('#p_id').val();
    let p_no = post_no;
    let thumbsUp = $(obj).children("#thumbsUp");
    let thumbsFill = $(obj).children("#thumbsFill");
    let countSpan = obj.nextSibling;
    $.ajax({
        url: "/postLike",
        type: "get",
        data: {"p_id": p_id, "p_no": p_no},
        success: function (chk) {
            if (chk == 0) {
                postLikeCount(p_no, countSpan);
                thumbsUp.css("display", "block");
                thumbsFill.css("display", "none");
            } else {
                postLikeCount(p_no, countSpan);
                thumbsUp.css("display", "none");
                thumbsFill.css("display", "block");
                thumbsFill.css("color", "rgba(100, 149, 237)");
            }
        }
    })
}

// function postLikeSelect(post_no, obj) {
//     let p_no = post_no;
//     let p_id = $('#p_id').val();
//     let thumbsUp = $(obj).children("#thumbsUp");
//     let thumbsFill = $(obj).children("#thumbsFill");
//     $.ajax({
//         url: "/postLikeSelect",
//         type: "get",
//         data: {"p_id": p_id, "p_no": p_no},
//         dataType: "json",
//         success: function (chk) {
//             if (chk == 0) {
//                 thumbsUp.css("display", "block");
//                 thumbsFill.css("display", "none");
//             } else {
//                 thumbsUp.css("display", "none");
//                 thumbsFill.css("display", "block");
//                 thumbsFill.css("color", "rgba(100, 149, 237)");
//             }
//         }
//     })
// }
//
// function postLikeCount(post_no, countSpan) {
//     let p_no = post_no;
//     let p_id = $('#p_id').val();
//     var cntSpan = countSpan;
//     $.ajax({
//         url: "/postLikeCount",
//         type: "get",
//         data: {"p_id": p_id, "p_no": p_no},
//         success: function (postLikeCount) {
//             cntSpan.textContent = postLikeCount;
//         }
//     })
// }

// window.addEventListener("DOMContentLoaded", function () {
//     postLikeCount();
//     postLikeSelect();
// })

// function startFunction(post_no, obj) {
//     let countSpan = obj.nextSibling;
//     postLikeCount(post_no, countSpan);
//     postLikeSelect(post_no, obj);
//     console.log('되나나나나나')
// }

//전체보기 좋아요 기능은 보류... 아래는 자세히 보기 좋아요 함수...

function startFunction() {
    postLikeCount();
    postLikeSelect();
}

function postLikeSelect() {
    let p_no = $('#p_no').val();
    let p_id = $('#p_id').val();
    let thumbsUp = $('#thumbsUp');
    let thumbsFill = $('#thumbsFill');
    $.ajax({
        url: "/postLikeSelect",
        type: "get",
        data: {"p_id": p_id, "p_no": p_no},
        dataType: "json",
        success: function (chk) {
            if (chk == 0) {
                thumbsUp.css("display", "block");
                thumbsFill.css("display", "none");
            } else {
                thumbsUp.css("display", "none");
                thumbsFill.css("display", "block");
                thumbsFill.css("color", "rgba(100, 149, 237)");
            }
        }
    })
}

function postLikeCount() {
    let p_no = $('#p_no').val();
    let p_id = $('#p_id').val();
    var cntSpan = document.getElementById('likeCount');
    $.ajax({
        url: "/postLikeCount",
        type: "get",
        data: {"p_id": p_id, "p_no": p_no},
        success: function (postLikeCount) {
            cntSpan.textContent = postLikeCount;
        }
    })
}