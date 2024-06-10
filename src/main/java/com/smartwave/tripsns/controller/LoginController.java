package com.smartwave.tripsns.controller;

import com.smartwave.tripsns.service.IF_LoginService;
import com.smartwave.tripsns.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @GetMapping(value = "login")
    public String login() {

        return "loginForm";}
    @Autowired
    IF_LoginService loginservice;

    @PostMapping("loginsave")
    public String loginsave(@RequestParam("id") String id , @RequestParam("pw") String pw, HttpSession session , RedirectAttributes rt) throws Exception {
        UserVO uvo = loginservice.login(id);
//        System.out.println("아이디:"+uvo.getId()+"비번:"+uvo.getPw());
        // 리턴받아온 db 의 pw 와 login.html에서 post 방식으로 받아온 pw 일치확인
        if (uvo != null) {
            if (uvo.getPw().equals(pw)){
                //비밀번호가 일치한후 접속시 쿠키값을 서버에 전송하고
                // 서버에서는 쿠키값을 참고하여 세선에 등록된 변수값을 가져오도록 설정
                if (session.getAttribute("userid") != null){// userid값이 존재한다면 -> 쓰레기값이 있다면
                    session.removeAttribute("userid");
                    session.removeAttribute("userpw");

                }
                session.setAttribute("userid", uvo.getId());
                session.setAttribute("userpw", uvo.getPw());
                System.out.println("id"+ session.getAttribute("userid"));
            }else {
                //비밀번호 틀릴경우
                System.out.println("비밀번호가 일치x");
                rt.addFlashAttribute("mesg","비밀번호가 일치하지않습니다");
                return "redirect:/login";



            }
        }else{
            // 아이디 없을경우
            System.out.println("아이디 존재x");
            rt.addFlashAttribute("mesg","존재하지않는 아이디 입니다");
            return "redirect:/login";
        }

        return "test";
    }
    @GetMapping(value = "test")
    public String test(HttpSession session , Model model) throws Exception {
        return "test";
    }

    @GetMapping(value = "logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/test";
    }

}
