document.addEventListener('DOMContentLoaded', function () {
   alarmchk();
});
function alarmchk(){
    let alarmwhite = $("#alarmwhite");//하얀색 알람 아이콘
    let alarmblack = $("#alarmblack");//검은색 알람 아이콘
    $.ajax({// 알람체크 리턴값을 받기위한 ajax
        url:"messagechk",
        type:"post",
        dataType:"json",
        success: function (chk) {
            if (chk > 0) {//알람함에 메시지가 있을경우
                alarmblack.css("display", "block");
                alarmwhite.css("display", "none");
            } else {//없을경우
                alarmblack.css("display", "none");
                alarmwhite.css("display", "block");
            }
        }
    })
}

