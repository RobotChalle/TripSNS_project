package com.smartwave.tripsns.service;

import com.smartwave.tripsns.dao.IF_ChatDAO;
import com.smartwave.tripsns.vo.ChatRoomVO;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ChatServiceImpl implements IF_ChatService {

    @Autowired
    IF_ChatDAO cdao;

    private Map<String, ChatRoomVO> chatMap; // 채팅방 정보 관리 목적.
                                            // 추후 DB 연결 시 변경 (지금은 채팅 테스팅 목적)

    @PostConstruct
    public void init() {
        chatMap = new HashMap<>();
    }

    @Override
    public List<ChatRoomVO> ChatList() throws Exception { // 전체 채팅방 리스트

        List<ChatRoomVO> chatRoom = new ArrayList<>(chatMap.values()) ;
        Collections.reverse(chatRoom); // 채팅방 순서 생성 순으로 변경(정확히는 순서 정반대)

        return chatRoom;
    }

    @Override
    public ChatRoomVO findChatNum(String chatRoomNum) throws Exception { // 채팅방번호 기준으로 채팅방 찾기
        return chatMap.get(chatRoomNum); // 추후 DB에 저장
    }

    @Override
    public ChatRoomVO createChatRoom(String chatName) throws Exception { // 채팅방 생성
        ChatRoomVO cRoomName = new ChatRoomVO().create(chatName);

        chatMap.put(cRoomName.getChatRoomNum(), cRoomName); // Map에 채팅방 번호와 채팅방 저장

        return cRoomName;
    }

    @Override
    public void addUser(String chatRoomNum) throws Exception { // 채팅방 인원 추가
        ChatRoomVO cRoom = chatMap.get(chatRoomNum);
        cRoom.setUserCnt(cRoom.getUserCnt()+1); ;

    }

    @Override
    public void removeUser(String chatRoomNum) throws Exception { // 채팅방 나감 (인원 -1)

        ChatRoomVO cRoom = chatMap.get(chatRoomNum);
        cRoom.setUserCnt(cRoom.getUserCnt()-1);

    }

    @Override
    public String addUserList(String chatRoomNum, String sendName) throws Exception { // 채팅방의 유저리스트에 유저 추가

        ChatRoomVO cRoom = chatMap.get(chatRoomNum);
        String sender = UUID.randomUUID().toString(); // UUID : 네트워크 상에서 고유성이 보장되는 id

        // 채팅 상 유저이름 중복 확인 후, 유저리스트에 추가(중복이라면 이름 뒤에 랜덤숫자 추가로 붙임)
        cRoom.getUserList().put(sender, sendName) ;

        return sender;
    }

    @Override
    public String userSame(String chatRoomNum, String sendName) throws Exception { // 채팅방 유저이름 중복 확인
        ChatRoomVO cRoom = chatMap.get(chatRoomNum);
        String whatName = sendName ;

        // 채팅방 내 유저이름이 중복이라면 나중에 들어온 유저의 이름 뒤에 랜덤 숫자 붙임(이름 재부여)
        while (cRoom.getUserList().containsKey(whatName)) { // 중복이름 안 나올 때까지 반복
            Random r = new Random();
            int ranNum = r.nextInt(100)+1;
            whatName = sendName + ranNum ;
        }
        return whatName;
    }

    @Override
    public void delUserList(String chatRoomNum, String sender) throws Exception { // 채팅방의 유저리스트에 유저 삭제

        ChatRoomVO cRoom = chatMap.get(chatRoomNum) ;
        cRoom.getUserList().remove(sender) ;

    }

    @Override
    public String whoUserName(String chatRoomNum, String sender) throws Exception { // 채팅방 채팅자명 조희
        ChatRoomVO cRoom = chatMap.get(chatRoomNum) ;
        return cRoom.getUserList().get(sender);
    }

    @Override
    public ArrayList<String> chatUserList(String chatRoomNum) throws Exception { // 채팅방의 전체 유저 조회
        ArrayList<String> cUserList = new ArrayList<>();

        ChatRoomVO cRoom = chatMap.get(chatRoomNum) ;

        // value 값만 뽑아 채팅유저리스트에 저장 후 return
        cRoom.getUserList().forEach((key, value) -> cUserList.add(value));

        return cUserList;
    }


}
