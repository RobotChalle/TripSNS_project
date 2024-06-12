package com.smartwave.tripsns.controller;

import com.smartwave.tripsns.service.IF_SNSService;
import com.smartwave.tripsns.util.FileDataUtil;
import com.smartwave.tripsns.vo.PostCommentVO;
import com.smartwave.tripsns.vo.PostVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller //체인지지
public class SNSController {

    @Autowired
    IF_SNSService sService;

    @Autowired
    FileDataUtil fileDataUtil;

    @GetMapping("/main") //메인화면 불러오기
    public String main(Model model) throws Exception {
        List<PostVO> allList = sService.postSelectAll();
        model.addAttribute("allListPost", allList);
        return "main";
    }

    @GetMapping("/post") //글 작성화면 불러오기
    public String post(Model model, @RequestParam("p_id") String p_id) {
        model.addAttribute("p_id", p_id);
        return "post";
    }

    @GetMapping("/detail") //자세히 보기 불러오기
    public String detail(Model model, @RequestParam("p_no") String p_no, @RequestParam("p_id") String p_id) throws Exception {
        PostVO postDetail = sService.postSelectOne(p_no);
        String attachName = sService.getFile(p_no);
        List<PostCommentVO> commentList = sService.commentList(p_no);
        model.addAttribute("postDetail", postDetail);
        model.addAttribute("attachName", attachName);
        model.addAttribute("commentList", commentList);
        model.addAttribute("p_id", p_id);
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

    @GetMapping(value = "/postToggleLike") //좋아요 저장
    public Map<String, Object> postToggleLike(PostVO pvo) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @GetMapping(value = "postLikeChk")
    public Map<String, Object> postLikeChk(HttpSession session, PostVO pvo) throws Exception {
        Map<String, Object> map = new HashMap<>();
        PostVO sessionUser = (PostVO) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            map.put("result", "fail");
            map.put("reason", "로그인이 되어있지 않습니다.");
            return map;
        }
        pvo.setP_id(sessionUser.getP_id());
        map.put("result", "success");
        map.put("data", sService.likeChk(pvo));
        map.put("data", map);
        return map;
    }

    @GetMapping("postLikeCnt")
    public Map<String, Object> postLikeCnt(PostVO pvo) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        map.put("count", sService.postLikeCnt(pvo));
        return map;
    }
}
