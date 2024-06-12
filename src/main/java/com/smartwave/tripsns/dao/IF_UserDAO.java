package com.smartwave.tripsns.dao;

import com.smartwave.tripsns.vo.UserVO;

public interface IF_UserDAO {
    //회원가입
    public void userinsert(UserVO uservo) throws Exception;// 회원가입 정보 insert
    public int idchk(String id) throws Exception;
    //로그인
    public UserVO login(String id) throws Exception;
    //프로필 개인정보 수정
    public void userupdate(UserVO uservo) throws Exception;

}
