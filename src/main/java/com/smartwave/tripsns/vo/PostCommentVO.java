package com.smartwave.tripsns.vo;

public class PostCommentVO {

    private int pc_no = 0;
    private String pc_text = null;
    private String pc_id = null;
    private String pc_indate = null;

    public int getPc_no() {
        return pc_no;
    }

    public void setPc_no(int pc_no) {
        this.pc_no = pc_no;
    }

    public String getPc_text() {
        return pc_text;
    }

    public void setPc_text(String pc_text) {
        this.pc_text = pc_text;
    }

    public String getPc_id() {
        return pc_id;
    }

    public void setPc_id(String pc_id) {
        this.pc_id = pc_id;
    }

    public String getPc_indate() {
        return pc_indate;
    }

    public void setPc_indate(String pc_indate) {
        this.pc_indate = pc_indate;
    }
}
