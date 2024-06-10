package com.smartwave.tripsns.service;

import com.smartwave.tripsns.vo.UserVO;

public interface IF_JoinService {
    public void userinsert(UserVO uservo) throws Exception;
    public int idchk(String id) throws Exception;
}
