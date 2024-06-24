package com.smartwave.tripsns.controller;

import com.smartwave.tripsns.service.IF_SNSService;
import com.smartwave.tripsns.service.IF_UserService;
import com.smartwave.tripsns.util.FileDataUtil;
import com.smartwave.tripsns.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        for (int i = 0; i < allList.size(); i++) {
            System.out.println(allList.get(i).toString());
        }
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
    public String detail(Model model, @RequestParam("p_no") String p_no, @SessionAttribute("userid") String u_id) throws Exception {
        PostVO postDetail = sService.postSelectOne(p_no);
        List<PostCommentVO> commentList = sService.commentList(p_no);
        int postCommentCnt = sService.postCommentCnt(p_no);
        model.addAttribute("postDetail", postDetail);
        model.addAttribute("commentList", commentList);
        model.addAttribute("postCommentCnt", postCommentCnt);
        //프로필 사진 불러오기
        ProfileVO prodetail = userservice.getProfile(u_id);
        model.addAttribute("profiledetail", prodetail);
        //자세히보기 프로필
        String propic = userservice.detailProfile(p_no);
        model.addAttribute("propic", propic);
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
    public String shorts(Model model, @SessionAttribute("userid") String u_id) throws Exception {
        List<ShortVO> shortList = sService.allShortList();
        model.addAttribute("allShortList", shortList);
        //프로필 사진 불러오기
        ProfileVO prodetail = userservice.getProfile(u_id);
        model.addAttribute("profiledetail", prodetail);
        return "shorts";
    }

    @GetMapping(value = "/short")//쇼츠 게시글 자세히 보기
    public String shortOne(Model model, @ModelAttribute ShortLikeVO slikevo, @SessionAttribute("userid") String u_id) throws Exception {
        ShortVO shortDetails = sService.shortDetails(slikevo.getS_no());
        VideoVO vvo = sService.getVideo(shortDetails.getSv_no());
        List<ShortCommentVO> commentList = sService.shortCommentList(slikevo.getS_no());
        int shortCommentCount = sService.shortCommentCount(slikevo.getS_no());
        String profileImg = sService.profileImg(shortDetails.getS_no());
        model.addAttribute("shortDetails", shortDetails);
        model.addAttribute("video", vvo);
        model.addAttribute("commentList", commentList);
        model.addAttribute("shortCommentCount", shortCommentCount);
        model.addAttribute("profileImg", profileImg);
        //프로필 사진 불러오기
        ProfileVO prodetail = userservice.getProfile(u_id);
        model.addAttribute("profiledetail", prodetail);
        return "short";
    }

    @PostMapping(value = "shortCommentInsert")//쇼츠 댓글 입력
    public String shortCommentInsert(@ModelAttribute ShortCommentVO scvo) throws Exception {
        sService.ShortCommentInsert(scvo);
        return "redirect:/short?s_no=" + scvo.getS_no();
    }

    @GetMapping(value = "shortCommentDelete") //댓글 삭제
    public String shortCommentDelete(@ModelAttribute ShortCommentVO scvo) throws Exception {
        sService.shortCommentDelete(scvo);
        return "redirect:/short?s_no=" + scvo.getS_no();
    }

    @GetMapping(value = "/addShortVideo")//쇼츠 비디오 추가
    public String addShortVideo(Model model, @SessionAttribute("userid") String u_id) throws Exception {
        //프로필 사진 불러오기
        ProfileVO prodetail = userservice.getProfile(u_id);
        model.addAttribute("profiledetail", prodetail);
        return "addShortVideo";
    }

    @PostMapping(value = "/addShort")//쇼츠 게시글 추가
    public String addShort(Model model, @ModelAttribute VideoVO vvo, MultipartFile[] file, @SessionAttribute("userid") String u_id) throws Exception {
        String[] filename = fileDataUtil.fileUpload(file);
        vvo.setSv_addr(filename[0]);
        vvo.setSv_thumbnail(filename[1]);
        sService.videoInsert(vvo);
        model.addAttribute("video", filename[0]);
        model.addAttribute("thumbnail", filename[1]);
        model.addAttribute("videoNo", sService.videoSelect());
        //프로필 사진 불러오기
        ProfileVO prodetail = userservice.getProfile(u_id);
        model.addAttribute("profiledetail", prodetail);
        return "addShort";
    }

    @PostMapping(value = "/insertShort")
    public String insertShort(@ModelAttribute ShortVO svo) throws Exception {
        sService.InsertShort(svo);
        return "redirect:shorts";
    }

    @GetMapping(value = "deleteShort") //댓글 삭제
    public String shortDelete(@ModelAttribute ShortVO svo) throws Exception {
        sService.deleteShort(svo);
        return "redirect:/shorts";
    }

    @GetMapping(value = "shortUpdate") //게시글 수정페이지
    public String shortUpdate(Model model,@ModelAttribute ShortVO svo, @SessionAttribute("userid") String u_id) throws Exception {
        if(svo.getS_id().equals(u_id)){
            ShortVO shortDetails = sService.shortDetails(svo.getS_no());
            model.addAttribute("shortDetails", shortDetails);
            //프로필 사진 불러오기
            ProfileVO prodetail = userservice.getProfile(u_id);
            model.addAttribute("profiledetail", prodetail);
            return "shortUpdateForm";
        }else {
            return "redirect:/main";
        }
    }
    @PostMapping(value = "shortUpdateSubmit") //게시글 수정페이지
    public String shortUpdateSubmit(Model model, @ModelAttribute ShortVO svo, @SessionAttribute("userid") String u_id, RedirectAttributes redirectA) throws Exception {
        if(svo.getS_id().equals(u_id)){
            sService.shortUpdateSubmit(svo);
            //프로필 사진 불러오기
            ProfileVO prodetail = userservice.getProfile(u_id);
            model.addAttribute("profiledetail", prodetail);
            return "redirect:/shorts";
        }else {
            return "redirect:/main";
        }
    }

    @GetMapping(value = "deleteShortChk") //댓글 삭제
    public String shortDeleteChecked(@ModelAttribute ShortVO svo) throws Exception {
        sService.deleteShort(svo);
        return "redirect:/manager";
    }

    @ResponseBody
    @GetMapping(value = "shortLikeUpDown") //게시글 좋아요
    public int shortLikeUpDown(@ModelAttribute ShortLikeVO slikevo) throws Exception {
        int flag = sService.shortLikeUpDown(slikevo);
        return flag;
    }


    @ResponseBody
    @GetMapping(value = "shortLikeCount")
    public int shortLikeCount(@ModelAttribute ShortLikeVO slikevo) throws Exception {
        return sService.shortLikeCount(slikevo);
    }

    @ResponseBody
    @GetMapping(value = "shortLikeChk")
    public int shortLikeChk(@ModelAttribute ShortLikeVO slikevo) throws Exception {
        int flag = 0;
        if (sService.shortLikeSelectOne(slikevo) != null) {
            flag = 1;
        }
        return flag;
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

    @GetMapping(value = "postDeleteManager")
    public String postDeleteManager(@ModelAttribute PostVO pvo) throws Exception {
        sService.postDelete(pvo);
        return "redirect:manager";
    }

    @GetMapping(value = "postCommentDelete") //댓글 삭제
    public String postCommentDelete(@ModelAttribute PostCommentVO pcvo, @ModelAttribute PostVO pvo) throws Exception {
        sService.postCommentDelete(pcvo);
        return "redirect:detail?p_no=" + pvo.getP_no() + "&p_id=" + pvo.getP_id();
    }

    @GetMapping(value = "postSearchById") //게시글 검색
    public String postSearchById(@RequestParam("searchWord") String searchWord, Model model, @SessionAttribute("userid") String u_id) throws Exception {
        List<PostVO> pvo = sService.postSearch(searchWord);
        List<ShortVO> svo = sService.shortSearch(searchWord);
        List<ProfileVO> uvo = userservice.userSearch(searchWord);
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("svo",svo);
        model.addAttribute("pvo", pvo);
        model.addAttribute("uvo", uvo);
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

    // 관리자 로그인지 관리페이지로 이동
    @GetMapping(value = "manager")
    public String manager(Model model, @SessionAttribute("userid") String u_id) throws Exception {
        //게시글 정보 가져오기
        List<PostVO> postList = sService.postSelectList();
        model.addAttribute("postList", postList);

        //쇼츠 정보 가져오기
        List<ShortVO> shortList = sService.allShortList();
        model.addAttribute("shortList", shortList);

        //관리자용 회원목록 get
        List<UserVO> userList = userservice.userList();
        model.addAttribute("userList", userList);

        //프로필 사진 불러오기
        ProfileVO prodetail = userservice.getProfile(u_id);
        model.addAttribute("profiledetail", prodetail);
        return "manager";
    }

    @GetMapping(value = "map") //지도 페이지
    public String map(Model model, @SessionAttribute("userid") String u_id, @RequestParam("p_place") String p_place) throws Exception {
        model.addAttribute("p_place", p_place);
        //프로필 사진 불러오기
        ProfileVO prodetail = userservice.getProfile(u_id);
        model.addAttribute("profiledetail", prodetail);
        return "map";
    }

    @ResponseBody //조회수 더하기
    @PostMapping(value = "postViewUpdate")
    public void postViewUpdate(@ModelAttribute PostVO pvo) throws Exception {
        sService.postViewUpdate(pvo);
    }

    @ResponseBody //조회수 조회
    @GetMapping(value = "postViewCount")
    public int postViewCount(@ModelAttribute PostVO pvo) throws Exception {
        int viewCount = sService.postViewCount(pvo);
        return viewCount;
    }

}