package com.smartwave.tripsns.dao;

import com.smartwave.tripsns.vo.*;
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
    public void postInsert(PostVO pvo) throws Exception {
        sqlSession.insert(mapperQuery + ".posting", pvo);
    }

    @Override
    public void postSaveAttach(String filename) throws Exception {
        sqlSession.insert(mapperQuery + ".postSaveAttach", filename);
    }

    @Override
    public List<PostVO> postSelectAll(String p_id) throws Exception {
        return sqlSession.selectList(mapperQuery + ".postSelectAll", p_id);
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
        return sqlSession.selectOne(mapperQuery + ".videoSelect");
    }

    @Override
    public void InsertShort(ShortVO svo) throws Exception {
        sqlSession.insert(mapperQuery + ".insertShort", svo);
    }

    @Override
    public List<ShortVO> allShortList() throws Exception {
        return sqlSession.selectList(mapperQuery + ".allShortList");
    }

    @Override
    public ShortVO shortDetails(int s_no) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".shortDetails", s_no);
    }

    @Override
    public void ShortCommentInsert(ShortCommentVO scvo) throws Exception {
        sqlSession.insert(mapperQuery + ".shortCommentInsert", scvo);
    }

    @Override
    public VideoVO GetVideo(int sv_no) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".getVideo", sv_no);
    }

    @Override
    public List<ShortCommentVO> shortCommentList(int s_no) throws Exception {
        return sqlSession.selectList(mapperQuery + ".shortCommentList", s_no);
    }

    @Override
    public void shortCommentDelete(ShortCommentVO scvo) throws Exception {
        sqlSession.delete(mapperQuery + ".shortCommentDelete", scvo);
    }

    @Override
    public void deleteShort(ShortVO svo) throws Exception {
        sqlSession.delete(mapperQuery + ".deleteShort", svo);
    }

    @Override
    public List<PostVO> postSelectList() throws Exception {
        return sqlSession.selectList(mapperQuery + ".postSelectList");
    }

    @Override
    public void shortLikeInsert(ShortLikeVO slikevo) throws Exception {
        sqlSession.insert(mapperQuery + ".shortLikeInsert", slikevo);
    }

    @Override
    public void shortLikeDelete(ShortLikeVO slikevo) throws Exception {
        sqlSession.delete(mapperQuery + ".shortLikeDelete", slikevo);
    }

    @Override
    public ShortLikeVO shortLikeSelectOne(ShortLikeVO slikevo) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".shortLikeSelectOne", slikevo);
    }

    @Override
    public int shortLikeCount(ShortLikeVO slikevo) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".shortLikeCount", slikevo);
    }

    @Override
    public int shortCommentCount(int s_no) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".shortCommentCount", s_no);
    }

    @Override
    public String profileImg(int s_no) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".profileImg", s_no);
    }

    @Override
    public void postViewUpdate(PostVO pvo) throws Exception {
        sqlSession.insert(mapperQuery + ".postViewUpdate", pvo);
    }

    @Override
    public int postViewCount(PostVO pvo) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".postViewCount", pvo);
    }


}
