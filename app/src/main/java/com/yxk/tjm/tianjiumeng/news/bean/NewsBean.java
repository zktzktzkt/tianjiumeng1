package com.yxk.tjm.tianjiumeng.news.bean;

/**
 * Created by ningfei on 2017/3/11.
 */

public class NewsBean {
    public NewsBean(int resPicId, String title, String time) {
        this.resPicId = resPicId;
        this.title = title;
        this.time = time;
    }

    public int getResPicId() {
        return resPicId;
    }

    public void setResPicId(int resPicId) {
        this.resPicId = resPicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private int resPicId;
    private String title;
    private String time;

}
