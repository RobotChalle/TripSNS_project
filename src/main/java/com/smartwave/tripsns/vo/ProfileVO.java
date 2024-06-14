package com.smartwave.tripsns.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileVO {
    private String u_id;
    private String u_intro;
    private String u_pic;
    private String filename = null;
}
