
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
    //프로필 미리보기
    function readURL(input) {// 파일입력 태그가 onchange - 변화가 있을 시 동작하는 함수를 구현
    if (input.files && input.files[0]) {//파일이 선택되엇을시
    var reader = new FileReader();//미리보기 생성을 위해 filreader객체생성 >파일을 읽고 사용자의 컴퓨터에 저장하는 것을 가능하게 해주는 Web API
    reader.onload = function(e) {//onload 함수를 통해 파일 데이터를 읽었을때 실행될 함수 정의
    document.getElementById('preview').src = e.target.result;//읽은 파일데이터를 src속성에 할당, e.target.result는 파일의 데이터 URL
};
    reader.readAsDataURL(input.files[0]);//데이터 URL은 파일의 내용을 base64로 인코딩한 문자열
} else {
    document.getElementById('preview').src = "";//파일이 선택되지 않은 경우 이미지 초기화
}
}

