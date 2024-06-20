package com.smartwave.tripsns.dao;

import com.smartwave.tripsns.vo.AdminVO;
import com.smartwave.tripsns.vo.ProfileVO;
import com.smartwave.tripsns.vo.UserVO;

import java.util.List;

public interface IF_UserDAO {
    //회원가입
    public void userinsert(UserVO uservo) throws Exception;// 회원가입 정보 insert
    public int idchk(String id) throws Exception;
    //로그인
    public UserVO login(String id) throws Exception;
    //프로필 개인정보 수정
    public void userupdate(UserVO uservo) throws Exception;
    // 프로필 기본 정보 회원가입시 입력
    public void userprofile(UserVO uservo)throws Exception;
    //프로필 기본정보 get
    public ProfileVO getProfile(String u_id) throws Exception;
    //프로필 업데이트
   public void profileupdate(ProfileVO pvo) throws Exception;
    //프로필 한줄소개만 업데이트
    public void introupdate(ProfileVO pvo) throws Exception;
    //회원탈퇴시 필요한 pw 가져오기
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
    public void chkdel(String uid)throws Exception;
}
