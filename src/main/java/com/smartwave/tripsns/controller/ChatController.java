package com.smartwave.tripsns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/chatList")
    public String chatList(Model model) {
        return "chatList";
    }

}
