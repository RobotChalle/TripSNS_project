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

@Controller
public class SNSController {

    @Autowired
    IF_SNSService sService;

    @Autowired
    FileDataUtil fileDataUtil;

    @GetMapping("/main")
    public String main(Model model) throws Exception {
        List<PostVO> allList = sService.postSelectAll();
        model.addAttribute("allListPost", allList);
        return "main";
    }

    @GetMapping("/post")
    public String post(Model model) {
        return "post";
    }

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam("p_no") String p_no) throws Exception {
        PostVO postDetail = sService.postSelectOne(p_no);
        String attachName = sService.getFile(p_no);
        List<PostCommentVO> commentList = sService.commentList(p_no);
        model.addAttribute("postDetail", postDetail);
        model.addAttribute("attachName", attachName);
        model.addAttribute("commentList", commentList);
        return "detail";
    }

    @PostMapping("/posting")
    public String posting(@ModelAttribute PostVO pvo, MultipartFile[] file) throws Exception {
        String[] filename = fileDataUtil.fileUpload(file);
        pvo.setFilename(filename);
        sService.postInsert(pvo);
        return "redirect:main";
    }

    @PostMapping("/commentSave")
    public String commentSave(@ModelAttribute PostCommentVO pcvo, Model model) throws Exception {
        sService.CommentSave(pcvo);
        model.addAttribute("p_no", pcvo.getPc_no());
        return "redirect:main";
    }
    @GetMapping(value = {"/shorts","/"})
    public String shorts() {return "shorts";}
    @GetMapping(value="/short")
    public String shortOne() {return "short";}
}
