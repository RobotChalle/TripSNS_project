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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
public class SNSController {
    @Autowired
    IF_UserService userservice;
    @Autowired
    IF_SNSService sService;

    @Autowired
    FileDataUtil fileDataUtil;

    @GetMapping("/main")//메인화면 불러오기
    public String main(Model model,
                       @SessionAttribute("userid") String u_id) throws Exception {

        List<PostVO> allList = sService.postSelectPost();
        model.addAttribute("allListPost", allList);
        //프로필 사진 불러오기
        ProfileVO prodetail = userservice.getProfile(u_id);
        model.addAttribute("profiledetail", prodetail);
        return "main";
    }

    @GetMapping("/post") //글 작성화면 불러오기
    public String post(Model model, @RequestParam("p_id") String p_id, @SessionAttribute("userid") String u_id) throws Exception {
        model.addAttribute("p_id", p_id);
        //프로필 사진 불러오기
        ProfileVO prodetail = userservice.getProfile(u_id);
        model.addAttribute("profiledetail", prodetail);
        return "post";
    }

    @GetMapping("/detail") //자세히 보기 불러오기
    public String detail(Model model, @RequestParam("p_no") String p_no, @RequestParam("p_id") String p_id, @SessionAttribute("userid") String u_id) throws Exception {
        PostVO postDetail = sService.postSelectOne(p_no);
        List<PostCommentVO> commentList = sService.commentList(p_no);
        int postCommentCnt = sService.postCommentCnt(p_no);
        model.addAttribute("postDetail", postDetail);
        model.addAttribute("commentList", commentList);
        model.addAttribute("postCommentCnt", postCommentCnt);
        //프로필 사진 불러오기
        ProfileVO prodetail = userservice.getProfile(u_id);
        model.addAttribute("profiledetail", prodetail);
        return "detail";
    }

    @PostMapping("/posting") //글 저장
    public String posting(@ModelAttribute PostVO pvo, MultipartFile[] file) throws Exception {
        String[] filename = fileDataUtil.fileUpload(file);
        pvo.setFilename(filename);
        sService.postInsert(pvo);
        return "redirect:main";
    }

    @PostMapping("/commentSave") //댓글 저장
    public String commentSave(@ModelAttribute PostCommentVO pcvo, @ModelAttribute PostVO pvo) throws Exception {
        sService.CommentSave(pcvo);
        return "redirect:detail?p_no=" + pvo.getP_no() + "&p_id=" + pvo.getP_id();
    }

    @GetMapping(value = {"/shorts"})
    public String shorts(Model model) throws Exception {
        List<ShortVO> shortList = sService.allShortList();
        model.addAttribute("allShortList", shortList);
        return "shorts";
    }

    @GetMapping(value = "/short/view")
    public String shortOne(Model model, @RequestParam("s_no") int s_no) throws Exception {
        ShortVO shortDetails = sService.shortDetails(s_no);
        model.addAttribute("shortDetails", shortDetails);
        return "short";
    }

    @GetMapping(value = "/addShortVideo")
    public String addShortVideo() {
        return "addShortVideo";
    }

    @PostMapping(value = "/addShort")
    public String addShort(Model model, @ModelAttribute VideoVO vvo, MultipartFile[] file) throws Exception {
        String[] filename = fileDataUtil.fileUpload(file);
        vvo.setSv_addr(filename[0]);
        vvo.setSv_thumbnail(filename[1]);
        sService.videoInsert(vvo);
        model.addAttribute("video", filename[0]);
        model.addAttribute("thumbnail", filename[1]);
        model.addAttribute("videoNo", sService.videoSelect());
        return "addShort";
    }

    @PostMapping(value = "/insertShort")
    public String insertShort(@ModelAttribute ShortVO svo) throws Exception {
        sService.InsertShort(svo);
        return "redirect:shorts";
    }


    @GetMapping(value = "postModifyForm") //게시글 수정페이지
    public String postModifyForm(Model model, @RequestParam("p_no") String p_no, @SessionAttribute("userid") String u_id) throws Exception {
        model.addAttribute("p_no", p_no);
        //프로필 사진 불러오기
        ProfileVO prodetail = userservice.getProfile(u_id);
        model.addAttribute("profiledetail", prodetail);
        return "postModifyForm";
    }

    @PostMapping(value = "postModify") //게시글 수정
    public String postModify(@ModelAttribute PostVO pvo) throws Exception {
        sService.postModify(pvo);
        return "redirect:detail?p_no=" + pvo.getP_no() + "&p_id=" + pvo.getP_id();
    }

    @GetMapping(value = "postDelete")
    public String postDelete(@ModelAttribute PostVO pvo) throws Exception {
        sService.postDelete(pvo);
        return "redirect:main";
    }

    @GetMapping(value = "postCommentDelete") //댓글 삭제
    public String postCommentDelete(@ModelAttribute PostCommentVO pcvo, @ModelAttribute PostVO pvo) throws Exception {
        sService.postCommentDelete(pcvo);
        return "redirect:detail?p_no=" + pvo.getP_no() + "&p_id=" + pvo.getP_id();
    }

    @GetMapping(value = "postSearchById") //게시글 검색
    public String postSearchById(@RequestParam("searchWord") String searchWord, Model model, @SessionAttribute("userid") String u_id) throws Exception {
        List<PostVO> pvo = sService.postSearch(searchWord);
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("pvo", pvo);
        //프로필 사진 불러오기
        ProfileVO prodetail = userservice.getProfile(u_id);
        model.addAttribute("profiledetail", prodetail);
        return "searchResult";
    }

    @ResponseBody
    @GetMapping(value = "postLike") //게시글 좋아요
    public int postLike(@ModelAttribute PostVO pvo) throws Exception {
        int chk = 0;
        sService.postLike(pvo);
        if (sService.postLikeSelect(pvo) == null) {
            return chk;
        } else {
            chk = 1;
            return chk;
        }
    }

    @ResponseBody
    @GetMapping(value = "postLikeSelect") //게시글 좋아요 확인
    public int postLikeSelect(@ModelAttribute PostVO pvo) throws Exception {
        int chk = 0;
        if (sService.postLikeSelect(pvo) == null) {
            return chk;
        } else {
            chk = 1;
            return chk;
        }
    }

    @ResponseBody
    @GetMapping(value = "postLikeCount") //게시글 좋아요 갯수
    public int postLikeCount(@ModelAttribute PostVO pvo) throws Exception {
        int postLikeCount = sService.postLikeCOunt(pvo);
        return postLikeCount;
    }

    @GetMapping(value = "postLikeDetail") //게시글 자세히보기 좋아요 후 같은 글 자세히보기로 이동
    public String postLikeDetail(@ModelAttribute PostVO pvo) throws Exception {
        sService.postLike(pvo);
        return "redirect:detail?p_no=" + pvo.getP_no() + "&p_id=" + pvo.getP_id();
    }

}