package com.smartwave.tripsns.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostCommentVO {

    private int pc_no = 0;
    private String pc_text = null;
    private String pc_id = null;
    private String pc_indate = null;

}
