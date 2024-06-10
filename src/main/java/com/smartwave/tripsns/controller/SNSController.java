package com.smartwave.tripsns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SNSController {
    @GetMapping(value = {"/shorts","/"})
    public String shorts() {return "shorts";}
    @GetMapping(value="/short")
    public String shortOne() {return "short";}
}
