package com.smartwave.tripsns.dao;

import com.smartwave.tripsns.vo.PostCommentVO;
import com.smartwave.tripsns.vo.PostLikeVO;
import com.smartwave.tripsns.vo.PostVO;
import com.smartwave.tripsns.vo.ShortVO;
import com.smartwave.tripsns.vo.VideoVO;
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
    public List<String> getFile(String pp_no) throws Exception {
        return sqlSession.selectList(mapperQuery + ".getFile", pp_no);
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
    public void postDelete(PostVO pvo) throws Exception {
        sqlSession.delete(mapperQuery + ".postDelete", pvo);
    }

    @Override
    public void postModify(PostVO pvo) throws Exception {
        sqlSession.update(mapperQuery + ".postModify", pvo);
    }

    @Override
    public void postCommentDelete(PostCommentVO pvo) throws Exception {
        sqlSession.delete(mapperQuery + ".postCommentDelete", pvo);
    }

    @Override
    public int postCommentCnt(String p_no) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".postCommentCnt", p_no);
    }

    @Override
    public List<PostVO> postSelectPost() throws Exception {
        return sqlSession.selectList(mapperQuery + ".postSelectPost");
    }

    @Override
    public List<PostVO> postSearch(String searchWord) throws Exception {
        return sqlSession.selectList(mapperQuery + ".postSearch", searchWord);
    }

    @Override
    public void postLike(PostVO pvo) throws Exception {
        sqlSession.insert(mapperQuery + ".postLike", pvo);
    }

    @Override
    public void postLikeDelete(PostVO pvo) throws Exception {
        sqlSession.delete(mapperQuery + ".postLikeDelete", pvo);
    }

    @Override
    public PostLikeVO postLikeSelect(PostVO pvo) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".postLikeSelect", pvo);
    }

    @Override
    public int postLikeCount(PostVO pvo) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".postLikeCount", pvo);
    }

    @Override
    public void videoInsert(VideoVO vvo) throws Exception {//쇼츠 동영상 저장
        sqlSession.insert(mapperQuery + ".videoInsert", vvo);
    }

    @Override
    public int videoSelect() throws Exception {
        return sqlSession.selectOne(mapperQuery+".videoSelect");
    }

    @Override
    public void InsertShort(ShortVO svo) throws Exception {
        sqlSession.insert(mapperQuery+".insertShort",svo);
    }

    @Override
    public List<ShortVO> allShortList() throws Exception {
        return sqlSession.selectList(mapperQuery+".allShortList");
    }

}
