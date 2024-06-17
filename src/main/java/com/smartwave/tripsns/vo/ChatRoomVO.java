package com.smartwave.tripsns.vo;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class ChatRoomVO { // 채팅방 정보
    private String chatRoomNum ; // 채팅방 번호
    private String chatName ; // 채팅방 이름
    private int userCnt ; // 채팅방 인원수

    private HashMap<String, String> userList = new HashMap<String, String>() ;

    public static ChatRoomVO create(String chatName) {
        ChatRoomVO cRoom = new ChatRoomVO();

        cRoom.chatRoomNum = UUID.randomUUID().toString();
        // UUID : 네트워크 상에서 고유성이 보장되는 id를 만들기 위한 표준 규약
        cRoom.chatName = chatName;

        return cRoom;
    }

}
