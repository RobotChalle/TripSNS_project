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

//게시글 관리 체크박스 전체 선택
document.querySelector('#checkAll');
checkAll.addEventListener('click', function () {
    const isChecked = checkAll.checked;
    if (isChecked) {
        const checkboxes = document.querySelectorAll('.chk');

        for (const checkbox of checkboxes) {
            checkbox.checked = true;
        }
    } else {
        const checkboxes = document.querySelectorAll('.chk');
        for (const checkbox of checkboxes) {
            checkbox.checked = false;
        }
    }
})
//게시글 관리 테이블 정렬
// 출처 : http://tonks.tistory.com/79

var myTable = document.getElementById("postList");
var replace = replacement(myTable);

function sortTD(index) {
    replace.ascending(index);
}

function reverseTD(index) {
    replace.descending(index);
}

/* sortingNumber() : 숫자인 실수만으로 되어있을 때, 적용될 함수 */

function sortingNumber(a, b) {

    if (typeof a == "number" && typeof b == "number") return a - b;

    // 천단위 쉼표와 공백문자만 삭제하기.
    var a = (a + "").replace(/[,\s\xA0]+/g, "");
    var b = (b + "").replace(/[,\s\xA0]+/g, "");

    var numA = parseFloat(a) + "";
    var numB = parseFloat(b) + "";

    if (numA == "NaN" || numB == "NaN" || a != numA || b != numB) return false;

    return parseFloat(a) - parseFloat(b);
}


/* changeForSorting() : 문자열 바꾸기. */

function changeForSorting(first, second) {

    // 문자열의 복사본 만들기.
    var a = first.toString().replace(/[\s\xA0]+/g, " ");
    var b = second.toString().replace(/[\s\xA0]+/g, " ");

    var change = {first: a, second: b};

    if (a.search(/\d/) < 0 || b.search(/\d/) < 0 || a.length == 0 || b.length == 0) return change;

    var regExp = /(\d),(\d)/g; // 천단위 쉼표를 찾기 위한 정규식.

    a = a.replace(regExp, "$1" + "$2");
    b = b.replace(regExp, "$1" + "$2");

    var unit = 0;
    var aNb = a + " " + b;
    var numbers = aNb.match(/\d+/g); // 문자열에 들어있는 숫자 찾기

    for (var x = 0; x < numbers.length; x++) {

        var length = numbers[x].length;
        if (unit < length) unit = length;
    }

    var addZero = function (string) { // 숫자들의 단위 맞추기

        var match = string.match(/^0+/);

        if (string.length == unit) return (match == null) ? string : match + string;

        var zero = "0";

        for (var x = string.length; x < unit; x++) string = zero + string;

        return (match == null) ? string : match + string;
    };

    change.first = a.replace(/\d+/g, addZero);
    change.second = b.replace(/\d+/g, addZero);

    return change;
}


/* byLocale() */

function byLocale() {

    var compare = function (a, b) {

        var sorting = sortingNumber(a, b);

        if (typeof sorting == "number") return sorting;

        var change = changeForSorting(a, b);

        var a = change.first;
        var b = change.second;

        return a.localeCompare(b);
    };

    var ascendingOrder = function (a, b) {
        return compare(a, b);
    };
    var descendingOrder = function (a, b) {
        return compare(b, a);
    };

    return {ascending: ascendingOrder, descending: descendingOrder};
}


/* replacement() */

function replacement(parent) {
    var tagName = parent.tagName.toLowerCase();
    if (tagName == "table") parent = parent.tBodies[0];
    tagName = parent.tagName.toLowerCase();
    if (tagName == "tbody") var children = parent.rows;
    else var children = parent.getElementsByTagName("li");

    var replace = {
        order: byLocale(),
        index: false,
        array: function () {
            var array = [];
            for (var x = 0; x < children.length; x++) array[x] = children[x];
            return array;
        }(),
        checkIndex: function (index) {
            if (index) this.index = parseInt(index, 10);
            var tagName = parent.tagName.toLowerCase();
            if (tagName == "tbody" && !index) this.index = 0;
        },
        getText: function (child) {
            if (this.index) child = child.cells[this.index];
            return getTextByClone(child);
        },
        setChildren: function () {
            var array = this.array;
            while (parent.hasChildNodes()) parent.removeChild(parent.firstChild);
            for (var x = 0; x < array.length; x++) parent.appendChild(array[x]);
        },
        ascending: function (index) { // 오름차순
            this.checkIndex(index);
            var _self = this;
            var order = this.order;
            var ascending = function (a, b) {
                var a = _self.getText(a);
                var b = _self.getText(b);
                return order.ascending(a, b);
            };
            this.array.sort(ascending);
            this.setChildren();
        },
        descending: function (index) { // 내림차순
            this.checkIndex(index);
            var _self = this;
            var order = this.order;
            var descending = function (a, b) {
                var a = _self.getText(a);
                var b = _self.getText(b);
                return order.descending(a, b);
            };
            this.array.sort(descending);
            this.setChildren();
        }
    };
    return replace;
}

