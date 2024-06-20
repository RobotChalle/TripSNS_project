
    $('document').ready(function (){
    let myid=$('#myid').val();//로그인 아이디
    let seid=$('#seid').val();// 세션에 저장된 로그인 아이디
    let other = $('#otherid').val();// 상대방 계정 아이디
    let postcnt = document.getElementById("postcnts")
    let shortcnt = document.getElementById("shortcnts")
    if(myid = seid) {
    $.ajax({//나의 게시물수 ajax 비동기방식 화면 로딩되었을시 실시간으로 숫자 변경 가능하도록
    url: "/postcnt",
    type: "post",
    data: {"myid": myid},
    dataType: "json",
    success: function (pcnt) {//컨트롤러 리턴값
    postcnt.innerText = pcnt;
}
});
    $.ajax({//나의 쇼츠 수 ajax 비동기방식 화면 로딩되었을시 실시간으로 숫자 변경 가능하도록
    url: "/shortcnt",
    type: "post",
    data: {"myid": myid},
    dataType: "json",
    success: function (scnt) {//컨트롤러 리턴값
    console.log(scnt);
    shortcnt.innerText = scnt;
}
});
}else {
    $.ajax({//상대방 게시물수 ajax 비동기방식 화면 로딩되었을시 실시간으로 숫자 변경 가능하도록
    url: "/otherpostcnt",
    type: "post",
    data: {"otherid": other},
    dataType: "json",
    success: function (pcnt) {//컨트롤러 리턴값

    postcnt.innerText = pcnt;
}
});
    $.ajax({//상대방 쇼츠 수 ajax 비동기방식 화면 로딩되었을시 실시간으로 숫자 변경 가능하도록
    url: "/othershortcnt",
    type: "post",
    data: {"otherid": other},
    dataType: "json",
    success: function (scnt) {//컨트롤러 리턴값
    console.log(scnt);
    shortcnt.innerText = scnt;
}
});
}
});

    //탭 기능
    $(function () {
        $('.tabcontent > div').hide();
        $('.tabnav a').click(function () {
            $('.tabcontent > div').hide().filter(this.hash).fadeIn();
            $('.tabnav a').removeClass('active');
            $(this).addClass('active');
            return false;
        }).filter(':eq(0)').click();
    });
