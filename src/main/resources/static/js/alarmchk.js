document.addEventListener('DOMContentLoaded', function () {
   alarmchk();
});


function alarmchk(){
    let alarmwhite = $("#alarmwhite");
    let alarmblack = $("#alarmblack");
    $.ajax({
        url:"messagechk",
        type:"post",
        dataType:"json",
        success: function (chk) {
            if (chk > 0) {
                alarmblack.css("display", "block");
                alarmwhite.css("display", "none");
            } else {
                alarmblack.css("display", "none");
                alarmwhite.css("display", "block");
            }
        }
    })
}