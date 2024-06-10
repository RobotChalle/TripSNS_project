package com.smartwave.tripsns.service;

import com.smartwave.tripsns.dao.IF_JoinDAO;
import com.smartwave.tripsns.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinServiceImpl implements IF_JoinService {
    @Autowired
    IF_JoinDAO sdao;
    @Override
    public void userinsert(UserVO uservo) throws Exception {
    sdao.userinsert(uservo);
    }

    @Override
    public int idchk(String id) throws Exception {
        int cnt = sdao.idchk(id);
        return cnt;
    }
}
