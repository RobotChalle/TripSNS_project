package com.smartwave.tripsns.controller;

import com.smartwave.tripsns.service.IF_SNSService;
import com.smartwave.tripsns.util.FileDataUtil;
import com.smartwave.tripsns.vo.PostCommentVO;
import com.smartwave.tripsns.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller //체인지지
public class SNSController {

    @Autowired
    IF_SNSService sService;

    @Autowired
    FileDataUtil fileDataUtil;

    @GetMapping("/main") //메인화면 불러오기
    public String main(Model model) throws Exception {
        List<PostVO> allList = sService.postSelectPost();
        model.addAttribute("allListPost", allList);
        return "main";
    }

    @GetMapping("/post") //글 작성화면 불러오기
    public String post(Model model, @RequestParam("p_id") String p_id) throws Exception {
        model.addAttribute("p_id", p_id);
        return "post";
    }

    @GetMapping("/detail") //자세히 보기 불러오기
    public String detail(Model model, @RequestParam("p_no") String p_no, @RequestParam("p_id") String p_id) throws Exception {
        PostVO postDetail = sService.postSelectOne(p_no);
        List<String> attachNameList = sService.getFile(p_no);
        List<PostCommentVO> commentList = sService.commentList(p_no);
        int postLikeCnt = sService.postLikeCnt(p_no);
        int postCommentCnt = sService.postCommentCnt(p_no);
        model.addAttribute("postDetail", postDetail);
        model.addAttribute("attachNameList", attachNameList);
        model.addAttribute("commentList", commentList);
        model.addAttribute("p_id", p_id);
        model.addAttribute("postLikeCnt", postLikeCnt);
        model.addAttribute("postCommentCnt", postCommentCnt);
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
    public String commentSave(@ModelAttribute PostCommentVO pcvo) throws Exception {
        sService.CommentSave(pcvo);

        return "redirect:main";
    }

    @GetMapping(value = {"/shorts", "/"})
    public String shorts() {
        return "shorts";
    }

    @GetMapping(value = "/short")
    public String shortOne() {
        return "short";
    }

    @GetMapping(value = "postLike")
    public String postLike(@ModelAttribute PostVO pvo) throws Exception {
        sService.postLike(pvo);
        return "redirect:main";
    }

    @GetMapping(value = "postDelete") //게시글 삭제
    public String postDelete(@ModelAttribute PostVO pvo) throws Exception {
        sService.postDelete(pvo);
        return "redirect:main";
    }

    @GetMapping(value = "postModifyForm") //게시글 수정페이지
    public String postModifyForm(Model model, @RequestParam("p_no") String p_no) throws Exception {
        model.addAttribute("p_no", p_no);
        return "postModifyForm";
    }

    @PostMapping(value = "postModify") //게시글 수정
    public String postModify(@ModelAttribute PostVO pvo) throws Exception {
        sService.postModify(pvo);
        return "redirect:main";
    }

    @GetMapping(value = "postCommentDelete") //댓글 삭제
    public String postCommentDelete(@ModelAttribute PostCommentVO pcvo) throws Exception {
        sService.postCommentDelete(pcvo);
        return "redirect:main";
    }
}
