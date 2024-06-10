package com.smartwave.tripsns.controller;

import com.smartwave.tripsns.service.IF_JoinService;
import com.smartwave.tripsns.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class JoinController {




    @GetMapping(value = "join")
    public String join() {return "joinForm";}

    @Autowired
    IF_JoinService snsService;
    @PostMapping(value = "joinsave")
    public String joinsave(@ModelAttribute UserVO uservo) throws Exception {
        snsService.userinsert(uservo);
        return "redirect:login";
    }
    @ResponseBody
    @PostMapping(value = "idchk")
    public int idchk(@RequestParam("originid") String id) throws Exception {
        int cnt = snsService.idchk(id);
        return cnt;
    }


}
