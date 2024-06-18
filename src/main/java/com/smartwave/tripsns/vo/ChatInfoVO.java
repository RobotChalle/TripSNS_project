package com.smartwave.tripsns.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatInfoVO { // 채팅 정보

    public enum MessageType {
        // 메시지 타입 : 입장, 채팅
        // 메시지 타입에 따라서 동작하는 구조 달라짐
        ENTER, LEAVE, TALK; // ENTER, LEAVE: 입장/퇴장 이벤트 처리 실행
                            // TALK : 어떤 채팅방을 sub하고 있는 모든 클라이언트에게 내용 전달
    }

    private MessageType type; // 메시지 타입
    private String chatRoomNum ; // 채팅방 번호
    private String sendName ; // 유저 이름
    private String sendID ; // 유저의 ID
    private String message ; // 채팅내용
    private String sendTime ; // 전송시간

}
