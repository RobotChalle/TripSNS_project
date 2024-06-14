package com.smartwave.tripsns.dao;

import com.smartwave.tripsns.vo.ProfileVO;
import com.smartwave.tripsns.vo.UserVO;

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


}
