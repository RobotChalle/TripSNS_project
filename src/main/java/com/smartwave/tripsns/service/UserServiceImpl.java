package com.smartwave.tripsns.service;


import com.smartwave.tripsns.dao.IF_UserDAO;
import com.smartwave.tripsns.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IF_UserService {
    @Autowired
    IF_UserDAO sdao;
    //회원가입
    @Override
    public void userinsert(UserVO uservo) throws Exception {
    sdao.userinsert(uservo);
    }

    @Override
    public int idchk(String id) throws Exception {
        int cnt = sdao.idchk(id);
        return cnt;
    }
    //로그인
    @Override
    public UserVO login(String id) throws Exception {

        return sdao.login(id);
    }
}
