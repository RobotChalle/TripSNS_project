package com.smartwave.tripsns.controller;


import com.smartwave.tripsns.service.IF_UserService;
import com.smartwave.tripsns.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {



//회원가입
    @GetMapping(value = "join")
    public String join() {return "joinForm";}

    @Autowired
    IF_UserService userservice;
    @PostMapping(value = "joinsave")//회원정보 입력 버튼
    public String joinsave(@ModelAttribute UserVO uservo) throws Exception {
        userservice.userinsert(uservo);
        return "redirect:login";
    }
    @ResponseBody
    @PostMapping(value = "idchk")//중복체크 버튼
    public int idchk(@RequestParam("originid") String id) throws Exception {
        int cnt = userservice.idchk(id);
        return cnt;
    }








    //로그인
    @GetMapping(value = "login")//로그인폼
    public String login() {
        return "loginForm";}
    @PostMapping("loginsave")//로그인 버튼
    public String loginsave(@RequestParam("id") String id , @RequestParam("pw") String pw, HttpSession session , RedirectAttributes rt) throws Exception {
        //model 객체가 redirect 하는순간 사라지기 떄문에 값을 넘기기위해  RedirectAttributes 공부하여  리다이렉트 페이지로 데이터 넘기기위해 적용 ,
        // 임시로 저장하는 방식인 flashattribute 적용 하여 세션에 저장되어 사용된 뒤에 자동으로 삭제되도록함 -> 임시로 사용되는 데이터 다루기위해 사용(리다이렉트 직전 플래시에 저장하는 메서드, Redirect 이후에는 소멸)
        //addAttribute() 로 넘겼다면, URL 로 넘어온만큼 기존처럼 @RequestParam 어노테이션을 이용
        //addFlashAttribute() 로 넘겼다면, @ModelAttribute 어노테이션을 이




        UserVO uvo = userservice.login(id);
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
            }else {
                //비밀번호 틀릴경우
//                System.out.println("비밀번호가 일치x");
                rt.addFlashAttribute("mesg","비밀번호가 일치하지않습니다");
                return "redirect:/login";
            }
        }else{
            // 아이디 없을경우
//            System.out.println("아이디 존재x");
            rt.addFlashAttribute("mesg","존재하지않는 아이디 입니다");
            return "redirect:/login";
        }
        return "redirect:main";
    }
//    @GetMapping(value = "test")
//    public String test(HttpSession session , Model model) throws Exception {
//        return "test";
//    }

    @GetMapping(value = "logout")//로그아웃
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping(value = "profile")
    public String profile() {
        return "profile";
    }


    /// 프로필 수정
    @GetMapping(value = "proupdate")
    public String proupdate(Model model, @RequestParam("id") String id) {
        model.addAttribute("id", id);
        return "profileMod";
    }


    @PostMapping(value = "userupdate")
    public String userupdate(@ModelAttribute UserVO uvo) throws Exception{
        userservice.userupdate(uvo);
        return "redirect:main"; // 프로필 화면 넣기 redirect 식별자
    }



}
