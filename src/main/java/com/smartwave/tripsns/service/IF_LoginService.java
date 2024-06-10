package com.smartwave.tripsns.service;

import com.smartwave.tripsns.vo.UserVO;

public interface IF_LoginService {
    public UserVO login(String id) throws Exception;

}
