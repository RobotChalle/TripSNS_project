package com.smartwave.tripsns.service;

import com.smartwave.tripsns.vo.UserVO;

public interface IF_UserService {
    //회원가입
    public void userinsert(UserVO uservo) throws Exception;
    public int idchk(String id) throws Exception;
    //로그인
    public UserVO login(String id) throws Exception;
}
