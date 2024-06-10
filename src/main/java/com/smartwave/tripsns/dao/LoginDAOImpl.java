package com.smartwave.tripsns.dao;

import com.smartwave.tripsns.vo.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAOImpl implements IF_LoginDAO{
    private static String  mapperQuery = "com.smartwave.tripsns.dao.IF_LoginDAO";
    @Autowired
    private SqlSession sqlSession;
    @Override
    public UserVO login(String id) throws Exception {
        return sqlSession.selectOne(mapperQuery+".selectid",id);
    }


}
