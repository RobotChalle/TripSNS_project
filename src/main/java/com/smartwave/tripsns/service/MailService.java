package com.smartwave.tripsns.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private static final String senderEmail = "kjy76882@gmail.com";//보낸사람 이메일 내이메일
    private static int number;
    public static void createNumber() {
        number = (int) (Math.random() * (90000)) + 100000;// 100000부터 189999 사이의 정수를 생성
    }
    public MimeMessage CreateMail(String mail) {
        createNumber();
        MimeMessage doyoungMessage = javaMailSender.createMimeMessage();
        try {
            doyoungMessage.setFrom(senderEmail);
            doyoungMessage.setRecipients(MimeMessage.RecipientType.TO, mail);
            doyoungMessage.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "TripSNS 이용해주셔서 감사합니다." + "</h3>";
            doyoungMessage.setText(body, "UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return doyoungMessage;
    }
    public int sendMail(String mail) {
        MimeMessage doyoungMessage = CreateMail(mail);
        javaMailSender.send(doyoungMessage);
        return number;
    }

}


