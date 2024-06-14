package com.smartwave.tripsns.vo;

import lombok.Data;

@Data
public class ChatJoinVO { // 채팅방에 참여한 회원 관리

    // 2024.06.13 기준 아직 안 씀... 추후 수정해보자!

    private String SendID ; // 유저의 아이디
    private String chatRoomNum ; // 채팅방 번호
}
