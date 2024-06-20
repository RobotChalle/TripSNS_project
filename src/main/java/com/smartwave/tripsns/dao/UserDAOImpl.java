package com.smartwave.tripsns.dao;

import com.smartwave.tripsns.vo.AdminVO;
import com.smartwave.tripsns.vo.ProfileVO;
import com.smartwave.tripsns.vo.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    @Override
    public List<UserVO> userList() throws Exception {
        return sqlSession.selectList(mapperQuery+".userList");
    }

    @Override
    public void chkdel(String uid) throws Exception {
        sqlSession.delete(mapperQuery+".chkuserdelete",uid);
    }


}
