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

//게시글 관리
function checkAll(selectAll) {
    const checkboxes = document.getElementsByName('checkAll');
    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
    })
}