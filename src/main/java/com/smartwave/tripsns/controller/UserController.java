package com.smartwave.tripsns.controller;


import com.smartwave.tripsns.service.IF_SNSService;
import com.smartwave.tripsns.service.IF_UserService;
import com.smartwave.tripsns.util.FileDataUtil;
import com.smartwave.tripsns.vo.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;


@Controller
public class UserController {


    @Autowired
    private FileDataUtil fileDataUtil;


    //회원가입
    @GetMapping(value = "join")
    public String join() {
        return "joinForm";
    }

    @Autowired
    IF_UserService userservice;

    @Autowired
    IF_SNSService sService;

    @PostMapping(value = "joinsave")//회원정보 입력 버튼
    public String joinsave(@ModelAttribute UserVO uservo) throws Exception {
        userservice.userinsert(uservo);
        userservice.userprofile(uservo);//프로필 기본정보
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
        return "loginForm";
    }

    @PostMapping("loginsave")//로그인 버튼
    public String loginsave(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session, RedirectAttributes rt) throws Exception {
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
                }
                session.setAttribute("userid", uvo.getId());
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


    @GetMapping(value = "logout")//로그아웃
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    //회원 탈퇴 페이지
    @GetMapping(value = "withdrawform")
    public String withdrawform(@SessionAttribute("userid") String u_id,Model model) throws Exception{
        String orginpw = userservice.getpw(u_id);//db에서 로그인한 pw 가져옴
        model.addAttribute("orginpw", orginpw);
        model.addAttribute("id", u_id);
        return "withdraw";
    }
    //회원탈퇴
    @PostMapping(value = "withdrawal")
    public String userdelete(@RequestParam("id") String id , HttpSession session ) throws Exception {
        userservice.userdelete(id);
        return "redirect:login";
    }
    // 프로필 기본정보 html 보내기 (한줄소개 코멘트, 프로필 기본사진)
    @GetMapping(value = "profile")
    public String profile(@SessionAttribute("userid") String u_id,Model model) throws Exception {
       String mainsuser = u_id;
        model.addAttribute("mainsuser", mainsuser);
        ProfileVO prodetail = userservice.getProfile(u_id);
        model.addAttribute("profiledetail", prodetail);
        model.addAttribute("profiledetail2", prodetail);
        model.addAttribute("followuser", mainsuser);// 로그인 아이디를 팔로우 아이디
        //게시글 목록
        List<PostVO> postVOList = sService.postSelectAll(u_id);
        model.addAttribute("postVOList", postVOList);
        //쇼츠 목록
        List<ShortVO> shortVOList = sService.userShortList(u_id);
        model.addAttribute("shortVOList", shortVOList);
        return "profile";
    }
    // 상대 프로필 기본정보 html 보내기 (한줄소개 코멘트, 프로필 기본사진)
    @GetMapping(value = "otherprofile")
    public String otherprofile(@RequestParam("p_id") String u_id,Model model,@SessionAttribute("userid") String myid) throws Exception {
        String otheruser = u_id;
        String mainsuser = myid;
        System.out.println("끌고온 상대방아이디"+otheruser);
        if(mainsuser.equals(otheruser) ){
            return "redirect:profile";
        }else{
            model.addAttribute("otheruser", otheruser);// 파라미터 로 받은 아이디를 팔로우 아이디
            model.addAttribute("followuser", otheruser);
            ProfileVO prodetail = userservice.getProfile(u_id);
            ProfileVO myprodetail = userservice.getProfile(myid);
            model.addAttribute("profiledetail", prodetail);
            model.addAttribute("profiledetail2", myprodetail);

            //게시글 목록
            List<PostVO> postVOList = sService.postSelectAll(u_id);
            model.addAttribute("postVOList", postVOList);
            return "profile";
        }




    }



    /// 프로필 수정 세션값 넘기기
    @GetMapping(value = "proupdate")
    public String proupdate(Model model, @SessionAttribute("userid") String u_id) throws Exception{
        ProfileVO prodetail = userservice.getProfile(u_id);
        model.addAttribute("profiledetail", prodetail);

        model.addAttribute("id", u_id);
        return "profileMod";
    }

