package com.smartwave.tripsns.dao;

import com.smartwave.tripsns.vo.UserVO;

public interface IF_JoinDAO {
    public void userinsert(UserVO uservo) throws Exception;// 회원가입 정보 insert
    public int idchk(String id) throws Exception;

}
