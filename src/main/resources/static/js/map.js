//기본 지도 생성////////////////////////////////////
var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var options = { //지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(37.2772219, 127.0279806), //지도의 중심좌표.
    level: 3 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

//지도 타입 설정////////////////////////////////////
// 지도 타입 정보를 가지고 있을 객체입니다
// map.addOverlayMapTypeId 함수로 추가된 지도 타입은
// 가장 나중에 추가된 지도 타입이 가장 앞에 표시됩니다
// 이 예제에서는 지도 타입을 추가할 때 지적편집도, 지형정보, 교통정보, 자전거도로 정보 순으로 추가하므로
// 자전거 도로 정보가 가장 앞에 표시됩니다
var mapTypes = {
    traffic: kakao.maps.MapTypeId.TRAFFIC,
    bicycle: kakao.maps.MapTypeId.BICYCLE,
};

// 체크 박스를 선택하면 호출되는 함수입니다
function setOverlayMapTypeId() {
    var chkTraffic = document.getElementById('chkTraffic'),
        chkBicycle = document.getElementById('chkBicycle');

    // 지도 타입을 제거합니다
    for (var type in mapTypes) {
        map.removeOverlayMapTypeId(mapTypes[type]);
    }

    // 교통정보 체크박스가 체크되어있으면 지도에 교통정보 지도타입을 추가합니다
    if (chkTraffic.checked) {
        map.addOverlayMapTypeId(mapTypes.traffic);
    }

    // 자전거도로정보 체크박스가 체크되어있으면 지도에 자전거도로정보 지도타입을 추가합니다
    if (chkBicycle.checked) {
        map.addOverlayMapTypeId(mapTypes.bicycle);
    }

}

//임의 마커 설정 + 인포윈도우////////////////////////////////////
//마커를 표시할 위치
var position = new kakao.maps.LatLng(37.2772219, 127.0279806);

//마커 생성
var marker3 = new kakao.maps.Marker({
    position: position
});

//마커를 지도에 표시
marker3.setMap(map);

//마커 인포윈도우 생성
var iwContent = '<div style = "padding:5px;">스마트 웨이브 본사</div>';

//인포윈도우 생성
var infowindow3 = new kakao.maps.InfoWindow({
    content: iwContent
});

//마커에 마우스오버 이벤트 등록
kakao.maps.event.addListener(marker3, 'mouseover', function () {
    infowindow3.open(map, marker3);
});

//마커에 마우스아웃 이벤트 등록
kakao.maps.event.addListener(marker3, 'mouseout', function () {
    infowindow3.close();
});