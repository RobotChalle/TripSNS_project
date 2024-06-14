package com.smartwave.tripsns.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;

@Getter
@Setter
@ToString
public class PostVO {

    private int p_no = 0;
    private String p_id = null;
    private String p_place = null;
    private String p_text = null;
    private String p_indate = null;
    private String pp_pic = null;
    private String[] filename = null;
    private int pl_no = 0;
    private String pl_id = null;

}
