document.addEventListener('DOMContentLoaded', function () {
    let p_no = document.getElementById('p_no').value;
    let viewSpan = document.getElementById('postView');
    $.ajax({
        url: "/#",
        type: "post",
        data: {"p_id": p_no},
        success: function () {
            console.log()
        }
    })
})