package com.smartwave.tripsns.service;


import com.smartwave.tripsns.dao.IF_UserDAO;
import com.smartwave.tripsns.vo.AdminVO;
import com.smartwave.tripsns.vo.ProfileVO;
import com.smartwave.tripsns.vo.UserVO;
import com.smartwave.tripsns.vo.FollowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements IF_UserService {
    @Autowired
    IF_UserDAO sdao;
    //회원가입
    @Override
    public void userinsert(UserVO uservo) throws Exception {
    sdao.userinsert(uservo);
    }
    //아이디 중복체크
    @Override
    public int idchk(String id) throws Exception {
        int cnt = sdao.idchk(id);
        return cnt;
    }
    //로그인
    @Override
    public UserVO login(String id) throws Exception {

        return sdao.login(id);
    }
    //프로필 개인정보 수정
    @Override
    public void userupdate(UserVO uservo) throws Exception {
        sdao.userupdate(uservo);
    }
    //프로필 기본 정보 입력(한줄소개 입력코멘트 , 기본 프로필사진)
    @Override
    public void userprofile(UserVO uservo) throws Exception{
        sdao.userprofile(uservo);
    }
    //프로필 기본 정보 get(한줄소개 입력코멘트 , 기본 프로필사진)
    @Override
    public ProfileVO getProfile(String u_id) throws Exception {
        return sdao.getProfile(u_id);
    }
    //프로필  수정
    @Override
    public void profileupdate(ProfileVO pvo) throws Exception {
        sdao.profileupdate(pvo);
    }
    //프로필 한줄소개 수정
    @Override
    public void introupdate(ProfileVO pvo) throws Exception {
        sdao.introupdate(pvo);
    }
    //회원탈퇴시 필요한 pw get
    @Override
    public String getpw(String u_id) throws Exception {
        return sdao.getpw(u_id);
    }
    //회원탈퇴
    @Override
    public void userdelete(String id) throws Exception {
        sdao.userdelete(id);
    }
    // 게시물수
    @Override
    public int postcnt(String id) throws Exception {
        return sdao.postcnt(id);
    }
    // 쇼츠 수
    @Override
    public int shortcnt(String id) throws Exception {
        return sdao.shortcnt(id);
    }
    //자세히 보기 프로필 innerjoin
    @Override
    public String detailProfile(String p_no) throws Exception {
        return sdao.detailProfile(p_no);
    }

    // 관리자 로그인
    @Override
    public AdminVO getAdmin(String id) throws Exception {
        return sdao.getAdmin(id);
    }
    // 관리자용 회원목록
    @Override
    public List<UserVO> userList() throws Exception {
        return sdao.userList();
    }
    // 관리자용 체크박스 삭제
    @Override
    public void chkdel(String[] id) throws Exception {
        for(String uid:id){
            sdao.chkdel(uid);
        }
    }
    // 관리자용 회원 검색목록 select
    @Override
    public List<UserVO> selectUserList(HashMap<String, String> userselect) throws Exception {
        return sdao.selectUserList(userselect);
    }
    // 팔로우  (팔로우 삽입 , 삭제)
    @Override
    public void follow(FollowVO fvo) throws Exception {
        if(sdao.selectFollow(fvo)==null){// 팔로우 했던 사람이 아니면 db에 값이 없다면
            sdao.follow(fvo);// 팔로우
        }else{
            sdao.followdel(fvo);// 값있으면 중복 x -> 삭제
        }

    }
    // 팔로우 조회 팔로우 했던 사람인지 여부판단
    @Override
    public FollowVO selectFollow(FollowVO fvo) throws Exception {
        return sdao.selectFollow(fvo);
    }
    //상대 화면 팔로워수 get 팔로우 당한사람 아이디의 팔로워개수
    @Override
    public int followercount(FollowVO fvo) throws Exception {
        return sdao.followercount(fvo);
    }
    //로그인 한 나의 화면 팔로우 수 get 내가 팔로우 버튼을 클릭했을시 팔로우한 아이디의 팔로우개수
    @Override
    public int followcount(FollowVO fvo) throws Exception {
        return sdao.followcount(fvo);
    }
    //유저 검색
    @Override
    public List<ProfileVO> userSearch(String searchWord) throws Exception {
        return sdao.userSearch(searchWord);
    }
    // 팔로우 리스트
    @Override
    public List<FollowVO> followList(String userid) throws Exception {
        return sdao.followList(userid);
    }
    // 팔로워 리스트
    @Override
    public List<FollowVO> followerList(String userid) throws Exception {
        return sdao.followerList(userid);
    }
}
