package com.smartwave.tripsns.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatInfoVO {

    public enum MessageType {
        // 메시지 타입 : 입장, 채팅
    }
}
