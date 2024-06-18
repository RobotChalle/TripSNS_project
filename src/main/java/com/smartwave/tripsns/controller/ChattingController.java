package com.smartwave.tripsns.controller;

import com.smartwave.tripsns.service.IF_ChatService;
import com.smartwave.tripsns.vo.ChatInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.ArrayList;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChattingController { // 채팅 주고받기 위한 Controller

    private SimpMessageSendingOperations simpMO; // 사용자에게 메시지 보내는 방법을 제공
    // 지정된 사용자에게 메시지를 보내기 위함...

    @Autowired
    IF_ChatService cSrv;

    @MessageMapping("/chatting/chatUser")
    // MessageMapping : WebSocket으로 들어오는 메시지를 발신처리함
    // 이때, 클라이언트에서는 /pub/chatting/message 로 요청 -> Controller 가 받아서 처리
    // 처리가 완료되면 /sub/chatting/chatRoomNum 으로 메시지 전송.
    public void chatUser(@Payload ChatInfoVO cvo, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        // @Payload : 제약사항과 관련된 메타 정보를 정의하는데 사용. http 요청을 보낼 때 전달되는 데이터, 즉 전송하고자 하는 실제 데이터
        // SimpMessageHeaderAccessor : 메시지 타입이 지정된 접근자 메서드를 통해서 Header 정보에 접근함

        // 채팅방에 참여한 유저가 추가될 경우
        cSrv.addUser(cvo.getChatRoomNum());

        // 채팅방 참여 유저 리스트에 유저 추가 및 ID 반환 (추후 수정)
        String userUUID = cSrv.addUserList(cvo.getChatRoomNum(), cvo.getSendName());

        // 반환한 결과를 Socket Session 에 userUUID 로 저장
        headerAccessor.getSessionAttributes().put("userUUID", userUUID);
        headerAccessor.getSessionAttributes().put("chatRoomNum", cvo.getChatRoomNum());

        cvo.setMessage(cvo.getSendName() + "님이 입장하셨습니다."); // 이름 + 입장 안내 메시지
//        cvo.setMessage("기분좋은 여행을 위해, 더 나은 커뮤니티를 위해 커뮤니티 이용규칙을 준수해주세요."); // 커뮤니티 준수 안내 메시지
        simpMO.convertAndSend("/sub/chatting/room/" + cvo.getChatRoomNum(), cvo);
        // 채팅방 연결 주소에 유저가 입장할 채팅방 번호 연결 후, ChatInfoVO에 저장
    }

    @MessageMapping("/chatting/sendMessage")
    public void sendMessage(@Payload ChatInfoVO cvo) throws Exception { // 입장한 유저의 메시지 전송 관리.......(수정)
        log.info("cvo {}", cvo);
        cvo.setMessage(cvo.getMessage());
        simpMO.convertAndSend("/sub/chatting/room" + cvo.getChatRoomNum(), cvo);
    }

    @EventListener
    public void webSocketMessageListener(SessionDisconnectEvent e) throws Exception {
        // 유저 퇴장 시, 유저 확인을 위한 메서드
        log.info("Disconnect {}", e);

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(e.getMessage());
        // 메시지 타입이 지정된 접근자 메서드를 통해 Header 정보에 접근

        // STOMP 세션에 있던 UUID 와 채팅방 번호를 확인한 후,
        // 채팅방에 있는 유저 리스트와 채팅방에서 해당 유저를 삭제.
        String userUUID = (String) headerAccessor.getSessionAttributes().get("userUUID");
        String chatRoomNum = (String) headerAccessor.getSessionAttributes().get("chatRoomNum");

        log.info("headerAccessor {}", headerAccessor);

        cSrv.removeUser(chatRoomNum); // 채팅방에 있는 유저가 나감.

        // 채팅방 유저 리스트에서 UUID 유저 닉네임 조회 및 리스트에서 유저 삭제(수정)
        String userName = cSrv.whoUserName(chatRoomNum, userUUID); // 유저 닉네임 조회
        cSrv.delUserList(chatRoomNum, userUUID); // 해당 유저를 유저리스트에서 삭제

        if (userName != null) { // 해당 유저 이름이 없을 경우, (수정)
            log.info("user Disconnected : " + userName);

            // ChatInfoVO 의 builder 어노테이션 참고...
            ChatInfoVO cvo = ChatInfoVO.builder()
                    .type(ChatInfoVO.MessageType.LEAVE) // 메시지 타입
                    .sendName(userName)
                    .message(userName + "님이 퇴장하셨습니다.")
                    .build();

            simpMO.convertAndSend("/sub/chatting/room/" + chatRoomNum, cvo);
        }
    }

    @GetMapping("/chatting/userList")
    @ResponseBody
    // ResponseBody 어노테이션을 명시함으로써 MessageConverter 를 통한 데이터 변환 과정을 거침.
    // 자바 객체를 HttpResponse 의 본문 responseBody 의 내용으로 매핑하는 역할을 함.
    public ArrayList<String> userList(String chatRoomNum) throws Exception {
        // 채팅방에 있는 유저 리스트 반환...
        return cSrv.chatUserList(chatRoomNum);
    }

    @GetMapping("/chatting/userSame")
    @ResponseBody
    public String userSame(@RequestParam("chatRoomNum") String chatRoomNum
            , @RequestParam("userName") String userName) throws Exception {
        // 채팅에 참여한 유저 닉네임 중복 확인을 위한 메서드

        // 유저이름 확인
        String whoUser = cSrv.userSame(chatRoomNum, userName); // 중복인지 확인
        log.info("whoUser {}", whoUser);

        return whoUser;

    }

}
