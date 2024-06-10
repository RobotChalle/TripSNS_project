package com.smartwave.tripsns.service;

import com.smartwave.tripsns.dao.IF_SNSDAO;
import com.smartwave.tripsns.vo.PostCommentVO;
import com.smartwave.tripsns.vo.PostVO;
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
    public List<PostVO> postSelectAll() throws Exception {
        return sdao.postSelectAll();
    }

    @Override
    public PostVO postSelectOne(String p_no) throws Exception {
        return sdao.postSelectOne(p_no);
    }

    @Override
    public String getFile(String pp_no) throws Exception {
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
}
