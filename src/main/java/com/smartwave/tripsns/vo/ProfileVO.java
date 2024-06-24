package com.smartwave.tripsns.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProfileVO {
    private String u_id;//로그인한 사용자 아이디 저장변수
    private String u_intro;
    private String u_pic;
    private String filename = null;
    
    private String id = null;// 검색어 저장 아이디

}
