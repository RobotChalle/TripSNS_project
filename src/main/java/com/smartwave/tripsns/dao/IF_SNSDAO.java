package com.smartwave.tripsns.dao;

import com.smartwave.tripsns.vo.*;

import java.util.List;

public interface IF_SNSDAO {

    public void postInsert(PostVO pvo) throws Exception; //게시글 저장

    public void postSaveAttach(String filename) throws Exception; //게시글 사진파일 저장

    public List<PostVO> postSelectAll(String p_id) throws Exception; //게시글 전부 가져오기

    public PostVO postSelectOne(String p_no) throws Exception; //게시글 하나 가져오기

    public List<String> getFile(String pp_no) throws Exception; //사진 가져오기

    public void commentSave(PostCommentVO pcvo) throws Exception; //댓글 저장하기

    public List<PostCommentVO> commentList(String p_no) throws Exception; //댓글 가져오기

    public void videoInsert(VideoVO vvo) throws Exception;

    public int videoSelect() throws Exception;

    public void postDelete(PostVO pvo) throws Exception; //게시물 삭제

    public void postModify(PostVO pvo) throws Exception; //게시물 수정

    public void postCommentDelete(PostCommentVO pvo) throws Exception; //댓글 삭제

    public int postCommentCnt(String p_no) throws Exception; //댓글 갯수

    public List<PostVO> postSelectPost() throws Exception; //게시글 관련 모든 정보 조회

    public List<PostVO> postSearch(String searchWord) throws Exception; //게시글 아이디로 검색

    public void postLike(PostVO pvo) throws Exception; //게시글 좋아요

    public void postLikeDelete(PostVO pvo) throws Exception; //게시글 좋아요 취소

    public PostLikeVO postLikeSelect(PostVO pvo) throws Exception; //게시글 좋아요 조회

    public int postLikeCount(PostVO pvo) throws Exception; //게시글 좋아요 갯수

    public void InsertShort(ShortVO svo) throws Exception;

    public List<ShortVO> allShortList() throws Exception;

    public ShortVO shortDetails(int s_no) throws Exception;

    public void ShortCommentInsert(ShortCommentVO scvo) throws Exception;

    public VideoVO GetVideo(int sv_no) throws Exception;

    public List<ShortCommentVO> shortCommentList(int s_no) throws Exception;

    public void shortCommentDelete(ShortCommentVO scvo) throws Exception;

    public void deleteShort(ShortVO svo) throws Exception;

    public List<PostVO> postSelectList() throws Exception; //게시글 전부 보기

    public void shortLikeInsert(ShortLikeVO slikevo) throws Exception;

    public void shortLikeDelete(ShortLikeVO slikevo) throws Exception;

    public ShortLikeVO shortLikeSelectOne(ShortLikeVO slikevo) throws Exception;

    public int shortLikeCount(ShortLikeVO slikevo) throws Exception;

    public int shortCommentCount(int s_no) throws Exception;

    public String profileImg(int s_no)throws Exception;
}
