package com.yxk.tjm.tianjiumeng.home.bean;


/**
 * Created by zkt on 2017/3/7.
 */

public class FlashSaleBean {
    private int resImage;

    public int getResImage() {
        return resImage;
    }

    public FlashSaleBean(int resImage) {
        this.resImage = resImage;
    }

    //倒计时相关
    private long countdown_0;
    private long  countdown_endTime;
    private int countdown_id;

    public int getCountdown_id() {
        return countdown_id;
    }

    public void setCountdown_id(int countdown_id) {
        this.countdown_id = countdown_id;
    }

    public long getCountdown_endTime() {
        return countdown_endTime;
    }

    public void setCountdown_endTime(long countdown_endTime) {
        this.countdown_endTime = countdown_endTime;
    }

    public long getCountdown_0() {
        return countdown_0;
    }

    public void setCountdown_0(long countdown_0) {
        this.countdown_0 = countdown_0;
    }


}
