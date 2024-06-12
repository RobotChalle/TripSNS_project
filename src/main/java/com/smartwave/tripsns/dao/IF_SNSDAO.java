package com.smartwave.tripsns.dao;

import com.smartwave.tripsns.vo.PostCommentVO;
import com.smartwave.tripsns.vo.PostVO;

import java.util.List;

public interface IF_SNSDAO {

    public void postInsert(PostVO pvo) throws Exception; //게시글 저장

    public void postSaveAttach(String filename) throws Exception; //게시글 사진파일 저장

    public List<PostVO> postSelectAll() throws Exception; //게시글 전부 가져오기

    public PostVO postSelectOne(String p_no) throws Exception; //게시글 하나 가져오기

    public String getFile(String pp_no) throws Exception; //사진 가져오기

    public void commentSave(PostCommentVO pcvo) throws Exception; //댓글 저장하기

    public List<PostCommentVO> commentList(String p_no) throws Exception; //댓글 가져오기

    public void postLike(PostVO pvo) throws Exception; //좋아요 정보 저장하기
    
    public void postLikeDel(PostVO pvo) throws Exception; //좋아요 삭제하기
    
    public int postLikeCnt(PostVO pvo) throws Exception; //좋아요 갯수

    public int postLikeMyCnt(PostVO pvo) throws Exception; //나의 좋아요 유무
}
