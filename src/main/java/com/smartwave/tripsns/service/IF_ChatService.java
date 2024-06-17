package com.smartwave.tripsns.service;

import com.smartwave.tripsns.vo.ChatRoomVO;

import java.util.ArrayList;
import java.util.List;

public interface IF_ChatService { // 채팅 서비스 인터페이스

//    public Map<String, Object> chatRoomList(Map<String, Object> map) throws Exception; // 채팅방 정보(+ 채팅내용)
//
//    public Map<String, Object> createChatRoom(Map<String, Object> paramMap) throws Exception; // 채팅방 생성
//
//    public void saveChat(Map<String, Object> Map) throws Exception; // 채팅 저장

    public List<ChatRoomVO> ChatList() throws Exception; // 전체 채팅방 리스트

    public ChatRoomVO findChatNum(String chatRoomNum) throws Exception; // 채팅방 아이디로 채팅방 찾기

    public ChatRoomVO createChatRoom(String chatName) throws Exception; // 채팅방 생성

    public void addUser(String chatRoomNum) throws Exception; // 채팅방 인원 추가.

    public void removeUser(String chatRoomNum) throws Exception; // 채팅방 나감(인원 -1)

    public String addUserList(String chatRoomNum, String sendName) throws Exception; // 채팅방 유저리스트에 유저 추가

    public String userSame(String chatRoomNum, String sendName) throws Exception; // 채팅방 유저 이름 중복 확인

    public void delUserList(String chatRoomNum, String sender) throws Exception; // 채팅방 유저리스트에서 유저 삭제

    public String whoUserName(String chatRoomNum, String sender) throws Exception; // 채팅방 채팅자명 조회

    public ArrayList<String> chatUserList(String chatRoomNum) throws Exception; // 채팅방에 들어간 전체 userList 조회


}
