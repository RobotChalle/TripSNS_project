package com.smartwave.tripsns.service;

import com.smartwave.tripsns.dao.IF_LoginDAO;
import com.smartwave.tripsns.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements IF_LoginService {
    @Autowired
    IF_LoginDAO logindao;
    @Override
    public UserVO login(String id) throws Exception {

        return logindao.login(id);
    }

}
