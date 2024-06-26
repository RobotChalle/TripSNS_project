package com.smartwave.tripsns.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShortVO {
    private int s_no;
    private String s_id;
    private int sv_no;
    private String sv_thumbnail;
    private String s_place;
    private String s_description;
    private String s_date;

    //profile 테이블
    private String u_pic = null; //프로필 사진
}
