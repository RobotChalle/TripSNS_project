package com.smartwave.tripsns.dao;

import com.smartwave.tripsns.vo.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements IF_UserDAO {
    private static String  mapperQuery = "com.smartwave.tripsns.dao.IF_UserDAO";
    @Autowired
    private SqlSession sqlSession;
    //회원가입
    @Override
    public void userinsert(UserVO uservo) throws Exception {
        sqlSession.insert(mapperQuery+".userinsert", uservo);
    }

    @Override
    public int idchk(String id) throws Exception {
         return sqlSession.selectOne(mapperQuery+".idchk",id);
    }
    //로그인
    @Override
    public UserVO login(String id) throws Exception {
        return sqlSession.selectOne(mapperQuery+".selectid",id);
    }
}
