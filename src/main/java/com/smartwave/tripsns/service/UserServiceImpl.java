package com.smartwave.tripsns.service;


import com.smartwave.tripsns.dao.IF_UserDAO;
import com.smartwave.tripsns.vo.ProfileVO;
import com.smartwave.tripsns.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IF_UserService {
    @Autowired
    IF_UserDAO sdao;
    //회원가입
    @Override
    public void userinsert(UserVO uservo) throws Exception {
    sdao.userinsert(uservo);
    }

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

    @Override
    public void introupdate(ProfileVO pvo) throws Exception {
        sdao.introupdate(pvo);
    }

    @Override
    public String getpw(String u_id) throws Exception {
        return sdao.getpw(u_id);
    }
}