function getTextByClone(tag) {
    var clone = tag.cloneNode(true); // 태그의 복사본 만들기.
    var br = clone.getElementsByTagName("br");
    while (br[0]) {
        var blank = document.createTextNode(" ");
        clone.insertBefore(blank, br[0]);
        clone.removeChild(br[0]);
    }
    var isBlock = function (tag) {
        var display = "";
        if (window.getComputedStyle) display = window.getComputedStyle(tag, "")["display"];
        else display = tag.currentStyle["display"];
        return (display == "block") ? true : false;
    };
    var children = clone.getElementsByTagName("*");
    for (var x = 0; x < children.length; x++) {
        var child = children[x];
        if (!("value" in child) && isBlock(child)) child.innerHTML = child.innerHTML + " ";
    }
    var textContent = ("textContent" in clone) ? clone.textContent : clone.innerText;
    return textContent;
}

//게시글 관리 엑셀 저장
const excelDownload = document.querySelector('#tab01 #excelDownload');
document.addEventListener('DOMContentLoaded', () => {
    excelDownload.addEventListener('click', exportExcel);
})

function exportExcel() {
    var wb = XLSX.utils.book_new();
    var newWorksheet = excelHandler.getWorksheet();
    XLSX.utils.book_append_sheet(wb, newWorksheet, excelHandler.getSheetName());
    var wbout = XLSX.write(wb, {bookType: 'xlsx', type: 'binary'});
    saveAs(new Blob([s2ab(wbout)], {type: "application/octet-stream"}), excelHandler.getExcelFileName());
}

var excelHandler = {
    getExcelFileName: function () {
        return '게시물.xlsx';
    },
    getSheetName: function () {
        return '게시물_목록';
    },
    getExcelData: function () {
        return document.getElementById('postList');
    },
    getWorksheet: function () {
        return XLSX.utils.table_to_sheet(this.getExcelData());
    }
}

function s2ab(s) {
    var buf = new ArrayBuffer(s.length);
    var view = new Uint8Array(buf);
    for (var i = 0; i < s.length; i++) view[i] = s.charCodeAt(i) & 0xFF;
    return buf;

}

//게시물관리 검색
document.addEventListener('DOMContentLoaded', function () {
    // let searchById = $(".searchId");
    // let searchByPlace = $(".searchPlace");
    let search = document.querySelector('#postSearch');
    let table = document.querySelector('#postList');

    search.addEventListener('keyup', function () {
        const searchWord = search.value.toLowerCase();
        const rows = table.querySelectorAll('tr');

        for (var i = 0; i < rows.length; i++) {
            var rowText = rows[i].textContent.toLowerCase();
            if (rowText.includes(searchWord)) {
                rows[i].style.display = '';
            } else {
                rows[i].style.display = 'none';
            }
        }
    })
    // if (searchById.is(":checked")) {
    //
    // } else if (searchByPlace.is(":checked")) {
    //
    // }
})

//게시물 관리 체크 삭제
function postDeleteChk() {
    const checkboxes = document.querySelectorAll('.chk:checked');
    for (const checkbox of checkboxes) {
        location.href = "/postDeleteManager?p_no=" + checkbox.value;
    }
}

////////////////////////////////////////////////////////////
const checkboxes = document.querySelectorAll('.chk');
for (const checkbox of checkboxes) {
    checkbox.addEventListener('click', function () {
        const totalCnt = checkboxes.length;
        const checkedCnt = document.querySelectorAll('.chk:checked').length;
        if (totalCnt == checkedCnt) {
            document.querySelector('#checkAll').checked = true;
        } else {
            document.querySelector('#checkAll').checked = false;
        }

    });

}