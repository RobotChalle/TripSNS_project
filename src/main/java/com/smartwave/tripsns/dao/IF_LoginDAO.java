package com.smartwave.tripsns.dao;

import com.smartwave.tripsns.vo.UserVO;

public interface IF_LoginDAO {
    public UserVO login(String id) throws Exception;

}
