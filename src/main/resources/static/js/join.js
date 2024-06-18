function sum() {
    let firstmail = $('#nowmail').val();
    let secondemail = $('#domain-txt').val();
    $("#orginmail").val(firstmail + "@" + secondemail);// hidden 값을 입력한 이메일값으로변경
    let year = $("select[name=year] option:selected").val();
    let month = $("select[name=month] option:selected").val();
    let day = $("select[name=day] option:selected").val();
    $("#orginbirth").val(year + "/" + month + "/" + day);

    var number1 = $("#number").val();//인증번호입력
    var number2 = $("#Confirm").val();//전송된 인증번호

    if ($("#number").val() == "") {//인증번호가 빈칸일떄
        alert("모든사항을 기입해주세요")
        return false;
    } else {//빈칸아닐때
        if (number1 == number2) {// 인증번호 입력란이랑 이메일론 발송된 이메일 번호가 동일할때
            return true;
        } else {
            alert("인증번호가 일치하지 않습니다");
            return false;
        }
    }
    return chkid()//
}

function chkid() {// 유효성 아이디 중복체크
    let sendid = $('#newid').val();// 입력한 id 값 저장
    var iderrmsg = document.getElementById('idmsg');// 아이디 중복체크후 가능 여부 확인 메세지 출력용
    console.log(sendid);
    $.ajax({
        url: "/idchk",//컨트롤러에서 요청받을 주소
        type: "post",//post 방식으로 전달
        data: {"originid": sendid},
        dataType: "text",
        //dataType: "json",
        success: function (cnt) {// 컨트롤러에서 넘어온 cnt 값 받는다
            console.log(typeof cnt);
            if (cnt == '0') {// cnt 가 0 이면 DB에 없음 -> 사용 가능 아이디
                //if(cnt == 0)
                iderrmsg.style.color = "green";
                iderrmsg.textContent = "사용 가능한 아이디 입니다";
                return true;
            } else {
                $('#newid').val("");
                $('#newid').focus();
                iderrmsg.style.color = "red";
                iderrmsg.textContent = "존재하는 아이디입니다";
                return false;
            }
        }
    })

}


function sendNumber() {
    let nowmail = $('#nowmail').val();
    let inputmail = $('#domain-txt').val();
    let fullmail = nowmail + "@" + inputmail;

    $.ajax({
        url: "/mail",
        type: "post",
        dataType: "json",
        data: {"mail": fullmail},
        success: function (data) {
            alert("인증번호가 발송되었습니다");
            $("#Confirm").attr("value", data);
        }
    });
}

function confirmNumber() {
    var number1 = $("#number").val();
    var number2 = $("#Confirm").val();
    if (number1 == number2) {
        alert("인증되었습니다.");
        return true;
    } else {
        alert("인증번호가 다릅니다 다시입력해주세요.");
        return false;
    }
}

const domainListEl = document.querySelector('#domain-list');//id 선택자 이용해서 변수에 저장
const domainInputEl = document.querySelector('#domain-txt');
domainListEl.addEventListener('change', (event) => {
    // option에 있는 도메인 선택 시
    if (event.target.value !== "type") {
        domainInputEl.value = event.target.value
        domainInputEl.disabled = true
    } else { //
        domainInputEl.value = ""
        domainInputEl.disabled = false
    }
})


// 출생 연도 셀렉트 박스 option 목록 동적 생성
const birthYearEl = document.querySelector('#birth-year')//id 선택자 이용해서 변수에 저장
const monthE1 = document.querySelector('#birth-month')
const dayE1 = document.querySelector('#birth-day')
let nowyear;//사용자가 선택한 year value 저장할 변수 선언
let nowmonth;//사용자가 선택한 month value 저장할 변수 선언

isYearOptionExisted = false;// 년도 option 목록 생성 여부 확인 boolean
monthoptionexisted = false;// 월 option 목록 생성 여부 확인 boolean
// dayopthionexisted = false;// 이거는 월 선택될떄마다 new Date()로 마지막 날짜 구한후 월 바뀔때마다 초기화시키고 for문으로 옵션 넣을거라 뻄


birthYearEl.addEventListener('focus', function () {
    // 년 목록 생성되지 않았을 때 (최초 클릭 시)
    if (!isYearOptionExisted) {
        //boolean 값 변경해서 for문 안돌아가도록 막기
        isYearOptionExisted = true
        for (var i = 1940; i <= 2024; i++) {
            // option element 생성
            const YearOption = document.createElement('option')
            YearOption.setAttribute('value', i)
            YearOption.innerText = i + '년'
            // birthYearEl의 자식 요소로 추가
            this.appendChild(YearOption);
        }
    }
});

// 선택된 년도가 change 되었을떄 jquery 사용해서 nowyear 변수에 사용자가 선택한 값의 value 가져와서 저장
$("select[name=year]").change(function () {
    nowyear = $("select[name=year] option:selected").val();
});

monthE1.addEventListener('focus', function () {
    if (!monthoptionexisted) {
        monthoptionexisted = true
        for (var i = 1; i <= 12; i++) {
            const monthOption = document.createElement('option')
            monthOption.setAttribute('value', i)
            monthOption.innerText = i + '월'
            this.appendChild(monthOption);
        }
    }
});


$("select[name=month]").change(function () {
    nowmonth = $("select[name=month] option:selected").val();
    const lastDay = new Date(nowyear, nowmonth, 0).getDate();//Date()로 년,월 입력해서 마지막 날짜 구한후 대입
    dayE1.addEventListener('focus', function () {
        $("select[name=day] option").remove();// 월이 바꼇을때 일  클릭하면 그전내용 초기화 한후 다시 반복문 돌려서 1월->31 2월->28 되도록 구현
        for (var i = 1; i <= lastDay; i++) {//2 월일경우 28일로 마지막 날짜 나오니까 반복문돌려서 옵션에 추가
            const dayOption = document.createElement('option')
            dayOption.setAttribute('value', i)
            dayOption.innerText = i + '일'
            this.appendChild(dayOption);
        }

    });
});

// 비밀번호 확인 유효성
var newpw = document.getElementById('newpw');
var conpw = document.getElementById('conpw');
var errmsg = document.getElementById('errmsg');
conpw.addEventListener('change', () => {
    if (newpw.value === conpw.value) {
        errmsg.style.color = "green";
        errmsg.textContent = "비밀번호가 일치합니다";
    } else {
        conpw.value = "";
        conpw.focus();
        errmsg.style.color = "red";
        errmsg.textContent = "비밀번호가 일치하지 않습니다";
    }
})


