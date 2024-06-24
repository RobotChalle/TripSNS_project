package com.smartwave.tripsns.dao;

import com.smartwave.tripsns.vo.AdminVO;
import com.smartwave.tripsns.vo.ProfileVO;
import com.smartwave.tripsns.vo.UserVO;
import com.smartwave.tripsns.vo.FollowVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class UserDAOImpl implements IF_UserDAO {
    private static String  mapperQuery = "com.smartwave.tripsns.dao.IF_UserDAO";
    @Autowired
    private SqlSession sqlSession;
    //회원가입
    @Override
    public void userinsert(UserVO uservo) throws Exception {
        sqlSession.insert(mapperQuery+".userinsert", uservo);
    }


    //아이디 중복체크
    @Override
    public int idchk(String id) throws Exception {
         return sqlSession.selectOne(mapperQuery+".idchk",id);
    }
    //로그인
    @Override
    public UserVO login(String id) throws Exception {
        return sqlSession.selectOne(mapperQuery+".selectid",id);
    }
    //프로필 개인정보 업데이트
    @Override
    public void userupdate(UserVO uservo) throws Exception {
        sqlSession.update(mapperQuery+".proupdate",uservo);
    }
    //프로필 기본 정보 입력(한줄소개 입력코멘트 , 기본 프로필사진)
    @Override
    public void userprofile(UserVO uservo) {
        sqlSession.insert(mapperQuery+".userprofile",uservo);
    }
    //프로필 기본 정보 get(한줄소개 입력코멘트 , 기본 프로필사진)
    @Override
    public ProfileVO getProfile(String u_id) throws Exception {
        return sqlSession.selectOne(mapperQuery+".getprofile",u_id);
    }
    //프로필 업데이트
    @Override
    public void profileupdate(ProfileVO pvo) throws Exception {
        sqlSession.update(mapperQuery+".profileupdate",pvo);
    }
    //프로필 한줄소개 업데이트
    @Override
    public void introupdate(ProfileVO pvo) throws Exception {
        sqlSession.update(mapperQuery+".introupdate",pvo);
    }
    //회원탈퇴시 필요한 pw get
    @Override
    public String getpw(String u_id) throws Exception {
        return sqlSession.selectOne(mapperQuery+".getpw",u_id);
    }
    //회원 탈퇴
    @Override
    public void userdelete(String id) throws Exception {
        sqlSession.delete(mapperQuery+".userdelete",id);
    }
    //게시물 수
    @Override
    public int postcnt(String id) throws Exception {
        return sqlSession.selectOne(mapperQuery+".postcnt",id);
    }
    //쇼츠 수
    @Override
    public int shortcnt(String id) throws Exception {
        return sqlSession.selectOne(mapperQuery+".shortcnt",id);
    }
    //자세히 보기 프로필
    @Override
    public String detailProfile(String p_no) throws Exception {
        return sqlSession.selectOne(mapperQuery+".detailprofile",p_no);
    }

    //관리자 로그인
    @Override
    public AdminVO getAdmin(String id) throws Exception {
        return sqlSession.selectOne(mapperQuery+".getadmin",id);
    }
    // 관리자 회원관리 유저 목록 select
    @Override
    public List<UserVO> userList() throws Exception {
        return sqlSession.selectList(mapperQuery+".userList");
    }
    // 관리자회원관리 체크박스 삭제
    @Override
    public void chkdel(String uid) throws Exception {
        sqlSession.delete(mapperQuery+".chkuserdelete",uid);
    }
    // 관리자 회원 검색제어 select
    @Override
    public List<UserVO> selectUserList(HashMap<String, String> userselect) throws Exception {
        return sqlSession.selectList(mapperQuery+".selectUserList",userselect);
    }
    // 팔로우 insert
    @Override
    public void follow(FollowVO fvo) throws Exception {
        sqlSession.insert(mapperQuery+".followinsert",fvo);
    }
    // 팔로우 조회 팔로우 했던 사람인지 여부판단
    @Override
    public FollowVO selectFollow(FollowVO fvo) throws Exception {
        return sqlSession.selectOne(mapperQuery+".followSelect",fvo);
    }
    // 팔로우 취소
    @Override
    public void followdel(FollowVO fvo) throws Exception {
        sqlSession.delete(mapperQuery+".followdel",fvo);
    }
    //상대 화면 팔로워수 get 팔로우 당한사람 아이디의 팔로워개수
    @Override
    public int followercount(FollowVO fvo) throws Exception {
        return sqlSession.selectOne(mapperQuery+".followercnt",fvo);
    }
    //로그인 한 나의 화면 팔로우 수 get 내가 팔로우 버튼을 클릭했을시 팔로우한 아이디의 팔로우개수
    @Override
    public int followcount(FollowVO fvo) throws Exception {
        return sqlSession.selectOne(mapperQuery+".followcnt",fvo);
    }

    @Override
    public List<ProfileVO> userSearch(String searchWord) throws Exception {
        return sqlSession.selectList(mapperQuery+".userSearch",searchWord);
    }


}