    // 개인정보 수정
    @PostMapping(value = "userupdate")
    public String userupdate(@ModelAttribute UserVO uvo) throws Exception{
        userservice.userupdate(uvo);
        return "redirect:profile"; // 프로필 화면 넣기 redirect 식별자
    }
    // 프로필 수정
    @PostMapping(value = "profileupdate")
    public String profileupdate(@ModelAttribute ProfileVO pvo, MultipartFile[] pfile,@RequestParam("u_intro") String intro) throws Exception{
       String filename =fileDataUtil.fileUpload(pfile)[0];
        if(filename==null){
            // 파일선택 안햇을시 > null 값일시 한줄소개만 변경 
            pvo.setU_intro(intro);
            userservice.introupdate(pvo);
            return "redirect:/profile";
        }else{
            //파일선택한 경우 >파일, 한줄소개 둘다 변경
            pvo.setFilename(filename);
            userservice.profileupdate(pvo);
            return "redirect:profile"; // 프로필 화면 넣기 redirect 식별자
        }
    }
    @ResponseBody
    @PostMapping(value = "postcnt")//나의 게시물수
    public int postcnt(@RequestParam("myid") String id ) throws Exception {
        int pcnt = userservice.postcnt(id);
        return pcnt;
    }
    @ResponseBody
    @PostMapping(value = "shortcnt")//나의 쇼츠 수
    public int shortcnt(@RequestParam("myid") String id ) throws Exception {
            int scnt = userservice.shortcnt(id);
            return scnt;
    }
    @ResponseBody
    @PostMapping(value = "otherpostcnt")//상대방 게시물수
    public int otherpostcnt(@RequestParam("otherid") String id) throws Exception {
        int pcnt = userservice.postcnt(id);
        return pcnt;
    }
    @ResponseBody
    @PostMapping(value = "othershortcnt")//상대방 쇼츠 수
    public int othershortcnt(@RequestParam("otherid") String id ) throws Exception {
        int scnt = userservice.shortcnt(id);
        return scnt;
    }


    //관리자 계정 삭제
    @GetMapping(value = "withdrawalManger")
    public String withdrawalManger(@RequestParam("id") String id, HttpSession session) throws Exception {
        userservice.userdelete(id);
        return "redirect:manager";
    }
    // 관리자 체크된 튜플 삭제
    @GetMapping(value = "delchk")
    public String chkdel(@RequestParam("id") String[] id, HttpSession session) throws Exception {
        userservice.chkdel(id);
        return "redirect:manager";
    }
    //관리자 회원검색  > 검색페이지로 이동
    @PostMapping(value = "userSearchList")
    public String userSearchList(@SessionAttribute("userid") String u_id,@RequestParam("searchitem")String usercolumn,@RequestParam("usersearch") String usersearch,Model model) throws Exception {
        HashMap<String, String> userselect = new HashMap<>();
        userselect.put("usercolumn", usercolumn);
        userselect.put("usersearch", usersearch);
        List<UserVO> selectUserLists =userservice.selectUserList(userselect);
        model.addAttribute("userList", selectUserLists);
        //프로필 사진 불러오기
        ProfileVO prodetail = userservice.getProfile(u_id);
        model.addAttribute("profiledetail", prodetail);
        return "userManageSearch";
    }
    // 팔로우
    @ResponseBody
    @PostMapping(value = "follow")
    public int follow(@ModelAttribute FollowVO fvo)throws Exception{
        userservice.follow(fvo);// 팔로우 삽입 -> 서비스단에서 판단해서 팔로우 한상태면 db 에서 delete , 안한상태면 삽입
        int cnt =0;// 팔로우 했던사람인지 여부
        if(userservice.selectFollow(fvo) == null){// db에 값이 없다면 팔로우 가능함
            return cnt;
        }else{//db 에 값있으면 이미 팔로우 한 사람이여서 못하게
            cnt =1;
        }
        return cnt;
    }
    // 팔로우 체크 (html 문서가 로딩되었을시 바로 함수 작동해서 팔로우 한 상태인지 안한상태인지 확인 여부 )
    @ResponseBody
    @PostMapping(value = "followchk")
    public int followchk(@ModelAttribute FollowVO fvo)throws Exception{
        int cnt =0;// 팔로우 했던사람인지 여부
        if(userservice.selectFollow(fvo) != null){// db에 값이 있으면 팔로우 한 상태 -> 1 리턴
            cnt=1;
        }
        return cnt; // 위에조건 넘겨서 db 값 없으면 팔로우 가능 상태 0 리턴
    }
    //상대 화면 팔로워수 get 팔로우 당한사람 아이디의 팔로워개수
    @ResponseBody
    @PostMapping(value = "followerCount")
    public int followerCount(@ModelAttribute FollowVO fvo)throws Exception{
        int followercnt = userservice.followercount(fvo);
        System.out.println(followercnt);
        return followercnt;
    }
    //로그인 한 나의 화면 팔로우 수 get 내가 팔로우 버튼을 클릭했을시 팔로우한 아이디의 팔로우개수
    @ResponseBody
    @PostMapping(value = "followCount")
    public int followCount(@ModelAttribute FollowVO fvo)throws Exception{
        int followcnt = userservice.followcount(fvo);
        System.out.println("팔로우랑꼐"+followcnt);
        return followcnt;
    }
    @GetMapping(value = "followList")
    public String followList(@RequestParam("id") String userid,Model model, @SessionAttribute("userid") String u_id)throws Exception{
        //팔로우,팔로워 목록 받아올 메서드 자리
        System.out.println("받은 아디"+userid);
        List<FollowVO> followList = userservice.followList(userid);
        List<FollowVO> followerList = userservice.followerList(userid);
        model.addAttribute("followList", followList);
        model.addAttribute("followerList", followerList);
        //프로필 사진 불러오기
        ProfileVO prodetail = userservice.getProfile(u_id);
        model.addAttribute("profiledetail", prodetail);
        return "followList";
    }




}
