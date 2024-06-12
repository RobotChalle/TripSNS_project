package com.smartwave.tripsns.dao;

import com.smartwave.tripsns.vo.PostCommentVO;
import com.smartwave.tripsns.vo.PostVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SNSDAOImpl implements IF_SNSDAO {

    private static String mapperQuery = "com.smartwave.tripsns.dao.IF_SNSDAO";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void postInsert(PostVO pvo) throws Exception { //게시글 저장
        sqlSession.insert(mapperQuery + ".posting", pvo);
    }

    @Override
    public void postSaveAttach(String filename) throws Exception { //게시글 사진파일 저장
        sqlSession.insert(mapperQuery + ".postSaveAttach", filename);
    }

    @Override
    public List<PostVO> postSelectAll() throws Exception {
        return sqlSession.selectList(mapperQuery + ".postSelectAll");
    }

    @Override
    public PostVO postSelectOne(String p_no) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".postSelectOne", p_no);
    }

    @Override
    public String getFile(String pp_no) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".getFile", pp_no);
    }

    @Override
    public void commentSave(PostCommentVO pcvo) throws Exception {
        sqlSession.insert(mapperQuery + ".commentSave", pcvo);
    }

    @Override
    public List<PostCommentVO> commentList(String p_no) throws Exception {
        return sqlSession.selectList(mapperQuery + ".commentList", p_no);
    }

    @Override
    public void postLike(PostVO pvo) throws Exception {
        sqlSession.insert(mapperQuery + ".postLike", pvo);
    }

    @Override
    public void postLikeDel(PostVO pvo) throws Exception {
        sqlSession.delete(mapperQuery + ".postLikeDel", pvo);
    }

    @Override
    public int postLikeCnt(PostVO pvo) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".postLikeCnt", pvo);
    }

    @Override
    public int postLikeMyCnt(PostVO pvo) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".postLikeMyCnt", pvo);
    }
}
