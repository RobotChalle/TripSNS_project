package com.smartwave.tripsns.controller;

import com.smartwave.tripsns.service.IF_ChatService;
import com.smartwave.tripsns.vo.ChatRoomVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Slf4j
public class ChatController {

    @Autowired
    private IF_ChatService cSrv;

    @GetMapping("/chatList")
    public String chatList(Model model) throws Exception { // 채팅 리스트
        model.addAttribute("cUserList", cSrv.ChatList()) ;
        log.info("show all chatList {}" , cSrv.ChatList());
        return "chatList";
    }

    @PostMapping("/chatting/createroom")
    public String createChatRoom(@RequestParam String chatName, RedirectAttributes ratr) throws Exception { // 채팅방 생성
        ChatRoomVO cRoom = cSrv.createChatRoom(chatName) ;
        log.info("create chatRoom {}", cRoom);
        ratr.addFlashAttribute("cRoom", cRoom);
        return "redirect:chatList";
    }

    @GetMapping("/chatting/chatIn")
    public String chatDetail(Model model, String chatRoomNum) throws Exception { // 채팅방 입장
        log.info("chatRoomNum {}", cSrv.findChatNum(chatRoomNum));
        model.addAttribute("cRoomIn", cSrv.findChatNum(chatRoomNum));
        return "chatGo"; // 채팅방 화면
    }

}
