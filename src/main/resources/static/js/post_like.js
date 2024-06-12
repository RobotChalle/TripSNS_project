function like(blog_id) {
    $.ajax({
        type: "post",
        url: "/blog/like",
        data: {
            'blod_id': blog_id,
            'csrfmiddlewaretoken': '{{csrf_token}}',
        },
        dataType: "json",
        success: function (response) {
            console.log(response.message);
            $('#like').replaceWith('<p id="like">' + response.message + '</p>');
            $("#like_counting").replaceWith('<span id="like_counting">' + response.num + '</span>');
        },
        error: function (request, status, error) {
            alert(error);
        }
    })
}