package com.smartwave.tripsns.controller;

import com.smartwave.tripsns.service.IF_ChatService;
import com.smartwave.tripsns.vo.ChatRoomVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
public class ChatController {

    @Autowired
    private IF_ChatService cSrv;

    @GetMapping("/chatList")
    public String chatList(Model model) throws Exception { // 채팅 리스트
        model.addAttribute("cUserList", cSrv.ChatList()) ;

        return "chatList";
    }

    @PostMapping("/chatting/createroom")
    public String createChatRoom(@RequestParam String chatName, RedirectAttributes ratr) throws Exception {
        ChatRoomVO cRoom = cSrv.createChatRoom(chatName) ;
        ratr.addFlashAttribute("cRoom", cRoom);
        return "redirect:chatList";
    }

}
