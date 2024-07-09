package com.smartwave.tripsns.controller;

import com.smartwave.tripsns.service.MailService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;
    @ResponseBody
    @PostMapping("/mail")//ajax 로 받을 식별자
    public String MailSend(String mail){

        int number = mailService.sendMail(mail);// 받은 이메일 매개변수로 활용

        String num = "" + number; // 랜덤 숫자

        return num;
    }






}
