package com.smartwave.tripsns.dao;

import com.smartwave.tripsns.vo.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JoinDAOImpl implements IF_JoinDAO {
    private static String  mapperQuery = "com.smartwave.tripsns.dao.IF_JoinDAO";
    @Autowired
    private SqlSession sqlSession;
    @Override
    public void userinsert(UserVO uservo) throws Exception {
        sqlSession.insert(mapperQuery+".userinsert", uservo);
    }

    @Override
    public int idchk(String id) throws Exception {
         return sqlSession.selectOne(mapperQuery+".idchk",id);
    }
}
