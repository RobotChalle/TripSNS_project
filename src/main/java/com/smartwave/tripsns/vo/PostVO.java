package com.smartwave.tripsns.vo;

public class PostVO {

    private int p_no = 0;
    private String p_id = null;
    private String p_place = null;
    private String p_text = null;
    private String p_indate = null;
    private String pp_pic = null;
    private String[] filename = null;

    public int getP_no() {
        return p_no;
    }

    public void setP_no(int p_no) {
        this.p_no = p_no;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_place() {
        return p_place;
    }

    public void setP_place(String p_place) {
        this.p_place = p_place;
    }

    public String getP_text() {
        return p_text;
    }

    public void setP_text(String p_text) {
        this.p_text = p_text;
    }

    public String getP_indate() {
        return p_indate;
    }

    public void setP_indate(String p_indate) {
        this.p_indate = p_indate;
    }

    public String getPp_pic() {
        return pp_pic;
    }

    public void setPp_pic(String pp_pic) {
        this.pp_pic = pp_pic;
    }

    public String[] getFilename() {
        return filename;
    }

    public void setFilename(String[] filename) {
        this.filename = filename;
    }
}
