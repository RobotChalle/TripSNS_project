package com.smartwave.tripsns.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SNSController {
    @GetMapping("shorts")
    public String shorts(Model model) {
        return "shorts";
    }
    @GetMapping(value = "/login")
    public String login() {return "loginForm";}
    @GetMapping(value = "/join")
    public String join() {return "joinForm";}
}
