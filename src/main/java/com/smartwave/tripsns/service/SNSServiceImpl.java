package com.smartwave.tripsns.service;

import com.smartwave.tripsns.dao.IF_SNSDAO;
import com.smartwave.tripsns.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SNSServiceImpl implements IF_SNSService {

    @Autowired
    IF_SNSDAO sdao;

    @Override
    public void postInsert(PostVO pvo) throws Exception { //게시글 저장
        sdao.postInsert(pvo);
        String[] filename = pvo.getFilename();
        for (String fname : filename) {
            sdao.postSaveAttach(fname);
        }
    }

    @Override
    public List<PostVO> postSelectAll(String p_id) throws Exception {
        return sdao.postSelectAll(p_id);
    }

    @Override
    public PostVO postSelectOne(String p_no) throws Exception {
        return sdao.postSelectOne(p_no);
    }

    @Override
    public List<String> getFile(String pp_no) throws Exception {
        return sdao.getFile(pp_no);
    }

    @Override
    public void CommentSave(PostCommentVO pcvo) throws Exception {
        sdao.commentSave(pcvo);
    }

    @Override
    public List<PostCommentVO> commentList(String p_no) throws Exception {
        return sdao.commentList(p_no);
    }

    @Override
    public void postDelete(PostVO pvo) throws Exception {
        sdao.postDelete(pvo);
    }

    @Override
    public void postModify(PostVO pvo) throws Exception {
        sdao.postModify(pvo);
    }

    @Override
    public void postCommentDelete(PostCommentVO pvo) throws Exception {
        sdao.postCommentDelete(pvo);
    }

    @Override
    public int postCommentCnt(String p_no) throws Exception {
        return sdao.postCommentCnt(p_no);
    }


    @Override
    public List<PostVO> postSearch(String searchWord) throws Exception {
        return sdao.postSearch(searchWord);
    }

    @Override
    public void postLike(PostVO pvo) throws Exception {
        if (sdao.postLikeSelect(pvo) == null) {
            sdao.postLike(pvo);
        } else {
            sdao.postLikeDelete(pvo);
        }
    }

    @Override
    public PostLikeVO postLikeSelect(PostVO pvo) throws Exception {
        return sdao.postLikeSelect(pvo);
    }

    @Override
    public int postLikeCOunt(PostVO pvo) throws Exception {
        return sdao.postLikeCount(pvo);
    }

    @Override
    public void videoInsert(VideoVO vvo) throws Exception {
        sdao.videoInsert(vvo);
    }

    @Override
    public int videoSelect() throws Exception {
        return sdao.videoSelect();
    }

    @Override
    public List<PostVO> postSelectPost() throws Exception {
        return sdao.postSelectPost();
    }

    @Override
    public void InsertShort(ShortVO svo) throws Exception {
        sdao.InsertShort(svo);
    }

    @Override
    public List<ShortVO> allShortList() throws Exception {
        return sdao.allShortList();
    }

    @Override
    public ShortVO shortDetails(int s_no) throws Exception {
        return sdao.shortDetails(s_no);
    }

    @Override
    public void ShortCommentInsert(ShortCommentVO scvo) throws Exception {
        sdao.ShortCommentInsert(scvo);
    }

    @Override
    public VideoVO getVideo(int sv_no) throws Exception {
        return sdao.GetVideo(sv_no);
    }

    @Override
    public List<ShortCommentVO> shortCommentList(int s_no) throws Exception {
        return sdao.shortCommentList(s_no);
    }

    @Override
    public void shortCommentDelete(ShortCommentVO scvo) throws Exception {
        sdao.shortCommentDelete(scvo);
    }

    @Override
    public void deleteShort(ShortVO svo) throws Exception {
        sdao.deleteShort(svo);
    }

    @Override
    public List<PostVO> postSelectList() throws Exception {
        return sdao.postSelectList();
    }


}
