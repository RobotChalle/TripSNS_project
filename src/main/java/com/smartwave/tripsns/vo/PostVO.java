package com.smartwave.tripsns.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;

@Getter
@Setter
@ToString
public class PostVO {

    //post 테이블
    private int p_no = 0;
    private String p_id = null;
    private String p_place = null;
    private String p_text = null;
    private String p_indate = null;

    //postPic 테이블
    private String pp_pic = null;
    private String[] filename = null;

    //profile 테이블
    private String u_pic = null; //프로필 사진
}
