package com.smartwave.tripsns.service;

import com.smartwave.tripsns.vo.AdminVO;
import com.smartwave.tripsns.vo.ProfileVO;
import com.smartwave.tripsns.vo.UserVO;

import java.util.HashMap;
import java.util.List;

public interface IF_UserService {
    //회원가입
    public void userinsert(UserVO uservo) throws Exception;
    public int idchk(String id) throws Exception;
    //로그인
    public UserVO login(String id) throws Exception;
    // 프로필 개인정보 수정
    public void userupdate(UserVO uservo) throws Exception;

    public void userprofile(UserVO uservo) throws Exception;// 프로필 기본정보(한줄소개, 기본 프로필 사진)
    //프로필 기본정보 get
    public ProfileVO getProfile(String u_id) throws Exception;
    // 프로필  수정
    public void profileupdate(ProfileVO pvo) throws Exception ;
    //프로필 한줄소개만 업데이트
    public void introupdate(ProfileVO pvo) throws Exception;
    //회원탈퇴시 비번 대조하기위한 메서드
    public String getpw(String u_id) throws Exception;
    //회원탈퇴 delete
    public void userdelete(String id) throws Exception;
    //게시물 수 get
    public int postcnt(String id) throws Exception;
    //쇼츠 수 get
    public int shortcnt(String id) throws Exception;
    // 자세히보기 프로필 get
    public String detailProfile(String p_no) throws Exception;
    //관리자 로그인
    public AdminVO getAdmin(String id) throws Exception;
    //관리자용 회원목록 전부 보기
    public List<UserVO> userList() throws Exception;
    // 체크된 유저목록 삭제
    public void chkdel(String[] id)throws Exception;
    //select 박스 검색제어 (아이디,이름)
    public List<UserVO> selectUserList(HashMap<String, String> userselect) throws Exception;
}
